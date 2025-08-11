import java.util.*;
public class s5650 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
        int[] dc = {0, 1, 0, -1};
        
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[][] map = new int[N][N];

            @SuppressWarnings("unchecked")
            List<int[]>[] wormhole = new ArrayList[11];
            for (int i = 6; i <= 10; i++) wormhole[i] = new ArrayList<>();
            
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    map[r][c] = sc.nextInt();
                    if (map[r][c] >= 6) {
                        wormhole[map[r][c]].add(new int[]{r, c});
                    }
                }
            }
            
            int maxScore = 0;
            for (int sr = 0; sr < N; sr++) {
                for (int scn = 0; scn < N; scn++) {
                    if (map[sr][scn] != 0) continue;
                    for (int d = 0; d < 4; d++) {
                        int r = sr, c = scn, dir = d, score = 0;
                        
                        while (true) {
                            r += dr[dir];
                            c += dc[dir];
                            
                            if (r < 0 || r >= N || c < 0 || c >= N) {
                                dir = (dir + 2) % 4;
                                score++;
                                continue;
                            }
                            
                            if ((r == sr && c == scn) || map[r][c] == -1) {
                                maxScore = Math.max(maxScore, score);
                                break;
                            }
                            
                            if (1 <= map[r][c] && map[r][c] <= 5) {
                                int block = map[r][c];
                                if (block == 1) {
                                    if (dir == 0) dir = 2;
                                    else if (dir == 1) dir = 3;
                                    else if (dir == 2) dir = 1;
                                    else dir = 0;
                                } else if (block == 2) {
                                    if (dir == 0) dir = 1;
                                    else if (dir == 1) dir = 3;
                                    else if (dir == 2) dir = 0;
                                    else dir = 2;
                                } else if (block == 3) {
                                    if (dir == 0) dir = 3;
                                    else if (dir == 1) dir = 2;
                                    else if (dir == 2) dir = 0;
                                    else dir = 1;
                                } else if (block == 4) {
                                    if (dir == 0) dir = 2;
                                    else if (dir == 1) dir = 0;
                                    else if (dir == 2) dir = 3;
                                    else dir = 1;
                                } else if (block == 5) {
                                    dir = (dir + 2) % 4;
                                }
                                score++;
                            }
                            
                            if (6 <= map[r][c] && map[r][c] <= 10) {
                                int num = map[r][c];
                                int[] first = wormhole[num].get(0);
                                int[] second = wormhole[num].get(1);
                                if (r == first[0] && c == first[1]) {
                                    r = second[0];
                                    c = second[1];
                                } else {
                                    r = first[0];
                                    c = first[1];
                                }
                            }
                        }
                    }
                }
            }
            
            System.out.println("#" + tc + " " + maxScore);
        }
    }
}
