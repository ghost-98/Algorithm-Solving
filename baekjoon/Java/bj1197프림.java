import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
// 정점의 범위가 간선 범위보다 작으므로 프림이 나을 듯
// unionFind 대신 boolean[] visited와 priorityQueue
// 간선을 인접행렬에 담았는데 메모리 초과 -> LIST<LIST<int[]>> 인접리스트로
// 배운점 list중첩했는데 속 list는 선언을 해줘야 하구나
public class bj1197프림 {
    static List<List<int[]>> edge;
    static boolean[] visited;
    static PriorityQueue<int[]> vertexEdge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        edge = new ArrayList<>(v+1);
        for (int i=0; i<=v; i++) edge.add(new ArrayList<>()); // 해줘야함

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edge.get(a).add(new int[] {b, c});
            edge.get(b).add(new int[] {a, c});
        }

        visited = new boolean[v+1];
        vertexEdge = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        ); // 우선순위 큐 Comparator 정의
        // 첫 정점. 아무거나 넣어도 됨
        visited[1] = true;
        for (int[] next : edge.get(1)) vertexEdge.add(new int[]{1, next[0], next[1]});

        // 프림 알고리즘 - 정점들의 간선들 우선순위 큐에 넣고 가중치 낮고 visited false인 정점 추가해가기
        int ret = 0;
        for(int vertexCnt=1; vertexCnt<v;){
            // 루트 노드(최소) 꺼내기
            int[] minEdge = vertexEdge.poll();
            int v1 = minEdge[0]; // 1번째 정점이 방문한 정점임
            int v2 = minEdge[1];
            int w = minEdge[2];

            // 연결된 노드면 패스
            if(visited[v2]) continue;

            visited[v2] = true;
            ret += w;
            vertexCnt++;

            // 새로 추가된 노드의 간선들 담기
            for (int[] next : edge.get(v2)) {
                int nv = next[0];
                int nw = next[1];
                if (!visited[nv]) {
                    vertexEdge.add(new int[]{v2, nv, nw});
                }
            }
        }
  
        System.out.print(ret);
    }
}
