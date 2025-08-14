import java.util.Scanner;

// 조합
public class bj15650 {
    static int[] numArr;
    static int n, m;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        
        numArr = new int[m];

        comb(0,1);
    }

    static void comb(int depth, int start){
        if(depth == m){
            for(int i=0; i<m; i++) System.out.print(numArr[i] + " ");
            System.out.println();
            return;
        }
        
        for(int i=start; i<=n-(m-depth-1); i++){
            numArr[depth] = i;
            comb(depth+1, i+1);
        }
    }
}
