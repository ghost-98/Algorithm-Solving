import java.util.Scanner;
// 20m
// 아이디어 : 재귀 (사각형은 2134 재귀로 좁혀가면서 -> 사각형 안에 없으면 사각형 크기만큼 더하고, 있으면 좁혀 들어가기 n==0이면서 r과 c 같을 때까지)
// 궁금증 : System.exit(0) 안쓰고 재귀에서 결과 찾으면 main함수 종료시키는 법 -> boolean true 반환으로 찾는 즉시, 최상위 호출까지 true 보내서 끝내는 법
public class bj1074 {
    static int r, c, ret;
    static int[] dy = {0, 0, 1, 1};
    static int[] dx = {0, 1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 1 ≤ N ≤ 15, 2의 지수
        r = sc.nextInt();
        c = sc.nextInt();

        recur(0, 0, n);
    }

    // 재귀
    static void recur(int y, int x, int n) {
        // 해당 사각형 안에 없으면 사각형 크기만큼 결과에 더하고 리턴
        if(y > r || x > c || y + (1 << n) <= r || x + (1 << n) <= c) {
            ret += (1 << n) * (1 << n); // Math.pow(2, n);
            return;
        };

        if(n == 0 && y == r && x == c) {
            System.out.print(ret);
            System.exit(0);
        }

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i] * (1 << (n-1));
            int nx = x + dx[i] * (1 << (n-1));
            recur(ny, nx, n-1);
        }
    }
}
