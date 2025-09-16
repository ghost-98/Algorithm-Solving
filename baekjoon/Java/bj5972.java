import java.util.*;
import java.io.*;

// 23m
public class bj5972 {
    static int n, m;
    static List<List<int[]>> edge;
    static int[] dist;
    static boolean[] visited;
    static PriorityQueue<int[]> pq;
    public static void main(String[] ars) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 1 <= N <= 50,000
        m = Integer.parseInt(st.nextToken()); // 1 <= M <= 50,000

        edge = new ArrayList<>();
        for(int i = 0; i <= n ; i++) edge.add(new ArrayList<>());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 1 <= A_i <= N
            int b = Integer.parseInt(st.nextToken()); // 1 <= B_i <= N
            int c = Integer.parseInt(st.nextToken()); // 0 <= C_i <= 1,000

            edge.get(a).add(new int[] {b, c});
            edge.get(b).add(new int[] {a, c});
        }

        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        visited = new boolean[n+1];
        pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, 1}); // pq는 거리, 노드 삽입

        while(!pq.isEmpty()) { // 이런식으로 현재 노드와 다음 노드간 관계성
            int[] cur = pq.poll();
            int curDist  = cur[0];
            int node = cur[1];

            if (visited[node]) continue;
            visited[node] = true;

            for (int[] next : edge.get(node)) {
                int nextNode = next[0];
                int weight = next[1];
                if (dist[nextNode] > curDist + weight) {
                    dist[nextNode] = curDist + weight;
                    pq.add(new int[]{dist[nextNode], nextNode});
                }
            }        
        }

        System.out.println(dist[n]);
    }
}
// 인접 리스트 선택 -> 인접 행렬 선택하면 25억칸인데 램 터짐
// 저 구조에서 모든 정점의 최단 거리가 갱신이 됨 (밀리고 그런거 없음)
// 최단거리 알고리즘인데, 가중치 양수이고 플로이드 워셜은 크기 제한때문에 안되고 벨만 포드는 느리고 bfs는 가중치 같지 않아서 패스
// visited 있는 버전은 중복 노드x  visited 없는 버전은 pq 빌 때까지

// for문으로 직접 초기화보다 빠른 메서드. JIT가 런타임 최적화. 가독성도 좋음 / 다차원 배열에서는?
// 기본 화살표 함수와 Comparator의 차이