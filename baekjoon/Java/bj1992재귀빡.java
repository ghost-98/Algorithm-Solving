import java.util.Scanner;
// 1시간 걸림
// 재귀 문제인데, 재귀 설계 및 구현 안익숙
// 문제 잘못 읽어서 엄청 헤맴 : 나눌 필요 없으면 () 필요 없음. 맨 처음에도 같음
public class bj1992재귀빡{
    static StringBuilder sb;
    static boolean[][] video;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        video = new boolean[n][n];

        for(int r=0; r<n; r++){
            String col = sc.next();
            for(int c=0; c<n; c++){
                if(col.charAt(c)-'0' == 1) video[r][c] = true;
            }
        }

        sb = new StringBuilder();
        // 재귀 구조(4분면 탐색하다 걸리면 더 들어가는)
        recur(n, 0, 0);
        System.out.print(sb);
    }

    // 재귀 기획 하는게 익숙하지 않군
    static void recur(int n, int startY, int startX){
        // 기저 조건 필요 x
        int checkValue = check(n, startY, startX);
        if(checkValue != -1) {
            sb.append(checkValue);
            return;
        }

        sb.append('(');

        int half = n/2;
        recur(half, startY, startX);
        recur(half, startY, startX+half);
        recur(half, startY+half, startX);
        recur(half, startY+half, startX+half);

        sb.append(')');
    }

    // 사각형 내 다를때 : -1, 같은데 1로 같으면 :1, 0으로 같으면 0 이 세가지 상태인데 허접하지 않나?
    static int check(int size, int startY, int startX){
        boolean firstValue = video[startY][startX];
        for(int r=startY; r<startY+size; r++){
            for(int c=startX; c<startX+size; c++){
                if(video[r][c] != firstValue) return -1;
            }
        }

        if(firstValue) return 1;
        else return 0;
    }
}