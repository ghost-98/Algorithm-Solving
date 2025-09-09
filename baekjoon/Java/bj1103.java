import java.util.*;
import java.io.*;
// 25m
public class bj1103 {
    static int n, m, ret;
    static char[][] board;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static boolean[][] visited;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // <= 50
        m = Integer.parseInt(st.nextToken()); // <= 50

        board = new char[n][m];
        for(int r = 0; r < n; r++){
            String col = br.readLine();
            for(int c = 0; c < m; c++) board[r][c] = col.charAt(c);
        }


        dp = new int[n][m];         // 메모이제이션
        visited = new boolean[n][m]; // 사이클 탐지용
        
        int ret = dfs(0, 0);
        System.out.println(ret);
    }

    static int dfs(int y, int x) {
        // 범위 밖이거나 구멍(H)인 경우: 이동 불가
        if (y < 0 || x < 0 || y >= n || x >= m || board[y][x] == 'H') return 0;

        // 방문 도중 다시 방문 → 사이클
        if (visited[y][x]) {
            System.out.println(-1);
            System.exit(0);
        }

        // 이미 계산한 값이 있으면 반환
        if (dp[y][x] != 0) return dp[y][x];

        visited[y][x] = true;
        int move = board[y][x] - '0';

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i] * move;
            int nx = x + dx[i] * move;
            dp[y][x] = Math.max(dp[y][x], dfs(ny, nx) + 1);
        }

        visited[y][x] = false; // 원복
        return dp[y][x];
    }

    /* dp 메모이제이션 없는 dfs
    static void dfs(int y, int x, int cnt){
        visited[y][x] = true;

        for(int i=0; i<4; i++){
            int ny = y + dy[i] * (board[y][x] - '0');
            int nx = x + dx[i] * (board[y][x] - '0');
            // 동전이 밖으로 나가거나 구멍에 빠질때 끝.
            if (ny < 0 || nx < 0 || ny >= n || nx >= m || board[ny][nx] == 'H') {
                ret = Math.max(ret, cnt + 1);
                continue;
            }
            // 동전 무한하게 움직일 때
            if (visited[ny][nx]) {
                System.out.println(-1); 
                System.exit(0); // 프로그램 종료
            }
            dfs(ny, nx, cnt + 1);
        }

        visited[y][x] = false; // 원복
    }
    */
}