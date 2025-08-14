import java.util.Scanner;

// 비내림차순 조합
public class bj15652 {
    static int[] numArr;
    static int n, m;
    static StringBuilder sb;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        n = sc.nextInt();
        m = sc.nextInt();
        
        numArr = new int[m];
        comb(0, 1);

        System.out.print(sb);
    }

    static void comb(int depth, int start){
        if(depth == m){
            for(int i=0; i<m; i++) sb.append(numArr[i]).append(" ");
            sb.append("\n");
            return;
        }
        
        for(int i=start; i<=n; i++){
            numArr[depth] = i;
            comb(depth+1, i);
        }
    }
}
