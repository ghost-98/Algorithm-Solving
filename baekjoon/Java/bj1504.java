import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.Arrays;
import java.util.PriorityQueue;

// 무방향 그래프 + 인접 행렬

// 아이디어 1. 다익스트라 dist 하면서 두개 거쳤는지 체크?
// 아이디어 2. 둘 중 하나
// 1 -> v1 -> v2 -> n
// 1 -> v2 -> v1 -> n
// 이대로 하려면 1, v1, v2 세 점을 출발점으로 다익스트라 쓴 후 종합해서 비교
public class bj1504 {
    static int n, e;
    static int[][] edge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken()); // 2 <= n <= 800
        e = Integer.parseInt(st.nextToken()); // 0 <= e <= 200,000

        // 정점 개수가 간선 개수에 비해 적어서 인접 행렬
        edge = new int[n+1][n+1];
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); // 1 ≤ c ≤ 1,000

            edge[a][b] = c;
            edge[b][a] = c;
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] dist1 = new int[n + 1];
        int[] distV1 = new int[n + 1];
        int[] distV2 = new int[n + 1];

        dist1 = dij(1);
        distV1 = dij(v1);
        distV2 = dij(v2);

        int ret1 = dist1[v1] + distV1[v2] + distV2[n];
        int ret2 = dist1[v2] + distV2[v1] + distV1[n];

        int ret = Math.min(ret1, ret2);
        if((judgeIntegerMax(dist1[v1]) || judgeIntegerMax(distV1[v2]) || judgeIntegerMax(distV2[n])) && (judgeIntegerMax(dist1[v2]) || judgeIntegerMax(distV2[v1]) || judgeIntegerMax(distV1[n]))) ret = -1;

        System.out.println(ret);
    }

    static boolean judgeIntegerMax(int n) {
        if(n == Integer.MAX_VALUE) return true;
        else return false;
    }

    static int[] dij(int startNode) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        pq.add(new int[] {startNode, 0}); // 노드, 거리
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int weight = cur[1];

            // 기존 거리가 새로 갱신된 거리보다 길면 continue지?
            if(dist[curNode] < weight) continue;

            for(int next = 1; next <= n; next++) {
                if(edge[curNode][next] == 0) continue;
                if(dist[next] <= dist[curNode] + edge[curNode][next]) continue;

                dist[next] = dist[curNode] + edge[curNode][next];
                pq.add(new int[] {next, dist[next]});
            }
        }

        return dist;
    }
}