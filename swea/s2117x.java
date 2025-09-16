import java.util.*;
import java.io.*;
// 기존 : 방범 영역의 중심을 점으로 다 순회하려고 함. 완탐
// 아이디어 : 마름모는 곧 맨해튼 거리 -> 생각하고 설계를 해야해!! + 이 문제는 아니지만 완탐시 집을 중심으로 생각해야 할 때도

public class s2117x {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // T <= 50
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 5 ≤ N ≤ 20
            int m = Integer.parseInt(st.nextToken()); // 1 ≤ M ≤ 10

            int[][] city = new int[n][n];
            List<int[]> house = new ArrayList<>();
            for (int r = 0; r < n; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < n; c++) {
                    city[r][c] = Integer.parseInt(st.nextToken());
                    if(city[r][c] == 1) house.add(new int[] {r, c});
                }
            }

            int ret = 0;
            for(int r = 0; r < n; r++) {
                for(int c = 0; c < n; c++) {
                    // k의 범위도 r, c마다 다르고, 애초에 r, c 완탐 해야 하는지
                    for(int k = 0; k < 2 * n - 1; k++) {
                        int cnt = 0;
                        for(int[] h : house) {
                            if(k >= getDistance(r, c, h[0], h[1])) cnt++;
                        }
                        
                        int cost = cnt * m - k * k - (k - 1) * (k - 1);
                        ret = Math.max(ret, cost);
                    }
                }
            }

            System.out.println("#" + tc + " " + ret);
        }
    }
    
    static int getDistance(int y1, int x1, int y2, int x2) {
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }
}
