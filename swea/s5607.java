import java.io.*;
import java.util.*;
// 이항계수 정리로 다 계산해서 저장한 후 접근하려 했는데 n이 너무 큼
// 
public class s5607 {
    static long MOD = 1234567891;
    static long[] fact;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int maxN = 0;
        int[][] queries = new int[T][2];
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            queries[t][0] = n;
            queries[t][1] = r;
            maxN = Math.max(maxN, n);
        }

        // 팩토리얼 전처리
        fact = new long[maxN + 1];
        fact[0] = 1;
        for (int i = 1; i <= maxN; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int n = queries[t][0];
            int r = queries[t][1];
            long ans = (fact[n] * modInverse(fact[r], MOD) % MOD * modInverse(fact[n - r], MOD)) % MOD;
            sb.append("#").append(t + 1).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    // 빠른 거듭제곱으로 모듈러 역원 계산
    static long modInverse(long a, long mod) {
        return pow(a, mod - 2, mod);
    }

    static long pow(long a, long b, long mod) {
        long res = 1;
        a %= mod;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return res;
    }
}