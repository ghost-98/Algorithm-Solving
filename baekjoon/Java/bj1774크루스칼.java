import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// have to input enter
public class bj1774크루스칼{
    static int[] parent;
    static int[][] pos;
    static List<double[]> edge;

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

        parent = new int[n+1];
        for(int i=1; i<=n; i++) parent[i] = i;
        
        // 연결되어 있는 간선
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        // 노드 간 모든 거리(간선)
        edge = new ArrayList<>();
        for(int i=1; i<=n-1; i++){
            for(int j=i+1; j<=n; j++) edge.add(new double[]{i, j, calcDistance(i, j)});
        }
        edge.sort((e1, e2) -> Double.compare(e1[2], e2[2])); // comparator, 간선 가중치 정렬

        // 크루스칼(가중치 작은거 부터 사이클 없이 n-1개)
        double ret = 0;
        for(double[] e : edge){ // 끝까지 해도 mst만들어진 이후엔 추가 없으므로 갠찮
            int a = (int)e[0];
            int b = (int)e[1];
            double w = e[2];
            if(find(a) != find(b)){
                union(a, b);
                ret += w;
            }
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

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) parent[rootY] = rootX;
    }
}