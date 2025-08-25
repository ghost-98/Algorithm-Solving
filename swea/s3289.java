import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class s3289{
    static int[] parent;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            parent = new int[n+1];
            Arrays.fill(parent, -1);

            int ops, a, b;
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                ops = Integer.parseInt(st.nextToken());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                if(ops == 0) union(a, b);
                else if(ops == 1) check(a, b);
            }

            System.out.println("#"+tc+" "+sb);
        }
    }

    static int find(int x){
        if(parent[x] == -1) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;

        parent[bRoot] = aRoot;
    }

    static void check(int a, int b){
        if(find(a) == find(b)) sb.append("1");
        else sb.append("0");
    }
}