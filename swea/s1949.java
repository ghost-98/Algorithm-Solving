import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.List;
import java.util.ArrayList;
// 45m
// 이런 문제 정도는 다 읽고 확신 있는 설계가 나와줘야 함

// 문제가 이상한게 높이 깎는 것과 상관 없이, 처음에 봉우리였던 곳이 봉우리임
public class s1949 {
    static int n, k, ret;
    static int[][] map;
    static List<int[]> peak;
    static boolean[][] visited;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=t; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 3 ≤ N ≤ 8
            k = Integer.parseInt(st.nextToken()); // 1 ≤ K ≤ 5

            map = new int[n][n];
            peak = new ArrayList<>();

            int maxH = 0;
            for(int r=0; r<n; r++){
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<n; c++){
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if(map[r][c] > maxH){
                        peak.clear();
                        peak.add(new int[] {r, c});
                        maxH = map[r][c];
                    }else if(map[r][c] == maxH) peak.add(new int[] {r, c});
                }
            }

            ret = 0;
            // 모든 땅을 1~k까지 깎아보는 3중 for문
            for(int carveR=0; carveR<n; carveR++){
                for(int carveC=0; carveC<n; carveC++){
                    for(int carveH=0; carveH<=k; carveH++){
                        if(map[carveR][carveC] < carveH) break; // 그 땅이 깎을 깊이보다 낮으면 break;
                        
                        // 땅 깎기
                        map[carveR][carveC] -= carveH;

                        for(int[] p : peak){
                            visited = new boolean[n][n];
                            dfs(p[0], p[1], 1); 
                        }

                        // 땅 원복
                        map[carveR][carveC] += carveH;
                    }
                }
            }

            System.out.println("#"+tc+" "+ret);
        }
    }

    // 땅 한곳 깎은 뒤, 지도 모든 좌표 시작점으로 dfs해서 가장 큰 결과 구하기
    // 백트래킹 잘 쳐볼게요 - ??
    static void dfs(int y, int x, int depth){
        visited[y][x] = true;
        ret = Math.max(ret, depth);

        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny<0 || nx<0 || ny>=n || nx>=n) continue; // 배열 인덱스 제한
            if(visited[ny][nx]) continue; // 지난 곳이면 패스
            if(map[y][x] <= map[ny][nx]) continue; // 다음 경로가 지금보다 높으면 패스
            dfs(ny, nx, depth+1);
        }

        visited[y][x] = false;
    }
}
// 가장 높은 곳 1개 이상중 시작 -> 낮거나 같은 쪽 4방으로만 움직임
// 한 곳 최대 k만큼 깎을수 있음 (1<=k)
// 최장 등산로

// 최악 : 깎을 수 있는 320개 경우 중 시작점 64개 백트치면서? -> 이런 백트요소 불일정할때 어떻게 산정???
// -> 그냥 완탐 dfs하면서 백트 잘치면 되는 문제 같다고 판단
