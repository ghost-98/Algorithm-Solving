import java.util.*;
import java.io.*;

public class s1494x {
    static int[] x, y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());

            x = new int[n];
            y = new int[n];
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
            }

            long[][] dist = new long[n][n];
            // 플로이드 워셜 한 후 나온 int[][]로 dfs로 확인
            for(int a = 0; a < n / 2 - 1; a++) {
                for(int b = a + 1; b < n / 2; b++) {
                    dist[a][b] = (x[a] - x[b]) * (x[a] - x[b]) + (y[a] - y[b]) * (y[a] - y[b]);
                    // dist[b][a] = dist[a][b];
                }
            }

            long ret = Long.MAX_VALUE;


            System.out.println("#" + tc + " " + ret);
        }
    }
}
