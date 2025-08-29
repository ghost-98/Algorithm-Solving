import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 시뮬레이션 완탐 가능 -> 시간복잡도 : w^N * dfs(터뜨리는)
// 주어진 범위들이 작으면 완탐. 너무 크면 최적화 알고리즘들 고려

// 부족한 점
// 1. 작명이 조잡하다,, 함수랑 변수,,
// 2. gravity 함수 투포인터로 최적화 가능
// 3. 배열 복사 코스트 최대 12*15 배열이니 괜찮겠지? 다른 원복 최적화는?
public class s5656 {
    static int n, w, h;
    static int totalWalls, tmpBombedWalls, totalBombedWalls;
    static int[][] walls;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static List<Integer> unbombedWalls;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            totalWalls = 0; totalBombedWalls = 0;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 1~4  구슬 쏜 횟수
            w = Integer.parseInt(st.nextToken()); // 2~12  가로
            h = Integer.parseInt(st.nextToken()); // 2~15  세로

            walls = new int[h][w];
            for(int r=0; r<h; r++){
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<w; c++){
                    walls[r][c] = Integer.parseInt(st.nextToken());
                    if(walls[r][c] != 0) totalWalls++;
                }
            }   

            dfs(0, 0);
            totalWalls -= totalBombedWalls;

            System.out.println("#" + tc + " " + totalWalls);
        }
    }

    // 최대 12(w)^4(n) 번의 재귀 = 약 20,000회 * 배열(12*15) 복사 코스트
    static void dfs(int depth, int sum){
        if(depth == n){
            if(sum > totalBombedWalls) totalBombedWalls = sum;
            return;
        }

        for(int c=0; c<w; c++){
            int breakWall = findWall(c);
            
            if(breakWall == h) continue; // 벽돌 없는 열 패스
            
            int[][] tmpWalls = new int[h][w];
            arrayCopy(walls, tmpWalls);
            
            tmpBombedWalls = 0; // 한번의 연쇄 폭발에서 깨지는 벽돌 수
            bomb(breakWall, c);
            gravity();
            int tmpBombedWallsCopy = tmpBombedWalls;

            sum += tmpBombedWalls;

            dfs(depth+1, sum);

            // 원복
            sum -= tmpBombedWallsCopy;
            arrayCopy(tmpWalls, walls);
        }
    }

    // 폭발
    static void bomb(int y, int x){
        int size  = walls[y][x];
        walls[y][x] = 0;
        tmpBombedWalls++;
        for(int i=1; i<size; i++){
            for(int j=0; j<4; j++){
                int ny = y + dy[j]*i;
                int nx = x + dx[j]*i;
                if(ny<0 || nx<0 || ny>=h || nx>=w || walls[ny][nx]==0) continue;

                bomb(ny, nx);
            }
        }
    }

    // 폭발 후 중력 처리
    static void gravity(){
        for(int c=0; c<w; c++){
            unbombedWalls = new ArrayList<>();
            for(int r=h-1; r>=0; r--){
                if(walls[r][c] != 0) unbombedWalls.add(walls[r][c]);
            }

            for(int r=0; r<h; r++) walls[r][c] = 0;
            int wallRow = h-1;
            for(int w : unbombedWalls) walls[wallRow--][c] = w;
        }
    }

    // 열에서 가장 상단 벽돌 찾는 함수
    static int findWall(int col){
        for(int r=0; r<h; r++){
            if(walls[r][col] != 0) return r;
        }
        return h;
    }

    // 2차원 배열 카피 함수
    static void arrayCopy(int[][] map, int[][] copymap){
        for(int r=0; r<h; r++){
            for(int c=0; c<w; c++) copymap[r][c] = map[r][c];
        }
    }
}
