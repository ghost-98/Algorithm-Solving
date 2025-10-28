import java.io.*;
import java.util.*;

// 7m
public class bj2206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 1,000
        int m = Integer.parseInt(st.nextToken()); // 1 ≤ M ≤ 1,000

        boolean[][] wallMovable = new boolean[n + 1][m + 1];
        for (int row = 0; row < n; row++) {
            String rows = br.readLine();

            for (int col = 0; col < m; col++) {
                if (rows.charAt(col) == '0')
                    wallMovable[row][col] = true;
            }
        }

        // 0, 0 -> n-1, m-1 까지 우,하 방향으로만
        // 2차원 DP (+ 차원 1추가 -> 벽깬거 안깬거) or
    }
}
// 벽깨기는 0 or 1
// 이동이 불가하면 -1
