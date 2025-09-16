import java.util.Scanner;

// 수학 - 경우를 직접 해나가보며 피보나치 수열이라는 걸 알아야 -> 피보나치 수열인지 몰랏..
public class bj2302부족 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        // 피보나치 수열 40까지
        int[] fibo = new int[41];
        fibo[0] = 1; fibo[1] = 1;
        for(int i = 2; i <= 40; i++) fibo[i] = fibo[i - 1] + fibo[i - 2];

        int ret = 1;
        int prev = 0;
        for(int i = 0; i < m; i++) {
            int cur = sc.nextInt();
            ret *= fibo[cur - prev - 1];
            prev = cur;
        }

        System.out.print(ret * fibo[n - prev]);
    }
}