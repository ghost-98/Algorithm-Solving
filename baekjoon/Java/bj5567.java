import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class bj5567 {
    public static int n, m;
    public static boolean[][] friendship = new boolean[501][501];
    public static boolean[] visited = new boolean[501];
    public static Deque<Integer> stk = new ArrayDeque<>();

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        for(int i=0; i<m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            friendship[x][y] = friendship[y][x] = true;
        }

        int ret = 0;
        for(int i=2; i<=n; i++){
            if(friendship[1][i]){
                stk.push(i);
                visited[i] = true;
                ret++;
            }
        }
        int size = stk.size();
        for(int i=0; i<size; i++){
            int tmp = stk.pop();
            for(int j=2; j<=n; j++){
                if(friendship[tmp][j] && !visited[j]){
                    visited[j] = true;
                    ret++;
                }
            }
        }

        System.out.print(ret);
    }
}
// stack.size()로 반복문을 돌린다면 내부에서 사이즈가 바뀌지 않는지 확인