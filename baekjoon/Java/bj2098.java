import java.util.*;
import java.io.*;
// 5:13
// 처음 아이디어 : 재귀(dfs) + dp -> 흠 가지치기 안되는지? 그리고 아마 아닌거 같아요
// 생각 : MST 쓰기엔 돌아오질 않잖아. DP 써야하나
// 개선 아이디어 : ..? 어떻게 푸냐
public class bj2098 {
    static int n, ret;
    static int[][] w;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 2 ≤ N ≤ 16
        w = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) w[i][j] = Integer.parseInt(st.nextToken()); // 1,000,000 이하 양수
        }

        ret = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) { // 시작 지점마다 dfs 해보기
            visited = new boolean[n + 1];
            // visited[i] = true;
            dfs(0, i, 0);
        }

        System.out.print(ret);
    }

    static void dfs(int depth, int prev, int cost) {
        if(depth == n) { // 첫 원소랑 연결
            ret = Math.min(ret, cost);
            return;
        }

        // 다 돌 수 없는 애들은 가지치기가 안되네, 끝까지 가서 알아서 소멸해야함
        for(int i = 1; i <= n; i++) {
            if(visited[prev] || w[prev][i] == 0) continue;

            visited[i] = true;
            dfs(depth + 1, i, cost + w[prev][i]);
            visited[i] = false;
        }
    }
} 