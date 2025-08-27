import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 27m
// 2차원 + 실수를 다루는 MST
public class bj4386프림 {
    static double[] X, Y;
    static boolean[] visited;
    static List<List<double[]>> edge;
    static PriorityQueue<double[]> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        // 별들의 모든 좌표 담기
        X = new double[n+1];
        Y = new double[n+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            X[i] = Double.parseDouble(st.nextToken());
            Y[i] = Double.parseDouble(st.nextToken());
        }

        edge = new ArrayList<>();
        for(int i=0; i<=n; i++) edge.add(new ArrayList<>());

        // 별들간 모든 간선 담기
        for(int i=1; i<=n-1; i++){
            for(int j=i+1; j<=n; j++){
                double calcRet = calcDistance(i, j);
                edge.get(i).add(new double[] {j, calcRet});
                edge.get(j).add(new double[] {i, calcRet});
            }
        }

        pq = new PriorityQueue<>((e1, e2) -> Double.compare(e1[2], e2[2]));
        visited = new boolean[n+1];

        // 프림 알고리즘
        // 1번 별부터 선택
        visited[1] = true;
        for(double[] d : edge.get(1)) pq.add(new double[] {1, d[0], d[1]});

        double ret = 0;
        for(int vertexCnt=1; vertexCnt<n;){
            double[] minEdge = pq.poll();
            int v2 = (int)minEdge[1];
            double w = minEdge[2];
            if(visited[v2]) continue;

            visited[v2] = true;
            for(double[] d : edge.get(v2)) pq.add(new double[] {v2, d[0], d[1]});
            ret += w;
            vertexCnt++;
        }

        System.out.printf("%.2f", ret);      
    }

    // 별들 간 거리 계산 (가중치)
    static double calcDistance(int i, int j){
        double x1 = X[i];
        double x2 = X[j];
        double y1 = Y[i];
        double y2 = Y[j];
        double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        
        return distance;
    }
}
