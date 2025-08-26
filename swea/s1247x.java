import java.util.Scanner;
// 백트래킹과 캐싱이 주효한 문제
// dp로 가능하다고 함
public class s1247x {
    static int n, ret;
    static int[][] pos;
    static int[][] dist;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = sc.nextInt();

            // 집 + 고객 n명 + 회사
            pos = new int[n + 2][2];
            visited = new boolean[n + 2];

            pos[0][0] = sc.nextInt();
            pos[0][1] = sc.nextInt();

            pos[n + 1][0] = sc.nextInt();
            pos[n + 1][1] = sc.nextInt();

            for (int i = 1; i <= n; i++) {
                pos[i][0] = sc.nextInt();
                pos[i][1] = sc.nextInt();
            }

            // 거리 캐싱
            dist = new int[n + 2][n + 2];
            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    dist[i][j] = Math.abs(pos[i][0] - pos[j][0]) + Math.abs(pos[i][1] - pos[j][1]);
                }
            }

            ret = Integer.MAX_VALUE;
            perm(0, 0, 0);
            System.out.println("#" + tc + " " + ret);
        }
    }

    static void perm(int before, int depth, int sum) {
        // 가지치기
        if (sum >= ret) return;

        // 모든 고객 방문했으면 회사로 이동
        if (depth == n) {
            ret = Math.min(ret, sum + dist[before][n + 1]);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            perm(i, depth + 1, sum + dist[before][i]);
            visited[i] = false;
        }
    }
}
