import java.util.*;
import java.io.*;

// 40m
// 비효율 최적화 고민 : 매번 bfs 새로 수행 + 녹는 치즈 체크 방식
public class bj2638 {
    static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 5 ≤ N ≤ 100
        int m = Integer.parseInt(st.nextToken()); // 5 ≤ M ≤ 100

        boolean[][] isCheese = new boolean[n][m];
        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < m; col++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1)
                    isCheese[row][col] = true;
            }
        }

        int dayCnt = 0;
        while (true) {
            boolean[][] visited = new boolean[n][m];
            visited[0][0] = true;

            Queue<int[]> q = new ArrayDeque<>();
            Queue<int[]> outsideCheese = new ArrayDeque<>();
            q.add(new int[] { 0, 0 });

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int cy = cur[0];
                int cx = cur[1];

                for (int i = 0; i < 4; i++) {
                    int ny = cy + dy[i];
                    int nx = cx + dx[i];
                    if (ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx])
                        continue;
                    if (isCheese[ny][nx]) {
                        outsideCheese.add(new int[] { ny, nx });
                    } else {
                        visited[ny][nx] = true;
                        q.add(new int[] { ny, nx });
                    }
                }
            }

            if (outsideCheese.isEmpty())
                break;

            // 녹임 로직
            while (!outsideCheese.isEmpty()) {
                int[] cur = outsideCheese.poll();
                int cy = cur[0];
                int cx = cur[1];
                // 두 면 체크 로직
                int outsideCnt = 0;
                for (int i = 0; i < 4; i++) {
                    int ny = cy + dy[i];
                    int nx = cx + dx[i];
                    if (visited[ny][nx] && !isCheese[ny][nx])
                        outsideCnt++;
                }

                if (outsideCnt >= 2)
                    isCheese[cy][cx] = false;
            }

            dayCnt++;
        }

        System.out.print(dayCnt);
    }
}
