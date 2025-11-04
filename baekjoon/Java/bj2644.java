import java.util.*;

// 노드의 개수가 최대100인 점
// 트리 잠깐 떠올렸으나, 관계성 배제하고 그래프로 보고 bfs/dfs 생각
// 방문 배열을 체크 및 기록으로 사용
public class bj2644 {
    // BFS
    public static int bfs(int start, int end, int n, List<List<Integer>> graph) {
        int[] visited = new int[n + 1];
        Arrays.fill(visited, -1);  // -1은 아직 방문하지 않음
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : graph.get(current)) {
                if (visited[neighbor] == -1) {
                    visited[neighbor] = visited[current] + 1;  // 촌수 1 증가
                    queue.offer(neighbor);
                    
                    if (neighbor == end) {
                        return visited[neighbor];
                    }
                }
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int m = sc.nextInt();
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);  // 양방향 관계 (계층 의미x)
            graph.get(b).add(a);
        }

        // BFS
        int result = bfs(x, y, n, graph);
        System.out.println(result);
    }
}
