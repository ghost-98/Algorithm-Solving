import java.io.*;
import java.util.*;
// bfs 떠올렷는데, 갱신 보장은 앞에 나온게 짧음 (가중치 1)
// 행렬 or 리스트 -> 리스트가 효율적  +  Integer.MAX_VALUE 확인으로 visited 대체 가능
public class bj18352 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 2 ≤ N ≤ 300,000
        int m = Integer.parseInt(st.nextToken()); // 1 ≤ M ≤ 1,000,000
        int k = Integer.parseInt(st.nextToken()); // 1 ≤ K ≤ 300,000
        int x = Integer.parseInt(st.nextToken()); // 1 ≤ X ≤ N

        List<List<Integer>> road = new ArrayList<>();
        for(int i = 0; i <= n; i++) road.add(new ArrayList<>());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            road.get(a).add(b);
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[x] = 0;
        
        // 먼저 나온게 최단 보장
        Queue<Integer> q = new ArrayDeque<>();
        q.add(x);

        while(!q.isEmpty()) {
            int start = q.poll();
            for(int next : road.get(start)) {
                if(dist[next] == Integer.MAX_VALUE) {
                    dist[next] = dist[start] + 1;
                    q.add(next);
                }
            }
        }

        List<Integer> ret = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            if(dist[i] == k) ret.add(i);
        }

        Collections.sort(ret);

        if(ret.size() == 0) System.out.println(-1);
        else {
            for(int i = 0; i < ret.size(); i++) System.out.println(ret.get(i));
        }
    }
}