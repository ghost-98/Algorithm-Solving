import java.io.*;
import java.util.*;
// 30m
// 테케 2번 5번 같이 히든테케를 알려줘서 디버깅 가능했음..
public class bj7576 {
    static int n, m;
    static int[] dy = {-1, 0 , 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); // 2 ~ 1000
        n = Integer.parseInt(st.nextToken()); // 2 ~ 1000

        // 필요한 것들 세팅
        int[][] tomato = new int[n][m];
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        int unripeTomatoCnt = 0;

        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < m; c++) {
                tomato[r][c] = Integer.parseInt(st.nextToken());
                if(tomato[r][c] == 1) {
                    q.add(new int[] {r, c}); // 초기 bfs 시작점들
                    visited[r][c] = true;
                }
                else if(tomato[r][c] == 0) unripeTomatoCnt++;
            }
        }

        // 히든 테케
        if(unripeTomatoCnt == 0) {
            System.out.print(0); return;
        }

        // bfs
        int dayCnt = 1, todayCnt = q.size(), tomorrowCnt = 0;
        while(unripeTomatoCnt > 0 && !q.isEmpty()) {
            if(todayCnt == 0) {
                dayCnt++; todayCnt = tomorrowCnt; tomorrowCnt = 0;
            }

            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            todayCnt--;

            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx] || tomato[ny][nx] == -1) continue;

                q.add(new int[] {ny, nx});
                tomorrowCnt++;
                visited[ny][nx] = true;

                unripeTomatoCnt--;
            }
        }

        if(unripeTomatoCnt != 0) dayCnt = -1;
        System.out.print(dayCnt);
    }
}
