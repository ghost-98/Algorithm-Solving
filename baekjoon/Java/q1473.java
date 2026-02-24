import java.util.Scanner;

public class q1473 {
    static int N, M, K;
    static int[][] graph;
    static boolean[][] visited;
    static int maxFood = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            int r = sc.nextInt() - 1; // 0-index
            int c = sc.nextInt() - 1;
            graph[r][c] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 1 && !visited[i][j]) {
                    maxFood = Math.max(maxFood, dfs(i, j));
                }
            }
        }

        System.out.println(maxFood);
    }

    static int dfs(int x, int y) {
        visited[x][y] = true;
        int count = 1;

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if (graph[nx][ny] == 1 && !visited[nx][ny]) {
                    count += dfs(nx, ny);
                }
            }
        }

        return count;
    }
}