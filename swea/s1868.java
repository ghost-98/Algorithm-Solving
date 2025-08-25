import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class s1868 {
    static int n;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static char[][] table;
    static boolean[][] visited;
    static List<int[]> mazeCnt0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){
            n = sc.nextInt(); // n <= 300
            table = new char[n][n];
            visited = new boolean[n][n];

            for(int r=0; r<n; r++){
                String str = sc.next();
                for(int c=0; c<n; c++){
                    table[r][c] = str.charAt(c);
                    if(table[r][c] == '*') visited[r][c] = true; // 지뢰 좌표는 방문 처리
                }
            }

            mazeCnt0 = new ArrayList<>();
            completeMineFind();
            // 0이었던 좌표들 클릭 시작
            int ret = 0;
            int size = mazeCnt0.size();
            for(int i=0; i<size; i++){
                int y = mazeCnt0.get(i)[0];
                int x = mazeCnt0.get(i)[1];
                if(visited[y][x]) continue;
                mazeCnt0Click(y, x);
                ret++;
            }

            // 0클릭 이후 남은 좌표들 처리
            for(int r=0; r<n; r++){
                for(int c=0; c<n; c++){
                    if(!visited[r][c]) ret++;
                }
            }

            System.out.println("#"+tc+" "+ret);
        }
    }

    static void completeMineFind(){
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if(table[r][c] == '*') continue;
                int mazeCnt = 0;
                for(int i=0; i<8; i++){
                    int ny = r + dy[i];
                    int nx = c + dx[i];
                    if(ny<0 || ny>=n || nx<0 || nx>=n) continue;
                    if(table[ny][nx] == '*') mazeCnt++;
                }
                if(mazeCnt == 0) mazeCnt0.add(new int[] {r, c});
                table[r][c] = (char)(mazeCnt+'0');
            }
        }
    }

    static void mazeCnt0Click(int y, int x){
            if (visited[y][x]) return;
            visited[y][x] = true;

            if (table[y][x] != '0') return;

            for(int i=0; i<8; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(ny<0 || ny>=n || nx<0 || nx>=n) continue;
                mazeCnt0Click(ny, nx);
            }
    }
}
// 가능하면 0인곳 누르고, 그런곳이 끝나면
// table 수로 만들고, 0인 부분 dfs로 하나인 셈 치고 싹 visited 그리고 테두리까지 visited
// visited 아닌 부분 개수 세기