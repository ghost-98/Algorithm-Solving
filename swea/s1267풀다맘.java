import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class s1267풀다맘 {
    static int v, e;
    static boolean[][] graph;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int tc=1; tc<=10; tc++){
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            graph = new boolean[v+1][v+1];
            visited = new boolean[v+1];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<e; i++){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from][to] = true;
            }

            sb = new StringBuilder();
            sb.append("#"+tc);
            // 진입차수 0인 노드들 찾아서
            for(int i=1; i<=v; i++){
                boolean flag = true;
                for(int j=1; j<=v; j++){
                    if(graph[j][i]) flag = false;
                }
                if(flag && !visited[i]) dfs(i);
            }

            System.out.println(sb);
        }
    }

    static void dfs(int x){
        sb.append(" " + x);
        visited[x] = true;
        for(int i=1; i<=v; i++){
            if(graph[x][i] && checkPreNode(i)) dfs(i);
        }
    }

    static boolean checkPreNode(int x){
        boolean flag = true;
        for(int i=1; i<=v; i++){
            if(graph[i][x] && !visited[i]) flag = false;
        }
        return flag;
    }
}
