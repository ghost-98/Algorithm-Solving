import java.util.*;

// 노드마다 bfs 돌리기와 플로이드 워셜 두가지 생각
public class bj1389 {

    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();

    static int bfs(int start) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                    queue.offer(next);
                }
            }
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += dist[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();  // 사람 수
        M = sc.nextInt();  // 친구 관계 수

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 관계 입력
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int minSum = Integer.MAX_VALUE;
        int ret = -1;

        // 모든 사람에 대해 BFS 수행
        for (int i = 1; i <= N; i++) {
            int sum = bfs(i);
            if (sum < minSum) {
                minSum = sum;
                ret = i;
            }
        }

        System.out.println(ret);
    }
}
