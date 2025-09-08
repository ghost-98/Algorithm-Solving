import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 15:37
// dp로 각 좌표에서 최솟값 + 말움직임 남은 cnt (움직일땐 항상 한칸 먼저)
public class bj1600 {

    static int[][] world;
    static int[][] dp;

    static int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    public static void bj1600(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken()); // 0 <= k <= 30

        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken()); // 1 <= w <= 200
        int h = Integer.parseInt(st.nextToken()); // 1 <= h <= 200

        world = new int[w][h];
        for(int r=0; r<w; r++){
            st = new StringTokenizer(st.nextToken());
            for(int c=0; c<h; c++) world[r][c] = Integer.parseInt(st.nextToken());
        }

        // 0,0 -> w-1, h-1로 이동
        // 갈 수 없으면 -1
    }
}
