import java.io.*;
import java.util.*;
// 그리디로 푸려다가 명제 부합X -> dfs 가지 치기
public class bj17136그리디x {
    static int ret = Integer.MAX_VALUE;
    static int[][] paper;
    static int[] colorPaperCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        paper = new int[10][10];
        colorPaperCnt = new int[6];
        for(int i=1; i<=5; i++) colorPaperCnt[i] = 5; // 1-base

        for(int i=0; i<10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++) paper[i][j] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0);

        System.out.println(ret == Integer.MAX_VALUE ? -1 : ret); // 삼항 연산자 활용
    }

    // 줄마다 탐색(2차원)
    // 붙이는 색종이 다르게 해서 dfs
    static void dfs(int y, int x, int used) {
        if (y == 10) { // 끝까지 탐색 완료
            ret = Math.min(ret, used);
            return;
        }

        if (x == 10) { // 다음 줄로 이동
            dfs(y + 1, 0, used);
            return;
        }

        if (paper[y][x] == 0) { // 이미 덮였거나 빈 칸이면 다음 칸
            dfs(y, x + 1, used);
            return;
        }

        // 아직 1이 남아있다면 색종이 붙여보기
        for (int size = 5; size >= 1; size--) {
            if (colorPaperCnt[size] > 0 && check(y, x, size)) {
                attach(y, x, size, true);   // 붙인다 (해당 영역 0으로 변경)
                colorPaperCnt[size]--;
                dfs(y, x + size, used + 1);
                attach(y, x, size, false);   // 되돌리기
                colorPaperCnt[size]++;
            }
        }
    }
    static boolean check(int y, int x, int size){
        if (y + size > 10 || x + size > 10) return false;
        for(int j=y; j<y+size; j++){
            for(int k=x; k<x+size; k++){
                if(paper[j][k] == 0) return false;
            }
        }
        return true;
    }

    static void attach(int y, int x, int size, boolean isGluing){
        for(int i=y; i<y+size; i++){
            for(int j=x; j<x+size; j++){
                if(isGluing) paper[i][j] = 0;
                else paper[i][j] = 1;
            }
        }
        return;
    }
}
