import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// 1-based로 통일
// n이 작다는건 완탐이 가능하다는 추측 -> 조합(완탐 없이 각각 다 연결되어 있는 뭉텅이 골라내는 방법 있나?)

// 배운점
// 1그룹과 2그룹 각각의 연결성 체크에 대한 가설이 틀림 -> 전제가 모든 상황에 맞는지 점검할 것

// 개선점
// checkConnect내 체크 2회 로직 + checkConnect와 getCntDiff 중복 로직 -> 로직 설계 변경
public class bj17471 {
    static int n;
    static int[] people;
    static boolean[] visited;
    static List<List<Integer>> connect;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // 구역 개수
        n = sc.nextInt(); // 2 <= n <= 10

        // 인구 수
        people = new int[n+1];
        for(int i=1; i<=n; i++) people[i] = sc.nextInt();

        // 도시간 연결 정보 담을 이중 리스트
        connect = new ArrayList<>();
        for(int i=0; i<= n; i++) connect.add(new ArrayList<>());

        // 인접 구역 정보
        for(int i=1; i<=n; i++){
            int nearCnt = sc.nextInt();
            for(int j=0; j<nearCnt; j++){
                int nearArea =  sc.nextInt();
                connect.get(i).add(nearArea);
            }
        }

        // 조합 완탐 - 비트 마스킹으로 그룹 분리 (1과 0)
        int ret = Integer.MAX_VALUE;
        for(int grpMask=1; grpMask<(1<<n); grpMask++){ // 아무 지역도 없는 그룹 없으므로 1부터
            if(!checkConnect(grpMask)) continue;
            ret = Math.min(ret, getCntDiff(grpMask)); // 이거 많이 쓰자 if-else 대신
        }

        System.out.println(ret == Integer.MAX_VALUE ? -1 : ret); // 두 그룹으로 못 나누는 경우
    }

    // 두 그룹 각각의 연결 확인(둘 다 각각 연결되어야 true)
    static boolean checkConnect(int mask){
        // 1그룹 체크
        visited = new boolean[n+1];
        for(int i=1; i<=n; i++){
            if((mask & 1<<(i-1)) != 0){
                dfs(i, mask, true);
                for(int j=0; j<n; j++){
                    if((mask & 1<<j) != 0 && !visited[j+1]) return false;  
                }
                break;
            }
        }

        // 2그룹 체크
        visited = new boolean[n+1];
        for(int i=1; i<=n; i++){
            if((mask & 1<<(i-1)) == 0){
                dfs(i, mask, false);
                for(int j=0; j<n; j++){
                    if((mask & 1<<j) == 0 && !visited[j+1]) return false;  
                }               
                break;
            }
        }

        return true;
    }

    // 연결 확인 dfs
    static void dfs(int i, int mask, boolean isGrp1){
        visited[i] = true;

        for(int connectedArea : connect.get(i)){
            if(visited[connectedArea]) continue;
            
            if(isGrp1 && (mask & 1<<(connectedArea-1)) == 0) continue;
            if(!isGrp1 && (mask & 1<<(connectedArea-1)) != 0) continue;

            dfs(connectedArea, mask, isGrp1);
        }
    }

    // 그룹간 인구 차이
    static int getCntDiff(int mask){
        int Asum = 0;
        int Bsum = 0;
        for(int i=1; i<=n; i++){
            if((mask & 1<<(i-1)) != 0) Asum += people[i];
            else Bsum += people[i];
        }

        return Math.abs(Asum - Bsum);
    }
}
