import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 8m
// MST - 크루스칼
public class bj1922크루스칼 {
    // 크루스칼 위해 필요한 parent 배열(유니온 파인드), 간선 리스트
    static int[] parent, rank; 
    static List<int[]> edge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        edge = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edge.add(new int[] {v1, v2, w});
        }
        edge.sort((e1, e2) -> e1[2]-e2[2]);

        parent = new int[n+1];
        for(int i=0; i<=n; i++) parent[i] = i;

        // rank 배열로 unionFind 최적화
        rank = new int[n+1];

        int ret = 0;
        for(int edgeIdx=0, edgeCnt=0; edgeCnt<n-1; edgeIdx++){
            int v1 = edge.get(edgeIdx)[0];
            int v2 = edge.get(edgeIdx)[1];
            int w = edge.get(edgeIdx)[2];

            if(find(v1) == find(v2)) continue; // 사이클 확인

            union(v1, v2);
            ret += w;
            edgeCnt++;
        }

        System.out.print(ret);
    }

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) return;

        // 이게 rank 최적화 코드 (같지 않으면 루트에 다른 트리 붙이므로 높이 변화x)
        if(rank[rootX] > rank[rootY]){
            parent[rootY] = rootX;
        }else if(rank[rootY] > rank[rootX]){
            parent[rootX] = rootY;
        }else{
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}
