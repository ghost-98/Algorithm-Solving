import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 벡터 배열 잘쓰기!! (훨씬 간단하게 구현 가능)
// 최단거리 bfs + dfs로 풀려면 기본으론 안되는 이유 알고 고민
public class s1953한번더 {
    static int n, m, l, ret;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> q;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int[][] pipeTypeDir = { // 0,1,2,3은 상하좌우
        {},                 
        {0, 1, 2, 3},       
        {0, 1},
        {2, 3},
        {0, 3},
        {1, 3},
        {1, 2},
        {0, 2}
    };

    static int[][] canConnect = { // 방향마다 연결 가능한 파이프 타입
        {1, 2, 5, 6},
        {1, 2, 4, 7},
        {1, 3, 4, 5},
        {1, 3, 6, 7}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            map = new int[n][m];
            for(int row=0; row<n; row++){
                st = new StringTokenizer(br.readLine());
                for(int col=0; col<m; col++){
                    map[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            
            visited = new boolean[n][m];
            q = new LinkedList<>();

            visited[r][c] = true;
            q.add(new int[]{r, c, 1});
            ret = 1;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int y = cur[0], x = cur[1], depth = cur[2];
                if (depth == l) continue;

                for (int dir : pipeTypeDir[map[y][x]]) {
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];
                    if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                    if (visited[ny][nx] || map[ny][nx] == 0) continue;

                    // 연결 체크
                    for (int tp : canConnect[dir]) {
                        if (tp == map[ny][nx]) {
                            visited[ny][nx] = true;
                            ret++;
                            q.add(new int[]{ny, nx, depth + 1});
                        }
                    }
                }
            }

            System.out.println("#"+tc+" "+ret);
        }
    }
}
