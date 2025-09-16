import java.io.*;
import java.util.*;

// 기존 아이디어 : n <= 100이하라 그리디(교차 전선 수가 가장 많은 전선부터 제거) -> 최적해 보장하지 않음
// 개선 아이디어 : a위치 기준으로 정렬하고, b값으로 LIS 구하기(O(n^2)) -> 최소 제거 개수 : n - maxLIS
public class bj2565x {
    static int n;
    static List<int[]> line;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 100 이하

        int[][] line = new int[n][2];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            line[i][0] = Integer.parseInt(st.nextToken()); // 전깃줄의 위치는 500 이하
            line[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(line, Comparator.comparingInt(o -> o[0]));

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxLIS = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (line[j][1] < line[i][1]) { // 증가하는 경우만 연결
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLIS = Math.max(maxLIS, dp[i]);
        }

        System.out.print(n - maxLIS);
    }
}
