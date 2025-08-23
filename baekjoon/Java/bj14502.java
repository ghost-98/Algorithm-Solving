import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// 38m
// 배운 문법 : 동적 배열, list 요소 접근
// 접근 방법 : dfs + 완탐(조합(3중 for문), 원복).
// + 2차원 좌표도 1차원으로 만들어서 조합함, visited 배열 재정비 위해선 새로 생성보단 채우는게 나음
public class bj14502 {
    static int n, m, virusArea, ret;
    static int[][] map;
    static int[] dy = {-1,0,1,0}, dx={0,1,0,-1};
    static boolean[][] visited;
    static List<int[]> empty, virus;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        empty = new ArrayList<>();
        virus = new ArrayList<>();
        for(int r=0; r<n; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<m; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 0) empty.add(new int[]{r, c});
                else if(map[r][c] == 2) virus.add(new int[]{r, c});
            }
        }

        visited = new boolean[n][m];
        run();
        System.out.print(ret);
    }

    // 3중 for문 + 원복 + 실행문
    static void run(){
        int length = empty.size();
        for(int i=0; i<length-2; i++){
            map[empty.get(i)[0]][empty.get(i)[1]] = 1;
            for(int j=i+1; j<length-1; j++){
                map[empty.get(j)[0]][empty.get(j)[1]] = 1;
                for(int k=j+1; k<length; k++){
                    map[empty.get(k)[0]][empty.get(k)[1]] = 1;
                    // 실행문
                    int safetyArea = getSafetyArea();
                    if(safetyArea > ret) ret = safetyArea;

                    // 원복
                    map[empty.get(k)[0]][empty.get(k)[1]] = 0;
                }
                map[empty.get(j)[0]][empty.get(j)[1]] = 0;
            }
            map[empty.get(i)[0]][empty.get(i)[1]] = 0;
        }
    }

    // dfs 적용 후 남은 0 개수 리턴
    static int getSafetyArea(){
        int safetyArea = 0;
        virusArea = 0;
        // visited 배열 갱신
        for (int r = 0; r < n; r++) Arrays.fill(visited[r], false);
        
        for(int[] pos : virus) dfs(pos[0], pos[1]);
        safetyArea = empty.size() + virus.size() - virusArea - 3;
        return safetyArea;
    }

    // dfs
    static void dfs(int y, int x){
        visited[y][x] = true;
        virusArea++;
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny<0 || nx<0 || ny>=n || nx>=m || map[ny][nx]!=0 || visited[ny][nx]) continue;
            dfs(ny, nx);
        }
    }
}
