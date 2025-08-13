import java.util.Scanner;

// 골드 1임..
// MOD(나머지 연산) + 페르마의 소정리
public class bj16134X {
    static final int MOD = 1000000007;
    static long retMom = 1, retSon = 1;
    static int n, r;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        r = sc.nextInt();

        if(n == 0){
            System.out.print(n);
            return;
        }

        // 분모 : n!
        for(int i=1; i<=n; i++) retMom = retMom * i % MOD;
        // 분자 : r!, (n-r)!
        for(int i=1; i<=r; i++) retSon = retSon * i % MOD;
        for(int i=1; i<=n-r; i++) retSon = retSon * i % MOD;
        
        // 페르마 소정리
        System.out.print(retMom * pow(retSon, MOD-2) % MOD);
    }

    // Math.pow는 파라미터와 리턴타입 double이라 정밀도 깨짐
    static long pow(long a, long b) {
        long result = 1;
        while (b > 0) {
            if ((b & 1) == 1) result = (result * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return result;
    }
}