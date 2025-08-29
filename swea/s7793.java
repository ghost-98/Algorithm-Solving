import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class s7793 {
    static int n, m, ret;
    static int[] dy={-1, 0, 1, 0}, dx={0, 1, 0, -1};
    static int[] suyeon, goddess;
    static char[][] map;
    static Queue<int[]> demonHandQ;
    static boolean[][] demonArea, demonAreaCopy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<t; tc++){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); // 2 <= n <= 50
            int m = Integer.parseInt(st.nextToken()); // 2 <= n <= 50
            
            demonArea = new boolean[n][m];
            demonHandQ = new ArrayDeque<>();

            suyeon = new int[2];
            goddess = new int[2];

            map = new char[n][m];
            for(int r=0; r<n; r++){
                String col = br.readLine();
                for(int c=0; c<m; c++){
                    map[r][c] = col.charAt(c);

                    if(map[r][c] == 'X') demonHandQ.add(new int[] {r, c});
                    else if(map[r][c] == 'S'){ suyeon[0] = r; suyeon[1] = c; }
                    else if(map[r][c] == 'D'){ goddess[0] = r; goddess[1] = c; }
                }
            }

            ret = Integer.MAX_VALUE;
            solveDFS(0);

            System.out.print("#" + tc + " ");
            if(ret == 0) System.out.println(ret);
            else System.out.println("GAME OVER");
        }
    }

    // 턴 = depth
    static void solveDFS(int depth){
        // 완탐 돌리고 되면 break + 백트래킹으로 최적화
        demonAreaCopy = new boolean[n][m];
        arrayCopy(demonArea, demonAreaCopy);

        demonHand();
        for(int i=0; i<4; i++){
            int ny = suyeon[0] + dy[i];
            int nx = suyeon[1] + dx[i];

            if(map[ny][nx] == 'D' && ret > depth+1) ret = depth+1; 
            if(ny<0 || nx<0 || ny>=n || nx>=m || map[ny][nx]!='.') continue;
            if(demonArea[ny][nx]) continue;

            solveDFS(depth+1);
        }
        // 원복
        arrayCopy(demonAreaCopy, demonArea);
    }


    static void demonHand(){
        int size = demonHandQ.size();
        for(int i=0; i<size; i++){
            int[] demonHandPos = demonHandQ.poll();
            int y = demonHandPos[0];
            int x = demonHandPos[1];

            for(int j=0; j<4; j++){
                int ny = y+dy[i];
                int nx = x+dx[i];
                if(ny<0 || nx<0 || ny>=n || nx>=m || demonArea[ny][nx]) continue;
                if(map[y][x] == 'X' || map[y][x] == 'D') continue;

                demonHandQ.add(new int[] {ny, nx});
            }
        }
    }

    //
    static void arrayCopy(boolean[][] real, boolean[][] copy){
        for(int r=0; r<n; r++){
            for(int c=0; c<m; c++) copy[r][c] = real[r][c];
        }
        return;
    }
}