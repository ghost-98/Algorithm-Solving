import java.io.*;
import java.util.*;

// n 크기 때문에 인접 행렬이 유리한가?
// 우선순위 큐 기반 다익스트라는 visited 불필요
public class bj1916 {
    static int n, m;
    static List<List<int[]>> bus;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 1 ≤ N ≤ 1,000
        m = Integer.parseInt(br.readLine()); // 1 ≤ M ≤ 100,000

        bus = new ArrayList<>();
        for (int i=0; i<=n; i++) bus.add(new ArrayList<>());

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            bus.get(start).add(new int[] {end, cost});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        dij(start);
        System.out.println(distance[end]);
    }

    static void dij(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1]-b[1]);
        pq.add(new int[]{start, 0});
        distance[start] = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int city = now[0];
            int cost = now[1];

            if (distance[city] < cost) continue; // 이미 더 짧은 경로 있음

            for (int[] next : bus.get(city)) {
                int nextCity = next[0];
                int nextCost = cost + next[1]; // cost : 지금까지의 비용

                if (distance[nextCity] > nextCost) {
                    distance[nextCity] = nextCost;
                    pq.add(new int[]{nextCity, nextCost});
                }
            }
        }
    }
}
