import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 15m, 유니온 파인드 (그래프 사이클)
public class bj20040 {
    static int n, m;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 3 <= n <= 500000
        m = Integer.parseInt(st.nextToken()); // 3 <= m <= 1000000

        parent = new int[n];
        Arrays.fill(parent, -1);

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(union(x, y)){
                System.out.print(i+1);
                return;
            }
        }
        System.out.print(0);
        return;
    }

    static int find(int x){
        if(parent[x] == -1) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    static boolean union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) return true;
        else parent[rootY] = rootX;
        return false;
    }
}
