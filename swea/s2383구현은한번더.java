import java.util.*;
import java.io.*;
// 아이디어 : 2(계단 수)^10(사람 최대 수)를 완탐하면 될 듯 
public class s2383구현은한번더 {
    static int n;
    static int[][] map;
    static List<int[]> people;
    static int[][] stairs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc <= t; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 4 ≤ N ≤ 10

            map = new int[n][n]; // 사람 수 : 10이하 자연수, 계단 수 : 2개, 2 ≤ 계단의 길이 ≤ 10
            people = new ArrayList<>();
            stairs = new int[2][3]; int idx = 0; // 계단 두개 정보 담는 idx보다 나은 방법?
            for(int r = 0; r < n; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < n; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    // 사람과 계단 정보 초기화
                    if(map[r][c] == 1) people.add(new int[] {r, c});
                    else if(map[r][c] != 0 && map[r][c] != 1) {
                        stairs[idx][0] = r; stairs[idx][1] = c; stairs[idx++][2] = map[r][c];
                    }
                }
            }

            int ret = Integer.MAX_VALUE;
            int peopleCnt = people.size();
            for(int mask = 0 ; mask < (1 << peopleCnt); mask++) ret = Math.min(ret, move(mask));

            System.out.println("#" + tc + " " + ret);
        }
    }

    static int move(int mask){
        List<Integer> stair1 = new ArrayList<>();
        List<Integer> stair2 = new ArrayList<>();
        
        // mask 기반으로 사람 배정
        for(int i = 0; i < people.size(); i++){
            int dist1 = Math.abs(stairs[0][0] - people.get(i)[0]) + Math.abs(stairs[0][1] - people.get(i)[1]);
            int dist2 = Math.abs(stairs[1][0] - people.get(i)[0]) + Math.abs(stairs[1][1] - people.get(i)[1]);
            if((mask & (1 << i)) == 0) stair1.add(dist1);
            else stair2.add(dist2);
        }

        // 계단별로 소요 시간 계산
        int time1 = calcTime(stair1, stairs[0][2]);
        int time2 = calcTime(stair2, stairs[1][2]);

        return Math.max(time1, time2);
    }

    static int calcTime(List<Integer> arrival, int len){
        if(arrival.isEmpty()) return 0;
        Collections.sort(arrival);

        List<Integer> endTime = new ArrayList<>();
        for(int i = 0; i < arrival.size(); i++){
            int start = arrival.get(i) + 1; // 도착 후 1분 대기
            if(endTime.size() >= 3 && endTime.get(0) > start) {
                // 계단 꽉 차있으면 가장 먼저 끝나는 사람 기다려야 함
                start = endTime.get(0);
                endTime.remove(0);
            }
            int finish = start + len;
            endTime.add(finish);
            Collections.sort(endTime); // 매번 endTime 정렬 말고 우선순위 큐 사용도 ㄱㅊ
        }
        return endTime.get(endTime.size() - 1);
    }
}
