import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 20m + a
// 문제점
// 처음 로직은 색종이 전체가 단색일 때를 고려하지 않은 로직.. -> 개선
// 뭉텅이의 색이 모두 같지 않을때, 모두 흰색, 모두 파란색일때 세 상태로 구분해서 int 반환함 -> 개선
class bj2630문제점읽기만 {
    static int white, blue;
    static boolean[][] paper; // 파란색 true, 흰색 white
    static int[] dy={0, 0 ,1 ,1}, dx={0, 1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        paper = new boolean[n][n];
        for(int r=0; r<n; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<n; c++){
                int color = Integer.parseInt(st.nextToken());
                if(color == 1) paper[r][c] = true;
                else if(color == 0) paper[r][c] = false;
            }
        }

        solve(0, 0, n);

        System.out.print(white + "\n" + blue);
    }

    // 재귀. 뭉텅이 체크 후 
    static void solve(int startY, int startX, int size){
        if(check(startY, startX, size)){
            if(paper[startY][startX]) blue++;
            else white++;
            return;
        }
        
        size /= 2;
        for(int i=0; i<4; i++) solve(startY + size*dy[i], startX + size*dx[i], size);
    }

    // 한 뭉텅이 체크
    static boolean check(int y, int x, int size){
        boolean flag = paper[y][x]; // 더 나은 방법 X 
        for(int r=y; r<y+size; r++){
            for(int c=x; c<x+size; c++){
                if(flag ^ paper[r][c]) return false; // xor 연산
            }
        }

        return true;
    }
}