import java.io.*;
import java.util.*;
// 24m
// 왜 어렵게 생각해.. 본질을 생각해 뭘 해야하는지 -> 단순 다익스트라
public class bj14284 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 2≤n≤5000
        int m = Integer.parseInt(st.nextToken()); // 1≤m≤100,000

        List<List<int[]>> edge = new ArrayList<>();
        for(int i = 0; i <= n; i++) edge.add(new ArrayList<>());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edge.get(a).add(new int[] {b, c});
            edge.get(b).add(new int[] {a, c});
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        // 다익스트라
        // s에서 출발하든 t에서 출발하든 결과 같을 것. 무방향 그래프라서
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        pq.add(new int[] {s, 0}); // 노드, 거리

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int d = cur[1];

            if(dist[curNode] < d) continue;

            for(int[] next : edge.get(curNode)) {
                int nextNode = next[0];
                int cost = next[1];

                if(dist[nextNode] <= dist[curNode] + cost) continue;

                dist[nextNode] = dist[curNode] + cost;
                pq.add(new int[] {nextNode, dist[nextNode]});
            }
        }

        System.out.println(dist[t]);
    }
}