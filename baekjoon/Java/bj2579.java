import java.util.Scanner;
public class bj2579 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 300이하
        int[] score = new int[n];
        for(int i=0; i<n; i++) score[i] = sc.nextInt();

        if(n == 1){
            System.out.println(score[0]);
            return;
        }

        int[][] dp = new int[n][2]; // 2번째 변수 0: 전 계단 안 밟음, 1: 전 계단 밟음
        dp[0][0] = score[0]; dp[1][0] = score[1]; dp[1][1] = score[0] + score[1];
        for(int i=2; i<n; i++){
            dp[i][0] = Math.max(dp[i-2][0], dp[i-2][1]) + score[i];
            dp[i][1] = dp[i-1][0] + score[i];
        }

        int ret =  Math.max(dp[n-1][0], dp[n-1][1]);

        System.out.print(ret);
    }
}
