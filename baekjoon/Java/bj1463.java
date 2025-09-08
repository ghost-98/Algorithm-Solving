import java.util.Scanner;
public class bj1463 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] num = new int[n+1]; // 조건에서 자유로운 역방향으로 1 -> n
        for(int i=2; i<=n; i++) num[i] = Integer.MAX_VALUE;

        for(int i=1; i<n; i++){
            if(i+1 <= n) num[i+1] = Math.min(num[i]+1, num[i+1]);
            if(i*2 <= n) num[i*2] = Math.min(num[i]+1, num[i*2]);
            if(i*3 <= n) num[i*3] = Math.min(num[i]+1, num[i*3]);
        }

        System.out.print(num[n]);
    }
}
