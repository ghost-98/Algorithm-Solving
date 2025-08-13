import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj3109 {
    static int[] dy = {-1, 0, 1};
    static int r, c, ret = 0;
    static char[][] map;
    static boolean visited[][]; // 파이프라인 겹치면 안됨

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];

        for(int i=0; i<r; i++){
            String line = br.readLine();
            // 스트링 배열이 나을지도
            for(int j=0; j<c; j++) map[i][j] = line.charAt(j);
        }

        for(int i=0; i<r; i++) {
            if(comb(i, 0)) ret++;
        }

        System.out.print(ret);
    }

    public static boolean comb(int row, int col){
        if(col == c-1) return true;

        for(int i=0; i<3; i++){
            int nr = row + dy[i];
            int nc = col + 1;

            if(nr < 0 || nr >= r) continue;
            if(map[nr][nc] == 'x') continue;
            if(visited[nr][nc]) continue;

            visited[nr][nc] = true;
            if(comb(nr, nc)) return true;
        }

        return false;
    }
}