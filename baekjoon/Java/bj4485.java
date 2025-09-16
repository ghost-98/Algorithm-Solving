import java.io.*;
import java.util.*;
// 23m
public class bj4485 {
    static int[][] cave;
    static int[][] dist;
    static PriorityQueue<int[]> pq;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
    
        int idx = 0;
        while(true) {
            int n = Integer.parseInt(br.readLine()); // 2 ≤ N ≤ 125
            if(n == 0) break;

            cave = new int[n][n];
            for(int r = 0; r < n; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < n; c++) cave[r][c] = Integer.parseInt(st.nextToken()); // 0 ~ 9
            }

            dist = new int[n][n];
            for(int i = 0; i < n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[0][0] = cave[0][0];

            pq = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
            pq.add(new int[] {0, 0, dist[0][0]}); // 위치, 거리

            while(!pq.isEmpty()) {
                int[] cur = pq.poll();
                int curY = cur[0];
                int curX = cur[1];
                int curD = cur[2];

                for(int i = 0; i < 4; i++) {
                    int nextY = curY + dy[i];
                    int nextX = curX + dx[i];
                    if(nextY < 0 || nextX < 0 || nextY >= n || nextX >= n) continue;
                    if(curD + cave[nextY][nextX] >= dist[nextY][nextX]) continue; // visited 역할 함

                    dist[nextY][nextX] = curD + cave[nextY][nextX];
                    pq.add(new int[] {nextY, nextX, dist[nextY][nextX]});
                }
            }

            System.out.println("Problem " + (++idx) + ": " + dist[n-1][n-1]);
        }
    }
}
