import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 50m
// curK 와 check() 컨셉만 이해 + 죽은앤 이동x
public class s2382x {
    static int n, k;
    static int[][] group;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            
            group = new int[k][4]; // 0 세로위치, 1 가로위치, 2 미생물 수, 3 이동 방향
            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                group[i][0] = Integer.parseInt(st.nextToken());
                group[i][1] = Integer.parseInt(st.nextToken());
                group[i][2] = Integer.parseInt(st.nextToken());
                group[i][3] = Integer.parseInt(st.nextToken()); // 1 상 2 하 3 좌 4 우
            }

            // 갱신되는 군집의 개수
            int curK = k;

            for(int i=0; i<m; i++){
                // 이동
                for(int j=0; j<curK; j++){
                    int direction = group[j][3];
                    if(group[j][2] == 0) continue; // 죽으면 이동 x
                    group[j][0] += dy[direction-1];
                    group[j][1] += dx[direction-1];
                }
                // 약품
                for(int j=0; j<curK; j++){
                    if(group[j][0] == 0 || group[j][0]==n-1 || group[j][1] == 0 || group[j][1]==n-1){
                        group[j][2] /= 2;
                        // 죽음 처리해도 되는데 안해도 무방할듯
                        int dir = group[j][3];
                        if(dir==1 || dir==3) group[j][3] += 1;
                        else if(dir==2 || dir==4) group[j][3] -= 1;
                    }
                }                
                // 만남 확인 처리
                curK = check(curK);
            }
            
            int ret = 0;
            for (int i = 0; i < curK; i++) ret += group[i][2];
            System.out.println("#"+tc+" "+ret);
        }
    }

    // 얘가 빡 + curK
    static int check(int currentK) {
        int[][][] map = new int[n][n][2];
        int[][] maxMap = new int[n][n];

        for (int i = 0; i < currentK; i++) {
            int y = group[i][0];
            int x = group[i][1];
            int count = group[i][2];
            int dir = group[i][3];

            if (count == 0) continue;

            map[y][x][0] += count;

            if (count > maxMap[y][x]) {
                maxMap[y][x] = count;
                map[y][x][1] = dir;
            }
        }
        
        int newK = 0;
        int[][] tempGroup = new int[currentK][4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j][0] > 0) {
                    tempGroup[newK][0] = i;
                    tempGroup[newK][1] = j;
                    tempGroup[newK][2] = map[i][j][0];
                    tempGroup[newK][3] = map[i][j][1];
                    newK++;
                }
            }
        }
        
        for(int i = 0; i < newK; i++) group[i] = tempGroup[i];

        return newK;
    }
}
