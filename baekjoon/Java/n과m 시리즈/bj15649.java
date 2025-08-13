import java.util.Scanner;

public class bj15649 {
    static int[] numArr;
    static boolean[] visited;
    static int n, m;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        
        visited = new boolean[n];
        numArr = new int[m];

        combination(0);
    }

    static void combination(int depth){
        if(depth == m){
            for(int i=0; i<m; i++) System.out.print(numArr[i] + " ");
            System.out.println();
            return;
        }
        
        for(int i=numArr[depth-1]; i<n-(m-depth); i++){
            if(visited[i]) continue;
            visited[i] = true;
            numArr[depth] = i+1;
            combination(depth+1);
            visited[i] = false;
        }

    }
}
