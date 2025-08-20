import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.Queue;
import java.util.ArrayDeque;

// bfs 거리
public class s1238 {
    static boolean[][] graph;
    static int[] visited;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int tc=1; tc<=10; tc++){
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            graph = new boolean[101][101];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<length/2; i++){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from][to] = true;
            }

            // bfs, visited가 거리 역할
            visited = new int[101];
            q = new ArrayDeque<>();
            q.add(start);
            visited[start] = 1; // 실제 거리는 visited 배열 값-1
            while(!q.isEmpty()){
                int top = q.remove();
                for(int i=1; i<=100; i++){
                    if(graph[top][i] && visited[i] == 0){
                        visited[i] = visited[top]+1;
                        q.add(i);
                    }
                }
            }

            int ret = 0, distance = 0;
            for(int i=1; i<=100; i++){
                if(visited[i] > distance) { distance = visited[i]; ret = i; }
                else if(visited[i] == distance ) { ret = i; }
            }

            System.out.println("#"+tc+" "+ret);
        }
    }
}
