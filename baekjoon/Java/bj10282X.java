import java.util.*;
import java.io.*;

// 헷갈리지 말고 다시 풀어봐
public class bj10282X {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            List<List<int[]>> graph = new ArrayList<>();
            for(int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());

            for(int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph.get(b).add(new int[] {a, s});
            }

            int[] dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[c] = 0;

            // 걸리는 시간 오름차순 정렬
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
            pq.add(new int[] {c, 0});

            while(!pq.isEmpty()) {
                int[] cur = pq.poll();
                int curNode = cur[0];
                int curTime = cur[1];

                if (curTime > dist[curNode]) continue;

                for(int[] next : graph.get(curNode)) {
                    int nextNode = next[0];
                    int nextTime = curTime + next[1];

                    if (nextTime < dist[nextNode]) {
                        dist[nextNode] = nextTime;
                        pq.add(new int[]{nextNode, nextTime});
                    }
                }
            }

            int infectedCnt = 0;
            int maxTime = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    infectedCnt++;
                    maxTime = Math.max(maxTime, dist[i]);
                }
            }

            System.out.println(infectedCnt + " " + maxTime);
        }
    }
}
