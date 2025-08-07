import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class bj1325X {
    // 리스트 배열 혹은 List<List<Integer>> 자바 컬렉션 이용
    public static List<List<Integer>> graph = new ArrayList<>();
    public static boolean[] visited = new boolean[0];
    public static Queue<Integer> q = new ArrayDeque<>();
    public static int[] dp;

    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        for(int i=0; i<n+1; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i=0; i<m; i++){
            String[] input2 = br.readLine().split(" ");
            int x = Integer.parseInt(input2[0]);
            int y = Integer.parseInt(input2[1]);

            graph.get(y).add(x);
        }

        dp = new int[n+1];
        Arrays.fill(dp, -1);
        visited = new boolean[n+1];

        int max_ = 0;
        ArrayList<Integer> retArr = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int ret = dfs(i);
            if (ret > max_) {
                max_ = ret;
                retArr.clear();
                retArr.add(i);
            } else if (ret == max_) {
                retArr.add(i);
            }
        }

        retArr.sort(null);
        for(int i=0; i<retArr.size(); i++){
            System.out.print(retArr.get(i) + " ");
        }
    }

    static int dfs(int n) {
        if (dp[n] != -1) return dp[n];

        visited[n] = true;
        int cnt = 1;

        for (int next : graph.get(n)) {
            if (!visited[next]) {
                cnt += dfs(next);
            }
        }

        visited[n] = false;
        dp[n] = cnt;

        return cnt;
    }
}

// 변수와 메서드 작성시 static과 접근제어자 주의
// 난 지금 문법에 약해. 로직은 아는데 구현이 안 되는게 있음

// 입출력 최적화 + 입출력 이해 좀 더
// 자료구조와 메서드. 같은 것 사이의 성능 차도

// List는 []인덱싱 안되는 거 알게 됨. .get()
// 전역 리스트나 배열 초기화 방법 (새 객체 생성 vs fill)
// 전역 변수 자료형별 초기값?
// 자바에서 동적배열
// 리스트 어레이 어레이리스트등 다 정렬 방법 달라

// 처음에 단순 bfs로 풀었는데 시간 초과 -> 