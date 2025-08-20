import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// bfs 거리
public class s7733 {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            int max_deli = 0;
            for(int r=0; r<n; r++){
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<n; c++){
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if(map[r][c] > max_deli) max_deli = map[r][c];
                }
            }

            int ret = 0;
            for(int i=0; i<=max_deli; i++){
                visited = new boolean[n][n];
                int mass = 0;
                for(int r=0; r<n; r++){
                    for(int c=0; c<n; c++){
                        if(!visited[r][c] && map[r][c] > i) {
                            dfs(r, c, i);
                            mass++;
                        }
                    }
                }
                if(mass > ret) ret = mass;
            }

            System.out.println("#"+tc+" "+ret);
        }
    }

    static void dfs(int y, int x, int limit){
        visited[y][x] = true;

        for(int i=0; i<4; i++){
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(ny<0 || nx<0 || ny>=n || nx>=n || visited[ny][nx] || map[ny][nx] <= limit) continue;
            dfs(ny, nx, limit);
        }
    }
}
