import java.util.Scanner;

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

        combination(0, 1);
        System.out.print(ret);
    }

    static void combination(int depth, int start){
        if(depth == m){
            ret++;
            return;
        }

        for(int i=start; i<=n-(m-depth)+1; i++){
            combination(depth+1, i+1);
        }
    }
}
