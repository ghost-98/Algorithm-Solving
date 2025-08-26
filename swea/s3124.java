import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class s3124 {
    static int[] parent, rank;
    static int[][] edge;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()); // v <= 10000
            int e = Integer.parseInt(st.nextToken()); // e <= 100000 
            
            edge = new int[e][3];
            for(int i=0; i<e; i++){
                st = new StringTokenizer(br.readLine());
                edge[i][0] = Integer.parseInt(st.nextToken());
                edge[i][1] = Integer.parseInt(st.nextToken());
                edge[i][2] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(edge, (e1, e2) -> Integer.compare(e1[2], e2[2]));

            parent = new int[v+1];
            for(int i=1; i<=v; i++) parent[i] = i;
			
            rank = new int[v+1];
            long ret = 0; // 범위 고려!!
            for(int edgeIdx=0, edgeCnt=0; edgeCnt < v-1 && edgeIdx < e; edgeIdx++){
                int a = edge[edgeIdx][0];
                int b = edge[edgeIdx][1];
                int c = edge[edgeIdx][2];

                int rootA = find(a);
                int rootB = find(b);

                if(rootA == rootB) continue;

                union(rootA, rootB);
                ret += c;
                edgeCnt++;
            }

            System.out.println("#"+tc+" "+ret);
        }
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