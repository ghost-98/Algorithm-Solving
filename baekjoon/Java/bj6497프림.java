import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 13m
public class bj6497프림 {
    static boolean[] visited;
    static List<List<int[]>> edge;
    static PriorityQueue<int[]> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if(n==0 && m==0) break;

            edge = new ArrayList<>();
            for(int i=0; i<=m; i++) edge.add(new ArrayList<>());

            int totalSum = 0;

            // 간선들 이중 리스트에 저장
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                totalSum += w;

                edge.get(v1).add(new int[] {v2, w});
                edge.get(v2).add(new int[] {v1, w});
            }

            pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[2], e2[2]));
            visited = new boolean[m+1];

            visited[1] = true;
            for(int[] e : edge.get(1)) pq.add(new int[] {1, e[0], e[1]});

            int minSum = 0;
            for(int vertexCnt=1; vertexCnt<m;){
                int[] minEdge = pq.poll();
                int v2 = minEdge[1];
                int w = minEdge[2];
                if(visited[v2]) continue;

                visited[v2] = true;
                for(int[] e : edge.get(v2)) pq.add(new int[] {v2, e[0], e[1]});
                minSum += w;
                vertexCnt++;
            }

            System.out.println(totalSum - minSum);
        }
    }
}
