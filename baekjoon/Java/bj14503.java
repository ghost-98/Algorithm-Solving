import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
// 40m, 조건 헷갈림. 사이드에 벽이 있다는
public class bj14503 {
    static int n, m, r, c, d, ret;
    static int[][] room;
    static int[] dy={-1,0,1,0}, dx={0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        room = new int[n][m];
        for(int row=0; row<n; row++){
            st = new StringTokenizer(br.readLine());
            for(int col=0; col<m; col++) room[row][col] = Integer.parseInt(st.nextToken());
        }

        recur(r, c, d);
    }

    static void recur(int y, int x, int dir){
        if(room[y][x]==0){
            ret++;
            room[y][x] = 2;
        }
        // 청소되지 않은 빈 칸 있는 경우
        for(int i=0; i<4; i++){
            dir = (dir + 3) % 4; // 왼쪽 회전
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if(room[ny][nx]==0){
                recur(ny, nx, dir);
                return;
            }
        }

        // 청소되지 않은 빈 칸 없는 경우
        int ny = y + dy[(dir+2)%4];
        int nx = x + dx[(dir+2)%4];
        if(room[ny][nx] != 1){
            recur(ny, nx, dir);
        }else System.out.print(ret);
    }
}
