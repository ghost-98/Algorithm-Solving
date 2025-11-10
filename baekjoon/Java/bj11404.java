import java.io.*;
import java.util.*;

// 입력 값 범위 (100 이하)와 문제 요구조건 -> 플로이드워셜 생각
// 고려 1 : 같은 노선의 여러 입력 중 최소 비용
// 고려 2 : MAX_VALUE가 아닌거 확인하는 조건
public class bj11404 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] bus = new int[n + 1][n + 1];
        for(int i = 0; i < n + 1; i++) Arrays.fill(bus[i], Integer.MAX_VALUE);
        for(int i = 1; i < n + 1; i++) bus[i][i] = 0;

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());   

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            bus[a][b] = Math.min(c, bus[a][b]);
        }

        // 플로이드 워셜
        for(int mid = 1; mid <= n; mid++) {
            for(int start = 1; start <= n; start++) {
                for(int end = 1; end <= n; end++) {
                    if(bus[start][mid] != Integer.MAX_VALUE && bus[mid][end] != Integer.MAX_VALUE && bus[start][end] > bus[start][mid] + bus[mid][end]) {
                        bus[start][end] = bus[start][mid] + bus[mid][end];
                    }
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(bus[i][j] == Integer.MAX_VALUE) sb.append(0);
                else sb.append(bus[i][j]);
                sb.append(" ");
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}