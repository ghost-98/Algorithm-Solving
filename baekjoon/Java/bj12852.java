import java.util.Arrays;
import java.util.Scanner;
// 1 -> N 방향 + dp 배열 최솟값 갱신 시 전 인덱스 저장 갱신
public class bj12852 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n + 1];
        int[] prev = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE); // dp 최솟값 갱신 위해
        dp[1] = 0;

        for(int i=1; i<n; i++){
            // + 1 연산
            if(dp[i + 1] > dp[i] + 1){
                dp[i + 1] = dp[i] + 1; 
                prev[i + 1] = i;
            }

            // * 2 연산
            if(n >= i * 2 && dp[i * 2] > dp[i] + 1) {
                dp[i * 2] = dp[i] + 1;
                prev[i * 2] = i;
            }

            // * 3 연산
            if(n >= i * 3 && dp[i * 3] > dp[i] + 1) {
                dp[i * 3] = dp[i] + 1;
                prev[i * 3] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[n]).append("\n");

        int idx = n;
        while(idx != 0){
            sb.append(idx).append(" ");
            idx = prev[idx];
        }        
        System.out.print(sb);
    }
}
