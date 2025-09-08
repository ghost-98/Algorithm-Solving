import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.List;
import java.util.ArrayList;

// CCTV 개수 8개 제한 -> 최대 4^8가지 상태 => 완탐 가능
// 원복을 배열 복사로 + 여러 처리
// 파악 설계는 빠르나, 구현이 느리다

// visited 사용x, dir 더 직관적으로 사용 가능

public class bj15683 {
    static int n, m; // 1 <= n, m <= 8
    static int ret = Integer.MAX_VALUE;
    static int[][] office; // 최대 64칸
    static List<int[]> cctv;
    static boolean[][] visited;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static List<int[]> dir = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        office = new int[n][m];
        visited = new boolean[n][m];
        cctv = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                office[i][j] = Integer.parseInt(st.nextToken());
                if(office[i][j] != 0 && office[i][j] != 6) cctv.add(new int[]{i, j});
            }
        }

        dir.add(new int[]{});
        dir.add(new int[]{1, 2, 4, 8});
        dir.add(new int[]{5, 10});
        dir.add(new int[]{3, 6, 12, 9}); 
        dir.add(new int[]{7, 14, 13, 11});       
        dir.add(new int[]{15});

        dfs(0);
        System.out.println(ret);
    }

    static void dfs(int depth){
        if(depth == cctv.size()){
            int tmpRet = check();
            if(ret > tmpRet) ret = tmpRet;

            return;
        }

        // 2차원 배열 복사
        boolean[][] copy = new boolean[n][m];
        arrCopy(visited, copy);

        int[] curCctv = cctv.get(depth);
        int curY = curCctv[0];
        int curX = curCctv[1];
        int cctvType = office[curY][curX];
        
        for(int d : dir.get(cctvType)){
            visited[curY][curX] = true;
            detect(curY, curX, d);
            dfs(depth+1);
            arrCopy(copy, visited); // 복원
        }
    }

    static int check(){
        int cnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(office[i][j] == 0 && !visited[i][j]) cnt++;
            }
        }

        return cnt;
    }

    static void detect(int y, int x, int dir){ // dir 0001 상, 0010 우 0100 하 1000 좌
        for(int i=0; i<4; i++){
            if((dir & 1<<i) != 0){ // 설정한 방향 쭉 감시
                int ny = y, nx = x;
                while(true){
                    ny += dy[i];
                    nx += dx[i];
                    if(ny<0 || nx<0 || ny>=n || nx>=m || office[ny][nx] == 6) break;
                    visited[ny][nx] = true;
                }
            }
        }
    }

    static void arrCopy(boolean[][] real, boolean[][] copy){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++) copy[i][j] = real[i][j];
        }
    }
}
