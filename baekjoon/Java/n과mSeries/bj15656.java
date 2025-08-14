import java.util.Arrays;
import java.util.Scanner;

// 중복 순열
public class bj15656 {
    static int[] inputNum, ret;
    static int n, m;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        n = sc.nextInt();
        m = sc.nextInt();

        ret = new int[m];
        inputNum = new int[n];
        for(int i=0; i<n; i++) inputNum[i] = sc.nextInt();
        Arrays.sort(inputNum);

        perm(0);

        System.out.print(sb);
    }
    
    static void perm(int depth){
        if(depth == m){
            for(int i=0; i<m; i++) sb.append(ret[i]).append(" ");
            sb.append("\n");
            return;
        }

        for(int i=0; i<n; i++){
            ret[depth] = inputNum[i];
            perm(depth+1);
        }
    }
}
