import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj14938 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 1 ≤ n ≤ 100
        int m = Integer.parseInt(st.nextToken()); // 1 ≤ m ≤ 15
        int r = Integer.parseInt(st.nextToken()); // 1 ≤ r ≤ 100

        // 각 지역 아이템 수 입력
        int[] itemCnt = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            itemCnt[i] = Integer.parseInt(st.nextToken());
        }

        // 거리 초기화
        int INF = 100_000;
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // 도로 정보 입력
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            // 양방향
            dist[a][b] = Math.min(dist[a][b], l);
            dist[b][a] = Math.min(dist[b][a], l);
        }

        // 플로이드–워셜
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 최대 아이템 수 계산
        int maxItems = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] <= m) {
                    sum += itemCnt[j];
                }
            }
            maxItems = Math.max(maxItems, sum);
        }

        System.out.println(maxItems);
    }
}