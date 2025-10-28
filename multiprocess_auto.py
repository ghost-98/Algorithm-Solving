import multiprocessing
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.common.exceptions import (
    NoSuchElementException,
    WebDriverException,
    TimeoutException,
)
import time

YOUTUBE_URL = "https://www.youtube.com/watch?v=GGE2zkkSPfo"
REPEAT_COUNT = 99999  # 각 인스턴스가 반복할 횟수
VIDEO_DURATION = 40  # 초 단위로 영상 길이
NUM_PROCESSES = 5  # 동시에 실행할 브라우저 인스턴스 수

def create_driver():
    options = webdriver.ChromeOptions()
    options.add_argument("--mute-audio")
    options.add_argument("--disable-infobars")
    options.add_argument("--disable-extensions")
    options.add_argument("--start-maximized")
    options.add_argument("--disable-notifications")
    # options.add_argument("--headless")  # 필요한 경우 활성화

    service = Service()
    return webdriver.Chrome(service=service, options=options)


def watch_video(instance_id):
    try:
        driver = create_driver()
    except WebDriverException as e:
        print(f"[{instance_id}] ❌ 드라이버 생성 실패: {e}")
        return

    for i in range(REPEAT_COUNT):
        try:
            print(f"[{instance_id}] ▶️ {i+1}/{REPEAT_COUNT} 반복 재생 시작")
            driver.get(YOUTUBE_URL)
            time.sleep(3)

            try:
                time.sleep(2)
                skip_btn = driver.find_element(By.CLASS_NAME, "ytp-ad-skip-button")
                skip_btn.click()
                print(f"[{instance_id}] ⏩ 광고 스킵")
            except NoSuchElementException:
                pass

            try:
                driver.execute_script("""
                    var video = document.querySelector('video');
                    if (video) {
                        video.muted = true;
                        video.play();
                    }
                """)
                print(f"[{instance_id}] ▶ 영상 강제 재생 시도")
            except Exception as e:
                print(f"[{instance_id}] ⚠️ 영상 재생 실패: {e}")

            time.sleep(VIDEO_DURATION)
            print(f"[{instance_id}] ⏹️ {i+1}/{REPEAT_COUNT} 영상 종료")

        except TimeoutException:
            print(f"[{instance_id}] ⏱️ 타임아웃")
        except Exception as e:
            print(f"[{instance_id}] ⚠️ 오류 발생: {e}")

    driver.quit()
    print(f"[{instance_id}] ✅ 반복 시청 종료")


def main():
    processes = []

    for i in range(NUM_PROCESSES):
        p = multiprocessing.Process(target=watch_video, args=(i,))
        p.start()
        processes.append(p)

    for p in processes:
        p.join()


if __name__ == "__main__":
    main()
