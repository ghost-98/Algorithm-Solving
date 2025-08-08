import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s5215 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            // 재료의 맛과 칼로리
            int[][] ingredient = new int[n][2];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());

                ingredient[i][0] = Integer.parseInt(st.nextToken());
                ingredient[i][1] = Integer.parseInt(st.nextToken());
            }

            int ret = 0;
            for(int i=1; i<(1<<n); i++){
                int[] tmpRet = {0, 0}; // [0]은 score, [1] calorie
                for(int j=0; j<n ; j++){
                    if((i & (1<<j)) == 1<<j){
                        tmpRet[0] += ingredient[j][0];
                        tmpRet[1] += ingredient[j][1];
                    }
                }
                if(tmpRet[1] <= l && tmpRet[0] > ret) ret = tmpRet[0];
            }

            System.out.println("#" + tc + " " + ret);
        }
    }
}
// 비트마스킹으로 풀었는데 조합(백트래킹 - dfs)로 풀 수도 있음
// 조합 1방법, 부분집합 1방법으로 풀어보기