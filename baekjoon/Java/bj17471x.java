import java.util.*;
// 어떻게 두 그룹으로 나눌까? (각 그룹은 모두 연결되어 있게) : 신장 트리에서 아무 간선이나 썰면 되잖아 (신장트리가 여러개 가능해서 다 순회는x)
// 비트마스크 + dfs(각 뭉탱이 연결 확인)
public class bj17471x {
    static int n;
    static int[] peopleCnt;
    static boolean[][] adj;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        peopleCnt = new int[n+1];
        for(int i=1; i<=n; i++) peopleCnt[i] = sc.nextInt();

        adj = new boolean[n+1][n+1];
        for(int i=1; i<=n; i++){
            int cnt = sc.nextInt();
            for(int j=0; j<cnt; j++){
                int k = sc.nextInt();
                adj[i][k] = true;
            }
        }

        int ret = Integer.MAX_VALUE;
        boolean divFlag = false;
        for(int mask=1; mask<(1<<n)-1; mask++){
            if(check(mask)){
                int diff = countPeopleDiff(mask);
                if(diff < ret) ret = diff;
                divFlag = true;
            }
        }

        System.out.println(divFlag ? ret : -1);
    }

    static boolean check(int mask){
        boolean[] visited = new boolean[n+1];

        // 그룹1
        int start1 = -1;
        for(int i=0; i<n; i++){
            if((mask & (1<<i)) != 0){
                start1 = i+1;
                break;
            }
        }
        if(start1 == -1) return false;
        dfs(start1, mask, true, visited);
        for(int i=0; i<n; i++){
            if((mask & (1<<i)) != 0 && !visited[i+1]) return false;
        }

        // 그룹2
        int start2 = -1;
        for(int i=0; i<n; i++){
            if((mask & (1<<i)) == 0){
                start2 = i+1;
                break;
            }
        }
        if(start2 == -1) return false;
        dfs(start2, mask, false, visited);
        for(int i=0; i<n; i++){
            if((mask & (1<<i)) == 0 && !visited[i+1]) return false;
        }

        return true;
    }

    static void dfs(int cur, int mask, boolean group, boolean[] visited){
        visited[cur] = true;
        for(int next=1; next<=n; next++){
            if(!adj[cur][next]) continue;
            if(visited[next]) continue;
            if(group && ((mask & (1<<(next-1))) == 0)) continue;
            if(!group && ((mask & (1<<(next-1))) != 0)) continue;
            dfs(next, mask, group, visited);
        }
    }

    static int countPeopleDiff(int mask){
        int a=0, b=0;
        for(int i=0; i<n; i++){
            if((mask & (1<<i)) != 0) a += peopleCnt[i+1];
            else b += peopleCnt[i+1];
        }
        return Math.abs(a-b);
    }
}