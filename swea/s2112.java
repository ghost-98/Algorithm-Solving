import java.util.Scanner;
// 90m
// 비트마스킹으로 완탐 했으나 -> 적은 개수부터 바꿔나가면서 되면은 종료시키는 최적화
public class s2112 {
    static int[][] film, filmCopy;
    static int t, d, w, k;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){
            int ret = Integer.MAX_VALUE;
            d = sc.nextInt();
            w = sc.nextInt();
            k = sc.nextInt();
            
            film = new int[d][w];
            filmCopy = new int[d][w];
            for(int r=0; r<d; r++){
                for(int c=0; c<w; c++){
                    film[r][c] = sc.nextInt();
                    filmCopy[r][c] = film[r][c];
                }
            }
            /**
            잘못 푼 코드 적은 개수부터 시작해서 부합하면 종료하는 로직이어야 하는데! 최적화!!!!

            int[] changeD = new int[d];
            // 0~w까지 열 중 바꿀 열 정하기
            for(int i=0; i<(1<<d); i++){
                int changeCnt = 0;
                for(int j=0; j<d; j++){ // j가 바꿀 행
                    if((i & (1<<j)) == 1<<j){
                        changeD[changeCnt++] = j;
                    }
                }

                // 바꿀 열들 A로 바꿀 열 B로 바꿀 열 분류
                for(int j=0; j<(1<<changeCnt); j++){
                    for(int l=0; l<changeCnt; l++){ // change[D]가 바꿀 행
                        if((j & (1<<l)) == 1<<l){
                            for(int m=0; m<w; m++){
                                film[changeD[l]][m] = 1;
                            }
                        }else{
                            for(int m=0; m<w; m++){
                                film[changeD[l]][m] = 0;
                            }
                        }
                    }
                    // 확인
                    if(check() && ret > changeCnt) ret = changeCnt;
                    // 원복
                    for(int l=0; l<changeCnt; l++){
                        for(int m=0; m<w; m++) film[changeD[l]][m] = filmCopy[changeD[l]][m];
                    }
                }
            }
            **/
            int[] changeD = new int[d];
            for(int i=0; i<=d; i++){
                if(ret != Integer.MAX_VALUE) break;

                // d개 중에서 i개 선택
                // 선택된 i개의 행들을 각각 1or2로 바꿈(2^i번)
                // 체크 후 원복
                for(int j=0; j<(1<<d); j++){
                    if(Integer.bitCount(j) != i) continue; // 완탐해야해서 비효율적. 이 부분은 dfs로 하자

                    int changeCnt = 0;
                    for(int l=0; l<d; l++){
                        if((j & (1<<l)) == 1<<l) changeD[changeCnt++] = l;
                    }
                    
                    // 바꿀 열들 A로 바꿀 열 B로 바꿀 열 분류
                    for(int l=0; l<(1<<changeCnt); l++){
                        for(int m=0; m<changeCnt; m++){ // change[D]가 바꿀 행
                            if((l & (1<<m)) == 1<<m){
                                for(int n=0; n<w; n++){
                                    film[changeD[m]][n] = 1;
                                }
                            }else{
                                for(int n=0; n<w; n++){
                                    film[changeD[m]][n] = 0;
                                }
                            }
                        }
                        // 확인
                        if(check() && ret > changeCnt){
                            ret = changeCnt;
                        }
                        // 원복
                        for(int m=0; m<changeCnt; m++){
                            for(int n=0; n<w; n++) film[changeD[m]][n] = filmCopy[changeD[m]][n];
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + ret);
        }
    }

    static boolean check(){
        for(int i=0; i<w; i++){
            boolean flag = false;
            int cnt = 1;
            int cur = film[0][i];
            for(int j=1; j<d; j++){
                if(cur == film[j][i]) cnt++;
                else{
                    cur = film[j][i];
                    cnt = 1;
                }
                if(cnt == k){
                    flag = true;
                    break;
                }
            }
            if(!flag) return flag;
        }
        return true;
    }
}