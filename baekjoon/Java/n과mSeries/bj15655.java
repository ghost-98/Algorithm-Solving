import java.util.Arrays;
import java.util.Scanner;

// 조합
public class bj15655 {
    static int[] numArr, ret;
    static int n, m;
    static StringBuilder sb;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        n = sc.nextInt();
        m = sc.nextInt();
        numArr = new int[n];
        for(int i=0; i<n; i++) numArr[i] = sc.nextInt();
        Arrays.sort(numArr);

        ret = new int[m];
        comb(0, 0);

        System.out.print(sb);
    }

    static void comb(int depth, int start){
        if(depth == m){
            for(int i=0; i<m; i++) sb.append(ret[i]).append(" ");
            sb.append("\n");
            return;
        }
        
        for(int i=start; i<n-(m-depth-1); i++){
            ret[depth] = numArr[i];
            comb(depth+1, i+1);
        }
    }
}
