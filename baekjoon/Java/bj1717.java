import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// union-find의 union과 find 문제 : parent[]로 풂
// 최적화 없이도 가능
public class bj1717{
    static int[] parent;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1]; // parent[]가 핵심
        Arrays.fill(parent, -1); // -1 가리키면 루트 노드

        int ops, a, b;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            ops = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if(ops == 0) union(a, b);
            else if(ops == 1) check(a, b);
        }

        System.out.print(sb);
    }

    // union-find의 find는 보통 재귀로 구현
    static int find(int x){
        if(parent[x] == -1) return x;
        return find(parent[x]); // 재귀는 이런 식으로 처리해야 반환 오류 없음
        // return parent[x] = find(parent[x]); -> 경로 압축(find시 루트로 바로 연결) 최적화
    }

    static void union(int a, int b){
        // find 선행
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;

        parent[bRoot] = aRoot; // rank이용한 최적화 가능
    }

    static void check(int a, int b){
        if(find(a) == find(b)) sb.append("YES");
        else sb.append("NO");
        sb.append('\n');
    }
}