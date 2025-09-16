import java.util.Scanner;
// 25m
// 완탐으로 하면 2^98가지.. -> DP
public class bj5557 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 3 ≤ N ≤ 100
        int[] nums = new int[n + 1]; 
        for(int i = 1; i <= n; i++) nums[i] = sc.nextInt(); // 0 ~ 9

        // [a][b] : a번째까지 연산했을때, 결괏값이 b인 가지 수
        long[][] dp = new long[n + 1][21];
        dp[1][nums[1]] = 1;

        // 조건1. 중간 결괏값 0 미만 20 초과는 패스
        for(int idx = 2; idx < n; idx++) {
            for(int val = 0; val <= 20; val++) {
                if(dp[idx - 1][val] == 0) continue; // 경우의 수 없으면 패스

                if(val + nums[idx] <= 20) dp[idx][val + nums[idx]] += dp[idx - 1][val];
                if(val - nums[idx] >= 0) dp[idx][val - nums[idx]] += dp[idx - 1][val];
            }
        }

        // 조건2. 마지막 수와 결괏 값 같아야 함.
        long ret = dp[n - 1][nums[n]];
        System.out.println(ret);
    }
}
