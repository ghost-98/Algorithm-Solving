import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
// 정점의 범위가 간선 범위보다 작으므로 프림이 나을 듯

/* 배운점
 * UnionFind 위한 int[] parent 루트 없으므로 자기 자신 가리키게
 * for 반복문에서 초기식, 조건식, 증감식 여러개, 다채롭게 표현 가능
 * 정렬 방법 comparator와 sort()
 */
public class bj1197크루스칼 {
    static int[] parent;
    static List<int[]> edge;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        // 간선의 두 정점과 가중치를 리스트에 담음
        edge = new ArrayList<>();
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edge.add(new int[] {a, b, c});
        }
        // 리스트 가중치 오름차순으로 정렬
        edge.sort((e1, e2) -> Integer.compare(e1[2], e2[2]));

        parent = new int[v+1];
        for(int i=1; i<=v; i++) parent[i] = i;

        // 크루스칼 - 사이클을 만들지 않는 가중치 낮은 간선부터 v-1개
        int ret = 0;
        for(int edgeCnt=0, edgeIdx=0; edgeCnt<v-1; edgeIdx++){
            int a = edge.get(edgeIdx)[0];
            int b = edge.get(edgeIdx)[1];
            if(find(a) == find(b)) continue;

            union(a, b);
            ret += edge.get(edgeIdx)[2];
            edgeCnt++;
        }

        System.out.print(ret);
    }

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // rank 최적화 안되어 있음
    static void union(int x, int y){
        int rootX = parent[x];
        int rootY = parent[y];
        if(rootX != rootY) parent[rootX] = rootY; 
    }
}
