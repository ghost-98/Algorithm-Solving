import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 무방향 그래프 + 인접 행렬
public class bj1504 {
    static int n, e;
    static int[][] edge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); // 2 <= n <= 800
        int e = Integer.parseInt(st.nextToken()); // 0 <= e <= 200,000

        // 정점 개수가 간선 개수에 비해 적어서 인접 행렬
        edge = new int[n+1][n+1];
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edge[a][b] = c;
            edge[b][a] = c;
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        
    }    
}
