import java.util.Scanner;

// 조합 (시간 복잡도?)
// n이 커서 비트 마스킹 불가 2^100 순차탐색 안됨
// 
public class bj2407 {
    static int[] numArr;
    static boolean[] visited;
    static int n, m;
    static long ret=0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
         
        visited = new boolean[n];
        numArr = new int[m];

        comb(0, 1);
        System.out.print(ret);
    }

    static void comb(int depth, int start){
        if(depth == m){
            ret++;
            return;
        }

        for(int i=start; i<=n-(m-depth)+1; i++){
            comb(depth+1, i+1);
        }
    }
}
