import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 15m
// Object 배열 쓰는법도 있고 클래스로 int int double도 있
// 프림으로 안풀리나.. ?
public class bj1774프림X{
    static int[][] pos;
    static boolean[] visited;
    static List<List<double[]>> edge;
    static PriorityQueue<double[]> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 정점
        pos = new int[n+1][2];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }

        edge = new ArrayList<>();
        for(int i=0; i<=n; i++) edge.add(new ArrayList<>());

        for(int i=1; i<=n-1; i++){
            for(int j=i+1; j<=n; j++){
                double distance = calcDistance(i, j);
                edge.get(i).add(new double[] {j, distance});
                edge.get(j).add(new double[] {i, distance});
            }
        }

        visited = new boolean[n+1];
        pq = new PriorityQueue<>((e1, e2) -> Double.compare(e1[2], e2[2]));
        int vertexCnt = 0;

        // 연결되어 있는 간선
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(!visited[a]){
                visited[a] = true;
                for(double[] e : edge.get(a)) pq.add(new double[] {a, e[0], e[1]});
                vertexCnt++;
            }else if(!visited[b]){
                visited[b] = true;
                for(double[] e : edge.get(b)) pq.add(new double[] {b, e[0], e[1]}); 
                vertexCnt++;
            }
        }

        // 프림
        double ret = 0;
        while(vertexCnt<n){
            double[] minEdge = pq.poll();
            int v2 = (int)minEdge[1];
            double w = minEdge[2];
            if(visited[v2]) continue;

            visited[v2] = true;
            for(double[] e : edge.get(v2)) pq.add(new double[] {v2, e[0], e[1]});
            ret += w;
            vertexCnt++;
        }

        System.out.printf("%.2f", ret);
    }

    // x1 ~ y2까지 int로 해서 계속 틀림
    static double calcDistance(int i, int j){
        long x1 = pos[i][0];
        long y1 = pos[i][1];
        long x2 = pos[j][0];
        long y2 = pos[j][1];
        double calcRet = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        return calcRet;
    }
}