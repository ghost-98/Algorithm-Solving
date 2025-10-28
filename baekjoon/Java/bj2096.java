import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 12m
// 완탐시 3^10만, 재귀도x -> dp : O(2*N)
public class bj2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 1 ≤ N ≤ 100,000
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine()); // 각각 0 ~ 9
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] maxDp = new int[n][3];
        int[][] minDp = new int[n][3];
        maxDp[0][0] = minDp[0][0] = arr[0][0];
        maxDp[0][1] = minDp[0][1] = arr[0][1];
        maxDp[0][2] = minDp[0][2] = arr[0][2];

        for (int line = 0; line < n - 1; line++) {
            maxDp[line + 1][0] = Math.max(maxDp[line][0], maxDp[line][1]) + arr[line + 1][0];
            maxDp[line + 1][1] = Math.max(Math.max(maxDp[line][0], maxDp[line][1]), maxDp[line][2]) + arr[line + 1][1];
            maxDp[line + 1][2] = Math.max(maxDp[line][1], maxDp[line][2]) + arr[line + 1][2];

            minDp[line + 1][0] = Math.min(minDp[line][0], minDp[line][1]) + arr[line + 1][0];
            minDp[line + 1][1] = Math.min(Math.min(minDp[line][0], minDp[line][1]), minDp[line][2]) + arr[line + 1][1];
            minDp[line + 1][2] = Math.min(minDp[line][1], minDp[line][2]) + arr[line + 1][2];
        }

        int max = Math.max(Math.max(maxDp[n - 1][0], maxDp[n - 1][1]), maxDp[n - 1][2]);
        int min = Math.min(Math.min(minDp[n - 1][0], minDp[n - 1][1]), minDp[n - 1][2]);
        System.out.println(max + " " + min);
    }
}
