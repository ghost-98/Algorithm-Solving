import java.util.*;
import java.io.*;

// 다익스트라인데, 중간에 특정 지점 경유하는가?
// 1 -> v == 1 -> p + p -> v
public class bj2211 {
    static int v;
    static List<List<int[]>> graph;
    static int[] dist;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i = 0; i < v + 1; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[] {b, c});
            graph.get(b).add(new int[] {a, c});
        }

        // 로직
        int shortest = dijk(1, v);
        int shortestThroughP = dijk(1, p) + dijk(p, v);
        
        if(shortest == shortestThroughP) System.out.print("SAVE HIM");
        else System.out.print("GOOD BYE");
    }

    public static int dijk(int start, int end) {
        dist = new int[v + 1];
        for(int i = 1; i <= v; i++) dist[i] = Integer.MAX_VALUE;
        dist[start] = 0;

        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[] {start, 0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];

            for(int[] next : graph.get(curNode)) {
                int nextNode = next[0];
                int cost = next[1];
                if(dist[nextNode] > dist[curNode] + cost) {
                    dist[nextNode] = dist[curNode] + cost;
                    pq.add(new int[] {nextNode, dist[nextNode]});
                }
            }
        }

        return dist[end];
    }
}
