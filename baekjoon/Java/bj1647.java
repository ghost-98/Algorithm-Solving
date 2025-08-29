import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// mst 구한 뒤에 mst 내에서 가장 가중치 큰 간선 하나만 뺀 값
// 크루스칼
public class bj1647 {
    static int[] parent;
    static List<int[]> edge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        edge = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edge.add(new int[] {a, b, cost});
        }
        edge.sort((e1, e2) -> e1[2]-e2[2]);

        parent = new int[n+1];
        for(int i=1; i<=n; i++) parent[i] = i;

        int ret = 0;
        for(int edgeIdx=0, edgeCnt=0; edgeCnt<n-2; edgeIdx++){ // n-2개 간선 고르면 답
            int v1 = edge.get(edgeIdx)[0];
            int v2 = edge.get(edgeIdx)[1];
            int cost = edge.get(edgeIdx)[2];
            if(find(v1) == find(v2)) continue;

            union(v1, v2);
            edgeCnt++;
            ret += cost;
        }

        System.out.print(ret);
    }

    static public int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) parent[rootY] = parent[rootX];
    }
}

