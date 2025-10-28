import java.util.Scanner;

// 파스칼의 삼각형 + 모듈러 연산
public class bj11051x {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int MOD = 10007;

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
