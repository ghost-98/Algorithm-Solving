import java.io.*;
import java.util.*;

public class s5643 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++){
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());

            int[][] dp = new int[n+1][n+1];

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                // a < b
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                dp[a][b] = 1; 
            }

            // 플로이드-워셜
            for(int k = 1; k <= n; k++){
                for(int i = 1; i <= n; i++){
                    for(int j = 1; j <= n; j++){
                        if(dp[i][k] == 1 && dp[k][j] == 1) dp[i][j] = 1;
                        if(dp[i][k] == 2 && dp[k][j] == 2) dp[i][j] = 2;
                    }
                }
            }

            int ret = 0;
            for(int i = 1; i <= n; i++){
                boolean flag = true;
                for(int j = 1; j <= n; j++){
                    if(dp[i][j] == 0){
                        flag = false;
                        break;
                    }
                }
                if(flag) ret++;
            }

            System.out.println("#" + tc + " " + ret);
        }
    }
}
/*
기존 코드 - 한 번만 지나가면서 갱신 함. n^3의 3중 for문 플로이드 워셜 사용

for(int j = 1; j <= n; j++){
    if(dp[a][j] == 2) dp[b][j] = 2;
    if(dp[b][j] == 1) dp[a][j] = 1;
}
*/