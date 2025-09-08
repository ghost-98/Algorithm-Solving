import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.PriorityQueue;

// 3:52
public class bj1916 {
    static int n, m;
    static int[][] route; // 간선 수에 비해 노드가 적어서 인접 행렬
    static PriorityQueue<int[]> pq;
    static int[] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine()); // 1 ≤ N ≤ 1,000
        m = Integer.parseInt(br.readLine()); // 1 ≤ M ≤ 100,000

        route = new int[n+1][n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());  
            int depart = Integer.parseInt(st.nextToken());
            int arrive = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            route[depart][arrive] = cost;
        }

        st = new StringTokenizer(br.readLine());
        int departCity = Integer.parseInt(st.nextToken());
        int arriveCity = Integer.parseInt(st.nextToken());

        cost = new int[n+1];
        for(int i=1; i<=n; i++) cost[i] = Integer.MAX_VALUE;
        cost[departCity] = 0;

        pq = new PriorityQueue<>((a, b) -> a[1]-b[1]);
        pq.offer(new int[] {departCity, 0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int arrive = cur[0];
            int costToCity = cur[1];

            if(cost[arrive] < costToCity) continue;

            for(int i=1; i<=n; i++){
                int nextCity = route[arrive][i];
                if(nextCity == 0) continue;

                if(cost[i] > cost[arrive] + nextCity){
                    cost[i] = cost[arrive] + nextCity;
                    pq.offer(new int[] {i, cost[i]});
                }
            }
        }

        System.out.print(cost[arriveCity]);
    }
}
