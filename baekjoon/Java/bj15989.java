import java.util.Scanner;

public class bj15989 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int i=0; i<T; i++){
            int n = sc.nextInt();

            int sum = 0;
            // 최소 자릿수 ~ 최대 자릿수에서 분배
            for(int j=n; j>=(n-1)/3+1; j--){
                // n-j를 잘 분배??
                if(n-j > j) sum -= (n-2*j); // 자리보다 남은 개수 많으면 처리
                sum += (n-j)/2+1; // 자리보다 남은 개수 같거나 적을때 처리
            }

            System.out.println(sum);
        }
    }
}

// n <= 10000
// 최소 : (n-1)/3 + 1개 (3포함)
// 최대 : n개