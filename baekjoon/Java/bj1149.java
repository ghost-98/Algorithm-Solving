import java.io.*;
import java.util.*;

// 완탐시 3*2^(n-1)
// int[] dp로 접근하니 국소 최적해가 전역적 최적해 보장x (그리디식x) -> dp에서 메모이제이션 할 변수차원 확장(색)
public class bj1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); // 2 ≤ N ≤ 1,000

        int[][] cost = new int[n+1][3];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n+1][3];
        dp[1][0] = cost[1][0];
        dp[1][1] = cost[1][1];
        dp[1][2] = cost[1][2];

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
        }        

        // dp[n][0], dp[n][1], dp[n][2] 중 최소
        System.out.println(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2])));
    }
}
