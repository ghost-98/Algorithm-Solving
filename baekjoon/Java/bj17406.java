import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 7m + 
// k <= 6이라서 일단 완탐 방향으로
// 1-based 통일
class bj17406 {
    static int n, m, k;
    static int ret = Integer.MAX_VALUE;
    static int[][] A;
    static int[][] rotateInfo;
    static boolean[] used;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        A = new int[n+1][m+1];
        used = new boolean[k+1];

        for(int r=1; r<=n; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<=m; c++) A[r][c] = Integer.parseInt(st.nextToken());
        }

        rotateInfo = new int[k+1][3];

        for(int i=1; i<=k; i++){
            st = new StringTokenizer(br.readLine());
            rotateInfo[i][0] = Integer.parseInt(st.nextToken());
            rotateInfo[i][1] = Integer.parseInt(st.nextToken());
            rotateInfo[i][2] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        System.out.print(ret);
    }

    static void dfs(int rotateCnt){
        if(rotateCnt == k){
            ret = Math.min(ret, getA());
            return;
        }

        for(int i=1; i<=k; i++){
            if(used[i]) continue;
            
            // 돌리기
            rotate(rotateInfo[i][0], rotateInfo[i][1], rotateInfo[i][2]);
            used[i] = true;

            dfs(rotateCnt+1);

            // 원복 (배열 복사로도 가능)
            unRotate(rotateInfo[i][0], rotateInfo[i][1], rotateInfo[i][2]);
            used[i] = false;
        }
    }

    // 정사각형 형태 회전
    static void rotate(int y, int x, int s){
        for(int i=1; i<=s; i++){
            int[] arrForRotate = new int[8*i+1];
            int idx = 1;
            for(int j=x-i; j<x+i; j++) arrForRotate[idx++] = A[y-i][j];
            for(int j=y-i; j<y+i; j++) arrForRotate[idx++] = A[j][x+i];
            for(int j=x+i; j>x-i; j--) arrForRotate[idx++] = A[y+i][j];
            for(int j=y+i; j>y-i; j--) arrForRotate[idx++] = A[j][x-i];

            arrForRotate[0] = arrForRotate[8*i];
            idx = 0;
            for(int j=x-i; j<x+i; j++) A[y-i][j] = arrForRotate[idx++];
            for(int j=y-i; j<y+i; j++) A[j][x+i] = arrForRotate[idx++];
            for(int j=x+i; j>x-i; j--) A[y+i][j] = arrForRotate[idx++];
            for(int j=y+i; j>y-i; j--) A[j][x-i] = arrForRotate[idx++];
        }
    }

    // 정사각형 형태 회전
    static void unRotate(int y, int x, int s){
        for(int i=1; i<=s; i++){
            int[] arrForRotate = new int[8*i+1];
            int idx = 0;
            for(int j=x-i; j<x+i; j++) arrForRotate[idx++] = A[y-i][j];
            for(int j=y-i; j<y+i; j++) arrForRotate[idx++] = A[j][x+i];
            for(int j=x+i; j>x-i; j--) arrForRotate[idx++] = A[y+i][j];
            for(int j=y+i; j>y-i; j--) arrForRotate[idx++] = A[j][x-i];

            arrForRotate[8*i] = arrForRotate[0];
            idx = 1;
            for(int j=x-i; j<x+i; j++) A[y-i][j] = arrForRotate[idx++];
            for(int j=y-i; j<y+i; j++) A[j][x+i] = arrForRotate[idx++];
            for(int j=x+i; j>x-i; j--) A[y+i][j] = arrForRotate[idx++];
            for(int j=y+i; j>y-i; j--) A[j][x-i] = arrForRotate[idx++];
        }
    }

    static int getA(){
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++){
            int rowSum = 0;
            for(int j=1; j<=m; j++) rowSum += A[i][j];
            min = Math.min(min, rowSum);
        }
        return min;
    }
}