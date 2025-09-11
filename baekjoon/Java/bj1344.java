import java.util.Scanner;
public class bj1344 {
    static int[] notPrimeNum = {0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18};
    static double[] aScore, bScore;
    static long[][] comb = new long[19][19];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        // 조합수 미리 계산
        initComb();

        aScore = new double[19];
        bScore = new double[19];

        // 이항분포 이용해서 확률 계산
        for (int cnt = 0; cnt <= 18; cnt++) {
            aScore[cnt] = comb[cnt + (18 - cnt)][cnt] * Math.pow(a / 100.0, cnt) * Math.pow(1 - a / 100.0, 18 - cnt);
            bScore[cnt] = comb[cnt + (18 - cnt)][cnt] * Math.pow(b / 100.0, cnt) * Math.pow(1 - b / 100.0, 18 - cnt);
        }

        double aNotPrime = 0, bNotPrime = 0;
        for (int n : notPrimeNum) {
            aNotPrime += aScore[n];
            bNotPrime += bScore[n];
        }

        double ret = 1 - (aNotPrime * bNotPrime);
        System.out.printf("%.10f\n", ret);
    }

    // 조합 -> 파스칼 삼각형 이용해 미리 comb[][] dp 메모이제이션
    static void initComb() {
        for (int n = 0; n <= 18; n++) {
            comb[n][0] = comb[n][n] = 1;
            for (int k = 1; k < n; k++) {
                comb[n][k] = comb[n - 1][k - 1] + comb[n - 1][k];
            }
        }
    }
}
/* < dp 메모이제이션 >
* 조합을 메모이제이션하는 아이디어 (n = 18)
* 소수 연산을 많이 하면 오차가 커짐

* 내 기존 코드
* int[18] aScore, bScore에 a와 b 각각의 득점 수 별 확률을 저장 (비트마스킹으로 2^18 전체)
* -> double의 누적 오차가 커짐 + 2^18이 비효율적

* 개선 코드
* n = 18 까지의 조합을 파스칼 삼각형 이용해 미리 메모이제이션 해놓음

* 차이점 나는 비트 마스킹으로 조합 확률을 모든 경우에 += 했기때문에 오차가 커졌고, 개선된 방식은 18Cn을 한번에 구해놓고 확률을 한 번에 저장했기 때문에 오차 적음
*/