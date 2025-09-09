import java.io.*;
import java.util.*;

// bfs가 더 나을지도
public class bj2636 {
    static int row, col;
    static int[][] board;
    static boolean[][] visited;
    static List<int[]> cheesePos;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        row = Integer.parseInt(st.nextToken()); // 최대 100
        col = Integer.parseInt(st.nextToken()); // 최대 100

        board = new int[row][col];
        for(int r=0; r<row; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<col; c++) board[r][c] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[row][col];

        int time = 0;
        int meltCheeseCnt = 0;
        while(true){
            fillVisited();
            cheesePos = new ArrayList<>();

            dfs(0, 0);

            if(cheesePos.isEmpty()) break; // 녹을 치즈 x

            meltCheeseCnt = cheesePos.size();
            for(int[] pos : cheesePos) board[pos[0]][pos[1]] = 0;

            time++;
        }

        System.out.println(time);
        System.out.println(meltCheeseCnt);
    }

    static void fillVisited(){
        for(int r=0; r<row; r++){
            for(int c=0; c<col; c++) visited[r][c] = false;
        }      
    }

    static void dfs(int y, int x){
        visited[y][x] = true;
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny<0 || nx<0 || ny>=row || nx>=col || visited[ny][nx]) continue;
            if(board[ny][nx] == 1){
                cheesePos.add(new int[] {ny, nx});
                visited[ny][nx] = true;
                continue;
            }
            dfs(ny, nx);
        }
    }
}
