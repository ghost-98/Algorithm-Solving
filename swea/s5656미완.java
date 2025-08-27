import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 그리디라고 생각하고 1시간동안 풀었는데,,, 그리디 보장 안돼..
public class s5656 {
    static int n, w, h;
    static int bombedWalls, bombSize, tmpBombSize;
    static int[][] walls;
    static boolean[][] bombed;
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 1~4  구슬 쏜 횟수
            w = Integer.parseInt(st.nextToken()); // 2~12  가로
            h = Integer.parseInt(st.nextToken()); // 2~15  세로

            walls = new int[h][w];
            for(int r=0; r<h; r++){
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<w; c++) walls[r][c] = Integer.parseInt(st.nextToken());
            }

            // 그리디

            // 터트릴 열 찾기
            bombedWalls = 0;
            // n회 구슬 발사
            for(int i=0; i<n; i++){
                // 그리디

                // 1번 벽돌 제일 많이 부서지는 열 찾기
                int bombSize = 0;
                int minY=0, minX=0;
                for(int j=0; j<w; j++){
                    int colNearestWall = checkNearestWall(j); // 열마다 최신 상단 벽돌 관리 최적화 가능할 듯
                    if(colNearestWall == h) continue; // 다 터진 열이면 패스

                    bombed = new boolean[h][w];
                    tmpBombSize = 0;
                    find(colNearestWall, j);

                    if(tmpBombSize > bombSize){
                        bombSize = tmpBombSize;
                        minX = j;
                        minY = colNearestWall;
                    }
                }

                // 2번 고른 열 터뜨리기
                bomb(minY, minX);

                // 3번 터진 후 정리
                afterBomb();
            }

            System.out.println(bombedWalls);
        }
    }

    // find와 bomb 중첩 그리고 로직 전체적으로 최적화 안되어 있음.
    static void find(int y, int x){
        int size  = walls[y][x];
        bombed[y][x] = true;
        tmpBombSize++;
        for(int i=1; i<size; i++){
            for(int j=0; j<4; j++){
                int ny = y+dy[j];
                int nx = x+dx[j];
                if(ny<0 || nx<0 || ny>=h || nx>=w || walls[ny][nx]==0 || bombed[ny][nx]) continue;

                find(ny, nx);         
            }
        }
    }

    static void bomb(int y, int x){
        int size  = walls[y][x];
        walls[y][x] = 0;
        bombedWalls++;
        for(int i=1; i<size; i++){
            for(int j=0; j<4; j++){
                int ny = y+dy[j];
                int nx = x+dx[j];
                if(ny<0 || nx<0 || ny>=h || nx>=w || walls[ny][nx]==0) continue;

                bomb(ny, nx);         
            }
        }
    }

    static int checkNearestWall(int col){
        for(int i=0; i<h; i++){
            if(walls[i][col] != 0) return i;
        }
        return h; // 다 터진 열 일때
    }

    static void afterBomb(){
        for(int c=0; c<w; c++){
            List<Integer> row = new ArrayList<>();
            for(int r=h-1; r>=0; r--){
                if(walls[r][c] != 0) row.add(walls[r][c]);
            }

            int r = h-1;
            for(int val : row) walls[r--][c] = val;
            for(; r>=0; r--) walls[r][c] = 0;
        }
    }

    // 그리디라고 믿고,,
    // 1. 열별로 터지는 벽돌 수 체크
    // 2. 터뜨리기 연쇄(재귀)
    // 3. 터진 후 정리
    // -> 1번과 2번은 유사 로직 사용
}