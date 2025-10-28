import java.util.*;
import java.io.*;

// knapsack 문제
public class bj1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken()); // 1 ~ 1000
        int n = Integer.parseInt(st.nextToken()); // 1 ~ 20

        int[][] info = new int[n][2]; // [][0]은 비용 [][1]은 수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken()); // 1 ~ 100
            info[i][1] = Integer.parseInt(st.nextToken()); // 1 ~ 100
        }

        int[] minDP = new int[c + 101];
        for (int i = 1; i < c + 101; i++)
            minDP[i] = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int cost = info[i][0];
            int people = info[i][1];

            for (int j = 0; j < c + 101 - people; j++) {
                if (minDP[j] != Integer.MAX_VALUE) {
                    minDP[j + people] = Math.min(minDP[j + people], minDP[j] + cost);
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int i = c; i < c + 101; i++)
            ret = Math.min(ret, minDP[i]);
        System.out.println(ret);
    }
}