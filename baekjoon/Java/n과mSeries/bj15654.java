import java.util.Arrays;
import java.util.Scanner;

// 순열
public class bj15654 {
    static int[] numArr, ret;
    static int n, m;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        n = sc.nextInt();
        m = sc.nextInt();
        numArr = new int[n];
        for(int i=0; i<n; i++) numArr[i] = sc.nextInt();
        Arrays.sort(numArr);
        
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
            ret[depth] = numArr[i];
            visited[i] = true;   
            perm(depth+1);
            visited[i] = false;
        }
    }
}
