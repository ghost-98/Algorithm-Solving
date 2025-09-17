import java.io.*;
import java.util.*;
// 20m
// 아이디어 : 그리디적으론 불가하고, dp로 해야할듯
// dp 구현 생각이 잘못됨 : 내 기존 로직은 start정렬이 보장 안되서 연속된 지름길이 적용 안됨. 도착 지점 이후 적용 비효율
public class bj1446 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 12이하 자연수
        int d = Integer.parseInt(st.nextToken()); // 10,000 이하 자연수

        List<int[]> shortcut = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine()); // 모든 입력은 0 ~ 10,000
            int start = Integer.parseInt(st.nextToken()); // 시작 위치는 무조건 도착 위치보다 작음
            int end = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            if (end > d) continue; // 도착 지점이 d보다 큰 경우는 배제 (역주행 안되는 조건)

            shortcut.add(new int[] {start, end, len});
        }
        
        int[] dist = new int[d + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        // start 기준 정렬
        shortcut.sort((a, b) -> a[0] - b[0]);

        for (int i = 0; i <= d; i++) {
            // 직선 이동
            if (i < d) dist[i + 1] = Math.min(dist[i + 1], dist[i] + 1);

            // 현재 위치에서 출발하는 지름길 적용
            for (int[] sc : shortcut) {
                if (sc[0] == i) {
                    dist[sc[1]] = Math.min(dist[sc[1]], dist[i] + sc[2]);
                }
            }
        }
        
        System.out.println(dist[d]);
    }
}