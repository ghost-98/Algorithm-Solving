import java.io.*;
import java.util.*;

public class bj17135 {
    static int N, M, D;
    static int[][] origin;   // 원본 맵
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        origin = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 궁수 3명이니까 3중 for문 조합 가능
        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                for (int k = j + 1; k < M; k++) {
                    simulate(new int[]{i, j, k});
                }
            }
        }

        System.out.println(answer);
    }

    // 시뮬레이션
    static void simulate(int[] archers) {
        int kill = 0;
        int[][] map = copyMap(origin);

        for (int turn = 0; turn < N; turn++) {
            Set<String> targets = new HashSet<>(); // 중복 적 위해

            // 각 궁수가 적 선택
            for (int ax : archers) {
                int[] target = chooseEnemy(map, N, ax);
                if (target != null) {
                    targets.add(target[0] + "," + target[1]);
                }
            }

            // 공격
            for (String t : targets) {
                String[] pos = t.split(",");
                int y = Integer.parseInt(pos[0]);
                int x = Integer.parseInt(pos[1]);
                if (map[y][x] == 1) {
                    map[y][x] = 0;
                    kill++;
                }
            }

            // 적 이동
            for (int r = N - 1; r > 0; r--) {
                map[r] = Arrays.copyOf(map[r - 1], M);
            }
            Arrays.fill(map[0], 0); // 맨 위는 비워짐
        }

        answer = Math.max(answer, kill);
    }

    // 궁수가 공격할 적 선택 (가장 가까운 + 왼쪽 우선)
    static int[] chooseEnemy(int[][] map, int archerRow, int ax) {
        int minDist = Integer.MAX_VALUE;
        int[] target = null;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) {
                    int dist = Math.abs(archerRow - r) + Math.abs(ax - c);
                    if (dist <= D) {
                        if (dist < minDist || (dist == minDist && c < target[1])) {
                            minDist = dist;
                            target = new int[]{r, c};
                        }
                    }
                }
            }
        }
        return target;
    }

    static int[][] copyMap(int[][] src) {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            newMap[i] = Arrays.copyOf(src[i], M);
        }
        return newMap;
    }
}
