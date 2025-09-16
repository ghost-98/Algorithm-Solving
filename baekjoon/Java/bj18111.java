import java.io.*;
import java.util.*;
// 40m
// 아이디어 : 완탐. 0부터 기존 땅의 수와 B값으로 맵 크기를 나눈값까지의 범위 설정이 주효했음
public class bj18111 {
    static int n, m, b;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 1 ≤ M, N ≤ 500
        m = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken()); // 0 ≤ B ≤ 6.4 × 107

        map = new int[n][m];
        int initGroundSum = 0;

        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken()); // 0 ~ 256
                initGroundSum += map[r][c];
            }
        }

        int heightRet = 0;
        int timeRet = Integer.MAX_VALUE;
        // 범위 지정 0부터 처음 땅의 합과 인벤토리 땅 합을 맵에 고루나눈 크기까지
        int endIdx = (initGroundSum + b) / (n * m);
        for(int height = endIdx; height >= 0; height--) { // height : 땅을 맞추는 높이
            int[] gradeRet = grade(height);
            int dig = gradeRet[0];
            int fill = gradeRet[1];
        
            if(dig + b < fill) continue; // 처리 필요

            int time = dig * 2 + fill;

            if(time < timeRet) {
                timeRet = time;
                heightRet = height;
            }
        }

        System.out.print(timeRet + " " + heightRet);
    }

    static int[] grade(int h) {
        int[] cnt = {0, 0}; // [0]은 파야하는 땅의 수, [1]은 메워야 하는 땅의 수
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(map[r][c] >= h) cnt[0] += map[r][c] - h;
                else cnt[1] += h - map[r][c];
            }
        }

        return cnt;
    }
}

