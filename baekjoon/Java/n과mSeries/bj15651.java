import java.util.Scanner;

// 중복 순열, 출력때문에 시간 초과
public class bj15651 {
    static int[] numArr;
    static int n, m;
    static StringBuilder sb;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        n = sc.nextInt();
        m = sc.nextInt();
        
        numArr = new int[m];
        perm(0);

        System.out.print(sb);
    }

    static void perm(int depth){
        if(depth == m){
            for(int i=0; i<m; i++) sb.append(numArr[i]).append(" ");
            sb.append("\n");
            return;
        }
        
        for(int i=0; i<n; i++){
            numArr[depth] = i+1;
            perm(depth+1);
        }
    }
}
