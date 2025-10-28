import java.util.Scanner;

// 첫 접근 : 끝에서 시작으로 하는 접근 굿
// 부족한 점 : 케이스 포함 문제 -> while문을 a!=b로 놓으면 a > b인 상태에서 같지않을때 처리가 안됨
public class bj16953 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        int cnt = 1;
        while (a < b) {
            if (b % 2 == 0) {
                b /= 2;
            } else if (b % 10 == 1) {
                b /= 10;
            } else {
                System.out.println(-1);
                return;
            }
            cnt++;
        }

        if (a == b) {
            System.out.println(cnt);
        } else {
            System.out.println(-1);
        }
    }
}
