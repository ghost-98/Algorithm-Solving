import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
// 11:21
// 1000C2 + 몇 초뒤 오름차순 정렬 + 3개이상 충돌 관리 + 이전에 터지면 continue
public class s5648 {
    static int n;
    static int[][] atomics;
    static List<int[]> collision; // 원자1 인덱스, 원자2 인덱스, 충돌위치y, 충돌위치x, 충돌 시간
    static boolean[] disappeared;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 1,000
            
            atomics = new int[n][4];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                atomics[i][0] = Integer.parseInt(st.nextToken()); // -1,000 ≤ x, y ≤ 1,000
                atomics[i][1] = Integer.parseInt(st.nextToken());
                atomics[i][2] = Integer.parseInt(st.nextToken()); // 상 0, 하 1, 좌 2, 우 3
                atomics[i][3] = Integer.parseInt(st.nextToken()); // 1 ≤ K ≤ 100

                // 좌표 2배 확대
                atomics[i][0] *= 2;
                atomics[i][1] *= 2;
            }

            collision = new ArrayList<>();
            disappeared = new boolean[n];
            getCollision(); // 충돌 가능성 다 담기

            collision.sort((a1, a2) -> a1[4]-a2[4]); // 충돌 시간 순으로 정렬

            int sum = 0;
            int idx = 0;
            while (idx < collision.size()) {
                int time = collision.get(idx)[4];
                Map<String, List<Integer>> map = new HashMap<>();

                // 같은 시간 충돌 모으기
                while (idx < collision.size() && collision.get(idx)[4] == time) {
                    int[] c = collision.get(idx++);
                    int a1 = c[0];
                    int a2 = c[1];

                    if (disappeared[a1] || disappeared[a2]) continue;

                    String key = c[2] + "," + c[3]; // 충돌 위치
                    map.putIfAbsent(key, new ArrayList<>());
                    map.get(key).add(a1);
                    map.get(key).add(a2);
                }

                // 충돌 처리 (2개 이상이면 모두 폭발)
                for (List<Integer> list : map.values()) {
                    Set<Integer> uniqueAtoms = new HashSet<>(list);
                    for (int atom : uniqueAtoms) {
                        if (!disappeared[atom]) {
                            sum += atomics[atom][3];
                            disappeared[atom] = true;
                        }
                    }
                }
            }

            System.out.println("#"+ tc + " " + sum);
        }


    }

    // 충돌 가능성 모두 계산
    static void getCollision() {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] a = atomics[i];
                int[] b = atomics[j];

                int dx = b[1] - a[1];
                int dy = b[0] - a[0];

                int t = -1;

                // 방향 고려
                if (a[2] == 0 && b[2] == 1 && dx == 0 && dy > 0) t = dy / 2;
                else if (a[2] == 1 && b[2] == 0 && dx == 0 && dy < 0) t = -dy / 2;
                else if (a[2] == 2 && b[2] == 3 && dy == 0 && dx > 0) t = dx / 2;
                else if (a[2] == 3 && b[2] == 2 && dy == 0 && dx < 0) t = -dx / 2;
                else if (Math.abs(dx) == Math.abs(dy) && ((a[2]==0 && b[2]==2 && dx>0 && dy>0) ||
                                                        (a[2]==0 && b[2]==3 && dx<0 && dy>0) ||
                                                        (a[2]==1 && b[2]==2 && dx>0 && dy<0) ||
                                                        (a[2]==1 && b[2]==3 && dx<0 && dy<0))) {
                    t = Math.abs(dx); // 대각선 충돌
                }

                if (t > 0) {
                    int y = a[0] + (a[2]==0?-1: a[2]==1?1:0)*t;
                    int x = a[1] + (a[2]==2?-1: a[2]==3?1:0)*t;
                    collision.add(new int[]{i, j, y, x, t});
                }
            }
        }
    }
}