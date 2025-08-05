import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// 24m
public class bj2606 {
    public static int vertexNum, edgeNum;
    public static boolean[] visited = new boolean[101];
    public static boolean[][] graph = new boolean[101][101];
    public static Queue<Integer> q = new ArrayDeque<>();

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        vertexNum = sc.nextInt();
        edgeNum = sc.nextInt();

        // 입출력 성능,,
        for(int i=0; i<edgeNum; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            graph[x][y] = graph[y][x] = true;
        }

        int ret = 0;
        q.add(1);
        while (!q.isEmpty()) {
            int tmp = q.remove();
            if(visited[tmp]) continue;
            visited[tmp] = true;
            ret++;
            for(int i=1; i<=vertexNum; i++){ // 그래프의 인접 행렬은 0없음 주의!
                if(graph[tmp][i] && !visited[i]) {
                    q.add(i);
                }
            }
        }

        System.out.print(ret-1);
    }
}
// 문제 풀면서 든  생각
// 1. 익숙하지 않아서 간단한 문젠데도 시간 많이 걸림 24m
// 2. 범위, 조건 주의 (그래프의 인접 행렬은 0없음 주의!)
// 3. Java의 자료구조 사용법과 메서드들 숙지 + 제네릭 클래스 제네릭은 래퍼클래스
// 4. Java에서 자료형 별 초기화 안할 시 초깃값?