import java.util.Arrays;
import java.util.Scanner;

// 중복이 허용되는 수열의 순열
// 중복되는 수열 처리 로직x
public class bj15663x {
    static int n, m;
    static int[] inputNum, ret;
    static boolean[] visited;
    static StringBuilder sb;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        n = sc.nextInt();
        m = sc.nextInt();
        inputNum = new int[n];
        for(int i=0; i<n; i++) inputNum[i] = sc.nextInt();
        Arrays.sort(inputNum);

        visited = new boolean[n];
        ret = new int[m];
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
            if(visited[i]) continue;
            ret[depth] = inputNum[i];
            visited[i] = true;
            perm(depth+1);
            visited[i] = false;
        }
    }
}
