import java.util.Scanner;
// 40m
// 유니온 파인드로 잘못 풂
// 1-based로 통일
// n이 작다는건 완탐이 가능하다는 추측 -> 조합(완탐 없이 각각 다 연결되어 있는 뭉텅이 골라내는 방법 있나?)
public class bj17471삽질 {
    static int n;
    static int[] people, parent, rank;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // 구역 개수
        n = sc.nextInt(); // 2 <= n <= 10

        // 인구 수
        people = new int[n+1];
        for(int i=1; i<=n; i++) people[i] = sc.nextInt();

        // 유니온 파인드 위한 배열 초기화 및 선언
        parent = new int[n+1];
        for(int i=1; i<=n; i++) parent[i] = i;
        rank = new int[n+1];

        // 인접 구역 정보
        for(int i=1; i<=n; i++){
            int nearCnt = sc.nextInt();
            for(int j=0; j<nearCnt; j++){
                int nearArea =  sc.nextInt();

                if(find(i) == find(nearArea)) continue; // 입력에서 같은 연결 두번 주어짐
                union(i, nearArea);
            }
        }

        // 조합 완탐 - 비트 마스킹으로 그룹 분리 (1과 0)
        int ret = Integer.MAX_VALUE;
        for(int grpMask=1; grpMask<(1<<n); grpMask++){ // 아무 지역도 없는 그룹 없으므로 1부터
            if(!checkConnect(grpMask)) continue;

            // checkConnect와 getCntDiff의 일부 중복 로직 해소 바람
            int cntDiff = getCntDiff(grpMask);
            if(ret > cntDiff) ret = cntDiff;
        }

        System.out.print(ret);
    }

    // 두 그룹 각각의 연결 확인(둘 다 각각 연결되어야 true)
    static boolean checkConnect(int mask){
        int Aprnt = 0; // 초깃값, 이 로직 허접한데,, 개선 바람
        int Bprnt = 0;
        for(int i=0; i<n; i++){
            if((mask & 1<<i) != 0){
                if(Aprnt == 0) Aprnt = find(i);
                else if(Aprnt != find(i)) return false;
            }else{
                if(Bprnt == 0) Bprnt = find(i);
                else if(Bprnt != find(i)) return false;                
            }
        }        

        return true;
    }

    // 그룹간 인구 차이
    static int getCntDiff(int mask){
        int Asum = 0;
        int Bsum = 0;
        for(int i=0; i<n; i++){
            if((mask & 1<<i) != 0) Asum += parent[i];
            else Bsum += parent[i];
        }

        return Math.abs(Asum - Bsum);
    }

    // find 최적화 함
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // union - int[] rank로 최적화 함
    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) return;

        if(rank[rootX] > rank[rootY]) parent[rootY] = rootX;
        else if(rank[rootX] < rank[rootY]) parent[rootX] = rootY;
        else{
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}
