import java.util.Scanner;
public class s3307 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){
            int n = sc.nextInt(); // 1 ≤ N ≤ 1,000

            int[] arr = new int[n+1];
            int[] dp = new int[n+1];

            for(int i=1; i<=n; i++) arr[i] = sc.nextInt();

            dp[1] = 1;
            for(int i=2; i<=n; i++){
                int beforeMax = 0;
                for(int j=0; j<i; j++){
                    if(arr[j] >= arr[i]) continue; // 값 작으면서 dp제일 큰거

                    beforeMax = Math.max(dp[j], beforeMax);
                }
                dp[i] = beforeMax + 1;
            }

            int ret = 0;
            for(int i=1; i<=n; i++) ret = Math.max(ret, dp[i]);

            System.out.println("#"+tc+" "+ret);
        }

        sc.close();
    }
}
