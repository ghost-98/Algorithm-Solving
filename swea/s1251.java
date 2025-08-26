import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 소수와 반올림, 자릿수 + 정수 자료형 크기에 따라 다루는 것
public class s1251 {
    static int[] X, Y, parent, rank;
    static List<long[]> edge;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            int n = Integer.parseInt(br.readLine());

            X = new int[n+1];
            Y = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++) X[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++) Y[i] = Integer.parseInt(st.nextToken());

            double e = Double.parseDouble(br.readLine());

            edge = new ArrayList<>();
            for(int i=1; i<=n-1; i++){
                for(int j=i+1; j<=n; j++) edge.add(new long[] {i, j, getCost(i, j)});
            }
            edge.sort((e1, e2) -> Long.compare(e1[2], e2[2]));
            
            parent = new int[n+1];
            for(int i=1; i<=n; i++) parent[i] = i;
            
            rank = new int[n+1];
            long sum = 0;
            for(int edgeIdx=0, edgeCnt=0; edgeCnt < n-1 && edgeIdx < edge.size(); edgeIdx++){
                long a = edge.get(edgeIdx)[0];
                long b = edge.get(edgeIdx)[1];
                long c = edge.get(edgeIdx)[2];

                int rootA = find((int)a);
                int rootB = find((int)b);

                if(rootA == rootB) continue;

                union(rootA, rootB);
                sum += c;
                edgeCnt++;
            }

            long ret = Math.round(e*sum);
            System.out.println("#"+tc+" "+ret);            
        }
    }

    static long getCost(int i, int j){
        long dx = X[i]-X[j];
        long dy = Y[i]-Y[j];
        return dx*dx + dy*dy;    
    }

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return;

        // rank 최적화
        if(rank[rootX] < rank[rootY]){
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            if(rank[rootX] == rank[rootY]) rank[rootX]++;
        }
    }
}
