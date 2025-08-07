import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class s2001 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] area = new int[n][n];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    area[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ret = 0;
            for(int i=0; i<=n-m; i++){
                for(int j=0; j<=n-m; j++){
                    int tmp = 0;
                    for(int k=i; k<i+m; k++){
                        for(int l=j; l<j+m; l++){
                            tmp += area[k][l];
                        }
                    }
                    if(ret<tmp) ret = tmp;
                }
            }

            System.out.println("#" + tc + " " + ret);
        }
    }
}