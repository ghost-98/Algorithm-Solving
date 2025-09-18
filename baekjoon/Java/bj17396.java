import java.util.*;
import java.io.*;
// 27m
// 다 잘 짰는데, visible 확인 로직에서 nextNode != n - 1 처리를 빠뜨림.. 그리고 if문 &&의 앞 뒤 순서 + 자료형 범위
public class bj17396 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 100,000
        int m = Integer.parseInt(st.nextToken()); // 1 ≤ M ≤ 300,000 

        boolean[] visible = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int visibleVal = Integer.parseInt(st.nextToken());
            visible[i] = (visibleVal == 1);
        }

        List<List<int[]>> edge = new ArrayList<>();
        for(int i = 0; i < n; i++) edge.add(new ArrayList<>());
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 0 ≤ a, b < N
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken()); // 1 ≤ t ≤ 100,000

            edge.get(a).add(new int[] {b, t});
            edge.get(b).add(new int[] {a, t});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((e1, e2) -> Long.compare(e1[1], e2[1]));
        pq.add(new long[] {0, 0});

        while(!pq.isEmpty()) {
            long[] cur = pq.poll();
            int curNode = (int) cur[0];
            long curDist = cur[1];

            if(dist[curNode] < curDist) continue;

            for(int[] next : edge.get(curNode)) {
                int nextNode = next[0];
                int cost = next[1];

                if(visible[nextNode] && nextNode != n - 1) continue; // 시야 판단만 다익스트라에 추가
                if(dist[nextNode] <= dist[curNode] + cost) continue;

                dist[nextNode] = dist[curNode] + cost;
                pq.add(new long [] {nextNode, dist[nextNode]});
            }
        }

        long  ret = dist[n - 1];
        if(dist[n - 1] == Long.MAX_VALUE) ret = -1;
        System.out.print(ret);
    }
}
