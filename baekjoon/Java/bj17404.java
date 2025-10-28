import java.util.*;
import java.io.*;

// 처음 접근 : 완탐 불가하니 dp 그런데 idx 1과 n번째간의 관계를 고려한 (시작 인덱스 저장한 차원 추가 혹은 시작인덱스에 따라 세번 반복문 + 2차원dp)
// 아이디어와 나머지 코드는 다 짰음 -> INF 활용 기법!!!
public class bj17404 {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] cost = new int[n + 1][3]; // 빨 초 파 순서
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                cost[i][j] = Integer.parseInt(st.nextToken());
        }

        int answer = INF;

        // start 색을 하나씩 고정하고 DP 진행
        for (int start = 0; start < 3; start++) {
            int[][] dp = new int[n + 1][3];

            // 1번째 집 초기화
            for (int j = 0; j < 3; j++) {
                if (j == start)
                    dp[1][j] = cost[1][j];
                else
                    dp[1][j] = INF;
            }

            // 2번째 집부터 n번째 집까지 진행
            for (int i = 2; i <= n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
            }

            // 마지막 집 색은 start 색과 달라야 함
            for (int end = 0; end < 3; end++) {
                if (start == end)
                    continue;
                answer = Math.min(answer, dp[n][end]);
            }
        }

        System.out.println(answer);
    }
}