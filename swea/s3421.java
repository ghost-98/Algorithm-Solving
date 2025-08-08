import java.util.Scanner;

// 37, 0based든 1based든 명확히 지킬 것. 디버깅 시간 오래 걸림
public class s3421 {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++){
            int n = sc.nextInt();
            int m = sc.nextInt();

            boolean[][] comb = new boolean[n+1][n+1];
            for(int i=0; i<m; i++){
                int a = sc.nextInt();
                int b = sc.nextInt();

                comb[a][b] = true;
                comb[b][a] = true;
            }

            int ret = 0;
            for(int i=0; i<(1<<n); i++){
                boolean[] ingredient = new boolean[n+1];
                for(int j=0; j<n; j++){
                    if((1<<j & i) == 1<<j){
                        ingredient[j+1] = true;
                    }
                }

                // 조합 안 맞으면 노카운트
                boolean flag = true;
                for(int j=1; j<=n; j++){
                    if(ingredient[j]){
                        for(int k=j+1; k<=n; k++){
                            if(ingredient[k] && comb[j][k]){
                                flag = false;
                                break;
                            }
                        }
                        if(!flag) break; // 얘때매 시간초과 안걸리긴 했는데, 로직이 조잡함
                    }
                }
                if(flag) ret++;
            }

            System.out.println("#" + tc + " " + ret);
        }
    }
}