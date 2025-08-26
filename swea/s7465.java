import java.util.Arrays;
import java.util.Scanner;

// 무리 중 가장 작은 번호가 대표자. parent[i] == -1인 대표자 = 무리 개수
// 기존 코드는 주석 처리 부분 살리고 22행 없고, find와 union 없이 구현했는데 : 안되는 이유 - 덮어 씌워질 수 있음
public class s7465 {
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){
            int n = sc.nextInt();
            int m = sc.nextInt();

            parent = new int[n+1];
            Arrays.fill(parent, -1);

            for(int i=0; i<m; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                union(x, y);
                /**
                if(x < y) parent[y] = x;
                else parent[x] = y;
                **/
            }

            int ret=0;
            for(int i=1; i<n+1; i++){
                if(parent[i]==-1) ret++;
            }

            System.out.println("#"+tc+" "+ret);
        }
    }

    static int find(int x){
        if(parent[x] == -1) return x;
        return parent[x] = find(parent[x]);
    }   

    static void union(int x, int y){
        int px = find(x);
        int py = find(y);

        if(px == py) return;
        if(px < py) parent[py] = px;
        else parent[px] = py;
    }
}
