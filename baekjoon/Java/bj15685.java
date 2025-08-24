import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
// 11:20. 그냥 구현(2차원)+자료구조는 동적 배열
public class bj15685 {
    static int n;
    static int[] dy = {0, -1, 0, 1}, dx = {1, 0, -1, 0};
    static boolean[][] visited = new boolean[101][101];
    static List<Integer> dirs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            dirs = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            // 동적 배열에 방향 리스트 넣기!!!
            dirs.add(d);
            for(int j=0; j<g; j++){
                int dirSize = dirs.size();
                for(int k = dirs.size()-1; k>=0; k--){
                    int dir = dirs.get(k);
                    dirs.add((dir+1)%4);
                }
            }

            // 시작 좌표부터 방향 리스트 따라 visited = true;
            visited[y][x] = true;
            for(int j=0; j<dirs.size(); j++){
                y += dy[dirs.get(j)];
                x += dx[dirs.get(j)];
                visited[y][x] = true;
            }
        }

        System.out.print(checkSquare());
    }

    // 최적화 없나..?
    static int checkSquare(){
        int ret = 0;
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]) ret++;
            }
        }
        return ret;
    }
}