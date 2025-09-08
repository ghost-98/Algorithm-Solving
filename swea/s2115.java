import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 55m
// 접근하는 법 확실하게. 예외 없게

// 실수 요소
// 1. 처음에 두개의 열 조합으로 하려했으나 캐싱이나 백트 요소 너무 많음
// 2. 같은 행에서 동시에 일하는 경우 생각 않고, 서로 다른 열에서 일하는 경우만 구함(열마다 최대 꿀제곱합 갖는 일차원 배열)
// 3. rowCol담은 2차원 배열로 다시 고침

public class s2115 {
    static int n, m, c;
    static int[][] honeyBarrel;
    static int[][] rowColMaxHoney;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int t = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=t; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 3 ≤ N ≤ 10
            m = Integer.parseInt(st.nextToken()); // 1 ≤ M ≤ 5
            c = Integer.parseInt(st.nextToken()); // 10 ≤ C ≤ 30

            honeyBarrel = new int[n][n];

            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) honeyBarrel[i][j] = Integer.parseInt(st.nextToken()); 
            }

            rowColMaxHoney = new int[n][n-m+1];
            getMaxHoneyInRow();

            // 두 일꾼 선택: 완전탐색
            int ret = 0;
            for(int r1=0; r1<n; r1++){
                for(int c1=0; c1<=n-m; c1++){
                    for(int r2=r1; r2<n; r2++){
                        for(int c2=0; c2<=n-m; c2++){
                            if(r1 == r2 && Math.abs(c1 - c2) < m) continue; // 같은 행 겹치면 패스
                            ret = Math.max(ret, rowColMaxHoney[r1][c1] + rowColMaxHoney[r2][c2]);
                        }
                    }
                }
            }
            // 처음에 두개의 열 조합으로 하려했으나 캐싱이나 백트 요소 너무 많음

            System.out.println("#" + tc + " " + ret);
        }
    }

    static void getMaxHoneyInRow(){
        for(int row=0; row<n; row++){
            for(int startCol=0; startCol<=n-m; startCol++){
                int MaxM = 0;
                // 부분집합 비트마스크
                for(int i=1; i<(1<<m); i++){
                    int sum = 0;
                    int sumForCheck = 0;
                    for(int j=0; j<m; j++){
                        if((i & (1<<j)) != 0) sumForCheck += honeyBarrel[row][startCol+j];
                    }
                    if(sumForCheck > c) continue;

                    for(int j=0; j<m; j++){
                        if((i & (1<<j)) != 0) sum += honeyBarrel[row][startCol+j] * honeyBarrel[row][startCol+j];
                    }
                    MaxM = Math.max(MaxM, sum);
                }
                rowColMaxHoney[row][startCol] = MaxM;
            }
        }
    }
}
// 두 명의 일꾼, 각각 가로 M개 (일꾼 순서 상관x)
// 한 벌통에서 채취한 꿀은 하나의 용기에. 수익은 용기 제곱의 합
// 한 일꾼은 M개의 꿀을 한번에 채취하지만 c이하에서만 가능

// 완탐 최악 : t * nCm * nC2 * 한 일꾼의 벌통에서 최대 수확 구하기
// 완탐 최악 계산 : 50 * 252 * 45 => 넉넉히 75만 * 벌통 최대수확