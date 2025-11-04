import java.util.Scanner;

// 고민없는 첫 생각: dfs 한번 돌린 후 R -> B로 대체한 후 DFS
// isBlue() 메서드 만들어서 dfs하나 더 만드는 헛수고 함 -> pic 자체를 바꾸고 dfs 하나로 사용
public class bj10026 {
    static int n;
    static char[][] pic;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        pic = new char[n][n];
        
        for(int r = 0; r < n; r++) {
            String s = sc.next();
            for(int c = 0; c < n; c++) pic[r][c] = s.charAt(c);
        }

        // dfs
        int cnt1 = 0, cnt2 = 0;

        visited = new boolean[n][n];
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(!visited[r][c]) {
                    dfs(r, c);
                    cnt1++;
                }
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (pic[r][c] == 'G') pic[r][c] = 'R';
            }
        }

        visited = new boolean[n][n];
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(!visited[r][c]) {
                    dfs(r, c);
                    cnt2++;
                }
            }
        }

        System.out.print(cnt1 + " " + cnt2);
    }

    public static void dfs(int cy, int cx) {
        visited[cy][cx] = true;

        for(int i = 0; i < 4; i++) {
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx] || pic[cy][cx] != pic[ny][nx]) continue;
            dfs(ny, nx);
        }
    }
}
