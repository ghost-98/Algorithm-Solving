import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가장자리가 아닌 좌표들 각각 갈 수 있는 방향(최대 4)들의 조합 중 연결 가능한 코어가 많으면서 가장 합이 낮은 거리
// gpt로 디버깅 한 부분 : 연결 안하는 선택지, dfs에서 가지치기(백트) 누락
// 빡구현 빡세고 낯설다..
// 어떻게 풀지 로직 + 시간복잡도 계산 + 고려하지 않은 부분 확인 및 엣지 케이스 + 최적화(가지치기)
public class s1767x {
    static int n;
    static boolean[][] processor;
    
    static int corePosCnt;
    static int[][] corePos;

    static int maxCoreConnect, minDistance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){

            n = Integer.parseInt(br.readLine());
            processor = new boolean[n][n];

            corePosCnt = 0;
            corePos = new int[n*n][2];
            for(int r=0; r<n; r++){
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<n; c++){
                    if(st.nextToken().equals("1")){
                        processor[r][c] = true;
                        if(r != 0 && r != n-1 && c != 0 && c != n-1){ // 코어 좌표 리스트에 담기
                            corePos[corePosCnt][0] = r;
                            corePos[corePosCnt][1] = c;
                            corePosCnt++;
                        }
                    }
                }
            }

            // 조합 : 최대 12개의 좌표가 4방향으로 갈 수 있으니 4^12 ~= 16000000
            // 조합1번 : 비트마스킹(2비트씩 한코어 비트마스킹으로 가능)
            // 조합2번 : dfs
            maxCoreConnect = 0;
            minDistance = Integer.MAX_VALUE;
            dfs(0, 0, 0);
                
            System.out.println("#" + tc + " " + minDistance);
        }
    }

    // 코어마다 4가지 선택지 최대 12코어 dfs
    public static void dfs(int depth, int coreConnect, int totalDistance){
        // 가지치기 누락
        if (coreConnect + (corePosCnt - depth) < maxCoreConnect) return;

        if(depth == corePosCnt){
            if(coreConnect > maxCoreConnect){
                maxCoreConnect = coreConnect;
                minDistance = totalDistance;
            }else if(coreConnect == maxCoreConnect && minDistance > totalDistance){
                minDistance = totalDistance;
            }
            return;
        }
        

        int y = corePos[depth][0];
        int x = corePos[depth][1];
        for(int i=1; i<=4; i++){
            int distance = check(i, y, x);
            if(distance == 0) continue;
            wireInstall(i, y, x);
            dfs(depth+1, coreConnect+1, totalDistance + distance);
            wireUninstall(i, y, x);
        }
        // 연결 안하는 선택지 누락했음
        dfs(depth+1, coreConnect, totalDistance);

    }

    // 안되면 0, 나머진 자연수로 거리 반환
    public static int check(int direction, int y, int x){
        // 1,2,3,4는 상우하좌 순
        int distance = 0;
        if(direction == 1){
            for(int i=0; i<y; i++){
                if(processor[i][x]) return 0;
            }
            distance = y;
        }else if(direction == 2){
            for(int i=x+1; i<n; i++){
                if(processor[y][i]) return 0;
            }
            distance = (n-1)-x;
        }else if(direction == 3){
            for(int i=y+1; i<n; i++){
                if(processor[i][x]) return 0;
            }
            distance = (n-1)-y; 
        }else if(direction == 4){
            for(int i=0; i<x; i++){
                if(processor[y][i]) return 0;
            }
            distance = x;
        }

        return distance;
    }

    static void wireInstall(int direction, int y, int x){
        if(direction == 1){
            for(int i=0; i<y; i++) processor[i][x] = true;
        }else if(direction == 2){
            for(int i=x+1; i<n; i++) processor[y][i] = true;
        }else if(direction == 3){
            for(int i=y+1; i<n; i++) processor[i][x] = true;
        }else if(direction == 4){
            for(int i=0; i<x; i++) processor[y][i] = true;
        }
    }

    static void wireUninstall(int direction, int y, int x){
        if(direction == 1){
            for(int i=0; i<y; i++) processor[i][x] = false;
        }else if(direction == 2){
            for(int i=x+1; i<n; i++) processor[y][i] = false;
        }else if(direction == 3){
            for(int i=y+1; i<n; i++) processor[i][x] = false;
        }else if(direction == 4){
            for(int i=0; i<x; i++) processor[y][i] = false;
        }
    }
}
