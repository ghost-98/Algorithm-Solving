import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 18
// 문제 제대로 읽자.. : 가중치 없고 그래프 연결 보장되어 있는데, 비행기 타는 횟수가 아닌 비행기 종류 수이므로 무조건 n-1 보장
public class bj9372{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
            }

            System.out.println(n-1);
        }
    }
}
// 비행기 탄 횟수로 오해했을때
// 간선이 한개인 곳부터 dfs해도 되겠는데? - 근데 모든 정점이 간선 2개 이상이라면? (최대한 편향된 트리 형태로 만들어서 루트 부터 dfs/bfs 가능할 듯한데..)