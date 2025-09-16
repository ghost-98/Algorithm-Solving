import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;
// 20m
public class bj16236x {
    static int n;
    static int sharkSize, sharkFeed;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        int[][] space = new int[n][n]; // 공간 상태
        List<List<int[]>> fishPos = new ArrayList<>(); // 물고기들 크기별 위치 
        for(int i=0; i<7; i++) fishPos.add(new ArrayList<>());

        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                space[r][c] = sc.nextInt(); 
                if(space[r][c] != 0 && space[r][c] != 9) fishPos.get(space[r][c]).add(new int[] {r, c});
            }
        }

        sharkSize = 2; sharkFeed = 0; // 상어 크기, 상어가 현재 크기에서 먹은 물고기 수
        // 먹는턴마다(있는지 조회) 자기 보다 작은 fishPos 순회하면서 가장 거리 짧은거 먹으러 다니기 -> 플로이드 워셜

        // 상어 초기 위치 포함, 각 위치를 노드로 보고

    }
}
// 같으면 지나감, 크면 먹으면서 지나감, 작으면 못 감