import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_2493 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] ret = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();  // ??

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int height = Integer.parseInt(st.nextToken());

            while(!stk.isEmpty() && stk.peek()[1] <= height){
                stk.pop();
            }

            if(stk.isEmpty()) ret[i] = 0;
            else ret[i] = stk.peek()[0] + 1;

            stk.push(new int[]{i, height});  // ?? 
        }

        // 대량 입출력
        StringBuilder sb = new StringBuilder();
        for(int i : ret) sb.append(i).append(" "); // 익명 객체
        System.out.println(sb.toString());
    }
    // n < 50만, 완탐이면 50만 * 50만 -> DP, 그리디 아닌 자료구조
    // 이 문제는 스택문제!! + 스택에 [인덱스, 높이]로 저장
    // Scanner는 Exception 안 던짐 but BufferedReader는 던지므로 throws IOException 선언 혹은 try-catch 구문
    // br로 정수 입력 받는법
    // StringTokenizer 쓰는법과 기본형의 Wrapper클래스 사용과 메서드
    // StringBuilder 사용 
}
