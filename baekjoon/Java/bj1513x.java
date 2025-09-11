import java.util.Scanner;
// 30m
/*
 * 기존 코드 : 완탐 + 가지치기 (dp + dfs) -> n, m이 50이기 때문에 시간 초과 (시간복잡도, 성능 체크)
 * 개선 코드 : 
 * 
 * -> 뭘 메모이제이션 할까에 대한 고민, 변수 차원
 */

public class bj1513x{
    static int n, m, c;
    static int[][] arcade;
    static final int MOD = 1000007;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 1 ~ 50
        m = sc.nextInt(); // 1 ~ 50
        c = sc.nextInt(); // 1 ~ 50

        arcade = new int[n + 1][m + 1];
        for(int i = 1; i <= c; i++){
            int y = sc.nextInt();
            int x = sc.nextInt();
            arcade[y][x] = i;
        }

        // dp[y][x][k][last] -> 마지막 게임기 번호 last
        int[][][][] dp = new int[n+1][m+1][c+1][c+1];

        // 시작점
        if(arcade[1][1] == 0) dp[1][1][0][0] = 1;
        else {
            int num = arcade[1][1];
            dp[1][1][1][num] = 1;
        }

        for(int y=1; y<=n; y++){
            for(int x=1; x<=m; x++){
                for(int k=0; k<=c; k++){
                    for(int last=0; last<=c; last++){
                        int ways = dp[y][x][k][last];
                        if(ways == 0) continue;

                        // 이동: 오른쪽
                        if(x+1 <= m){
                            int nextNum = arcade[y][x+1];
                            if(nextNum == 0){
                                dp[y][x+1][k][last] = (dp[y][x+1][k][last]+ways)%MOD;
                            } else if(nextNum > last){
                                dp[y][x+1][k+1][nextNum] = (dp[y][x+1][k+1][nextNum]+ways)%MOD;
                            }
                        }

                        // 이동: 아래
                        if(y+1 <= n){
                            int nextNum = arcade[y+1][x];
                            if(nextNum == 0){
                                dp[y+1][x][k][last] = (dp[y+1][x][k][last]+ways)%MOD;
                            } else if(nextNum > last){
                                dp[y+1][x][k+1][nextNum] = (dp[y+1][x][k+1][nextNum]+ways)%MOD;
                            }
                        }

                    }
                }
            }
        }

        int[] ret = new int[c+1];
        for(int k=0; k<=c; k++){
            for(int last=0; last<=c; last++){
                ret[k] = (ret[k] + dp[n][m][k][last])%MOD;
            }
        }

        for(int i=0; i<=c; i++) System.out.print(ret[i]+" ");
    }

    /*
    static void dfs(int y, int x, int visitCnt) {
        // n, m 도착시
        if(y == n && x == m){
            if(arcade[y][x] == 0) ret[visitCnt]++;
            else if(checkVisitOrder(arcade[y][x])) ret[visitCnt+1]++;
            return;
        }

        int arcadeNum = arcade[y][x];
        if(arcadeNum != 0){ // 오락실일 때
            if(checkVisitOrder(arcadeNum)) {
                visited[arcadeNum] = true;
                // n혹은 m 초과 제한
                if(x+1 <= m) dfs(y, x + 1, visitCnt+1);
                if(y+1 <= n) dfs(y + 1, x, visitCnt+1);

                visited[arcadeNum] = false; // 원복
            }else return; // 방문 오락실 순서 꼬이면 가지 치기
        }else{
            if(x+1 <= m) dfs(y, x + 1, visitCnt);
            if(y+1 <= n) dfs(y + 1, x, visitCnt);
        }
    }

    static boolean checkVisitOrder(int n) {
        for(int i = n + 1; i <= c; i++){
            if(visited[i]) return false;
        }

        return true;
    }
    */
}