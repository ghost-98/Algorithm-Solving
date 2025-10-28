import java.io.*;
import java.util.*;

// 첫 접근 : dp
// 이런 문제는 그리디로 될거 같은데, 적용되지 않는 문제임 -> dp로 하면 O(n^2) 가능
// 공간도 배열 n^2로 만들어야하는게 맞다네 + 하향식 dp
public class bj1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 1 ~ 500

        int[][] tri = new int[n + 1][n + 1];
        for (int floor = 1; floor <= n; floor++) {
            st = new StringTokenizer(br.readLine());
            for (int elem = 1; elem <= floor; elem++) {
                tri[floor][elem] = Integer.parseInt(st.nextToken()); // 0 ~ 999
            }
        }

        // dp 로직 (최대)
        int[][] dp = new int[n + 1][n + 1];
        dp[1][1] = tri[1][1];
        for (int floor = 1; floor < n; floor++) {
            for (int elem = 1; elem <= floor; elem++) {
                dp[floor + 1][elem] = Math.max(dp[floor + 1][elem], dp[floor][elem] + tri[floor + 1][elem]);
                dp[floor + 1][elem + 1] = Math.max(dp[floor + 1][elem + 1], dp[floor][elem] + tri[floor + 1][elem + 1]);
            }
        }

        int ret = 0;
        for (int i = 1; i <= n; i++)
            ret = Math.max(ret, dp[n][i]);
        System.out.print(ret);
    }
}