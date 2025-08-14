import java.util.Arrays;
import java.util.Scanner;

// 비내림차순 조합
public class bj15657 {
    static int n, m;
    static int[] inputNum, ret;
    static StringBuilder sb;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        n = sc.nextInt();
        m = sc.nextInt();
        inputNum = new int[n];
        for(int i=0; i<n; i++) inputNum[i] = sc.nextInt();
        Arrays.sort(inputNum);

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

        for(int i=start; i<n; i++){
            ret[depth] = inputNum[i];
            comb(depth+1, i);
        }
    }
}
