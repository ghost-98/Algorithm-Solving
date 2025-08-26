import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 39m, 크루스칼 알고리즘
    // 간선 담을 배열 c기준 정렬 -> 정렬 방법 comparator와 sort()
    // for문 호출문과 조건문 저렇게 쓰는 것도 있구나..
public class bj1197크루스칼한번더 {
    static int[] parent;
    static int[][] edge;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken()); // v <= 10000
        int e = Integer.parseInt(st.nextToken()); // e <= 100000 정렬 하려면 nlog n인 정렬
        
        edge = new int[e][3];
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1] = Integer.parseInt(st.nextToken());
            edge[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(edge, (e1, e2) -> e1[2] - e2[2]); // edge가 배열이면 Arrays.sort(), 리스트면 리스트명.sort()

        // 루트 없으므로 자기 자신 가리키게
        parent = new int[v+1];
        for(int i=1; i<=v; i++) parent[i] = i;

        int ret = 0;
        // 이런 형태의 반복문..!!
        for(int edgeIdx=0, edgeCnt=0; edgeCnt < v-1 && edgeIdx < e; edgeIdx++){
            int a = edge[edgeIdx][0];
            int b = edge[edgeIdx][1];
            int c = edge[edgeIdx][2];

            int rootA = find(a);
            int rootB = find(b);

            if(rootA == rootB) continue;

            union(rootA, rootB);
            ret += c;
            edgeCnt++;
        }

        System.out.print(ret);
    }

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 재귀 및 최적화
    }

    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return;
        parent[rootY] = rootX;
    }
}