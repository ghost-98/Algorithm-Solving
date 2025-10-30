import java.io.*;
import java.util.*;

/**
 * <처음 접근>
 * 1. 완탐 재귀 ..? but 돌아가야 최소일수도 있으니 4방 탐색
 * 2. 그리디 불가능
 * 3. dp ..?
 * -> {우, 하} 옵션만 있는 완탐 재귀로 풂..
 * <풀이..>
 * 다익스트라
 */
public class bj1249 {
    static int n, ret;
    static int[][] map, dist;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine()); // 1 ~ 100
            map = new int[n][n];
            dist = new int[n][n];

            for (int row = 0; row < n; row++) {
                String tmp = br.readLine();
                for (int col = 0; col < n; col++) {
                    map[row][col] = tmp.charAt(col) - '0';
                    dist[row][col] = Integer.MAX_VALUE;
                }
            }

            System.out.println("#" + tc + " " + dijk());
        }
    }

    static int dijk() {
        // cost 기준 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[] { 0, 0, 0 }); // y, x, cost
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int y = cur[0], x = cur[1], cost = cur[2];

            if (dist[y][x] < cost)
                continue;
            if (y == n - 1 && x == n - 1)
                return cost;

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n)
                    continue;

                int newCost = cost + map[ny][nx];
                if (newCost < dist[ny][nx]) {
                    dist[ny][nx] = newCost;
                    pq.offer(new int[] { ny, nx, newCost });
                }
            }
        }
        return dist[n - 1][n - 1];
    }
}