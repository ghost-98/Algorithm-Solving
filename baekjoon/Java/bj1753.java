import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class bj1753 {
    static int V, E, K;
    static int[] distance;
    static List<List<int[]>> edge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        edge = new ArrayList<>();
        for(int i=0; i<V+1; i++) edge.add(new ArrayList<>());

        // 간선 값 입력 받기
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edge.get(u).add(new int[] {v, w});
        }

        // 거리 배열 초기화
        distance = new int[V+1];
        for(int i=1; i<=V; i++) distance[i] = Integer.MAX_VALUE;
        distance[K] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1]-b[1]);
        pq.offer(new int[] {K, 0});

        while(!pq.isEmpty()){
            // 현재 노드
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curNodeDistance = cur[1];

            if(distance[curNode] < curNodeDistance) continue; // pq 넣은 뒤 갱신 반영

            // 현재 노드와 연결된 노드 조회
            for(int[] next : edge.get(curNode)){
                int nextNode = next[0];
                int weight = next[1];

                if(distance[nextNode] > curNodeDistance + weight){
                    distance[nextNode] = curNodeDistance + weight;
                    pq.offer(new int[] {nextNode, distance[nextNode]});
                }
            }
        }

        for(int i=1; i<=V; i++){
            if(distance[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(distance[i]);
        }
    }
}
