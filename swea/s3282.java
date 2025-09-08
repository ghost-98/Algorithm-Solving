import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// n 최대 100 + 테스트케이스 => 부분집합으로는 불가
public class s3282 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=t; tc++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 1≤N≤100
            int k = Integer.parseInt(st.nextToken()); // 1≤K≤1000

            int[][] objects = new int[n+1][2];
            for(int i=1; i<=n; i++){
                st = new StringTokenizer(br.readLine());
                objects[i][0] = Integer.parseInt(st.nextToken());
                objects[i][1] = Integer.parseInt(st.nextToken());                
            }

            // dp[i][w] = i번째 물건까지 고려했을 때, 무게 w에서 얻을 수 있는 최대 가치
            int[][] dp = new int[n+1][k+1];

            for(int i=1; i<=n; i++){
                int weight = objects[i][0];
                int value = objects[i][1];
                for(int w=0; w<=k; w++){
                    // i번째 물건을 담지 않는 경우
                    dp[i][w] = dp[i-1][w];
                    // i번째 물건을 담을 수 있는 경우
                    if(w >= weight){
                        dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weight] + value);
                    }
                }
            }

            int ret = dp[n][k];

            System.out.println("#"+tc+" "+ret);
        }
    }
}