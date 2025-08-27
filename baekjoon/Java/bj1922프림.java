import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 9:15
// MST - 프림
public class bj1922프림 {
    // 프림 위해 필요한 visited배열, 이중 리스트, 우선순위 큐
    static boolean[] visited;
    static List<List<int[]>> edge;
    static PriorityQueue<int[]> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        edge = new ArrayList<>(n+1);
        for(int i=1; i<=n+1; i++) edge.add(new ArrayList<>());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edge.get(v1).add(new int[] {v2, w});
            edge.get(v2).add(new int[] {v1, w});
        }

        visited = new boolean[n+1];
        pq = new PriorityQueue<>((e1, e2) -> e1[1]-e2[1]);
        // 1부터 시작하는데 다른 깔끔한 방식은?
        visited[1] = true;
        for(int[] e : edge.get(1)) pq.add(e);

        int ret = 0;
        for(int vertexCnt=1; vertexCnt<n;){
            int[] minEdge = pq.poll();
            int v2 = minEdge[0];
            int w = minEdge[1];

            if(visited[v2]) continue;

            for(int[] e : edge.get(v2)) pq.add(e);
            visited[v2] = true;
            ret += w;
            vertexCnt++;
        }

        System.out.print(ret);
    }
}
