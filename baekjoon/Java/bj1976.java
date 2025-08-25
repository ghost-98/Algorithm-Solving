import java.util.Arrays;
import java.util.Scanner;
// 1:32
public class bj1976 {
    static int n, m;
    static int[] parent;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // <= 200
        m = sc.nextInt(); // <= 1000

        parent = new int[n+1];
        Arrays.fill(parent, -1);

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int check = sc.nextInt();
                if(check == 1) union(i+1, j+1); // 그냥 union 하는 것. 최적화 위해선 rank 도입
            }
        }

        int[] trip = new int[m];
        for(int i=0; i<m; i++) trip[i] = sc.nextInt();

        boolean flag = true;
        for(int i=0; i<m-1; i++){
            if(find(trip[i]) != find(trip[i+1])){
                flag = false;
                break;
            }
        }

        if(flag) System.out.print("YES");
        else System.out.print("NO");

    }

    static int find(int x){
        if(parent[x] == -1) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot == yRoot) return;

        parent[yRoot] = xRoot;
    }
}

