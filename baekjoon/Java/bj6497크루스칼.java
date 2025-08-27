import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 12m
// 크루스칼 가중치 낮은 것부터 + unionFind
public class bj6497크루스칼 {
    static int[] parent;
    static int[][] edge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if(n==0 && m==0) break;

            parent = new int[m+1];
            for(int i=1; i<=m; i++) parent[i] = i;

            edge = new int[n][3];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                edge[i][0] = Integer.parseInt(st.nextToken());
                edge[i][1] = Integer.parseInt(st.nextToken());
                edge[i][2] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(edge, (e1, e2) -> Integer.compare(e1[2], e2[2])); // n <= 200000인데 시간 복잡도 괜찮나

            long ret = 0;
            for(int[] e : edge){
                int a = e[0];
                int b = e[1];

                if(find(a) == find(b)){
                    ret += e[2];
                    continue;
                }
                union(a, b);

            }

            System.out.println(ret);
        }
    }

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) parent[rootY] = rootX;
    }
}
