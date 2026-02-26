import java.util.*;
// 32m
public class q14891 {
    static int[][] topni;
    static int[] head;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        topni = new int[4][8]; // 0-based
        head = new int[4]; // 0-based

        for(int i = 0; i < 4; i++) {
            String s = sc.next();

            // n극 0, s극 1
            for(int j = 0; j < 8; j++) {
                if(s.charAt(j) == '1') topni[i][j] = 1;
            }
        }

        int k = sc.nextInt();
        for(int i = 0; i < k; i++) {
            int num = sc.nextInt();
            int dir = sc.nextInt();

            // 돌리는 로직 - 재귀
            rotate(num - 1, dir, 0);
        }

        // 점수 계산
        int ret = 0;
        for(int i = 0; i < 4; i++) {
            ret += topni[i][head[i]] * (1 << i);
        }
        
        System.out.print(ret);
    }

    public static void rotate(int num, int dir, int from) {
        // 오른쪽 검사
        if(from != 1 && num < 3) {
            if(topni[num][(head[num] + 2) % 8] 
                != topni[num + 1][(head[num + 1] + 6) % 8]) {

                rotate(num + 1, -dir, -1);
            }
        }

        // 왼쪽 검사
        if(from != -1 && num > 0) {
            if(topni[num][(head[num] + 6) % 8] 
                != topni[num - 1][(head[num - 1] + 2) % 8]) {

                rotate(num - 1, -dir, 1);
            }
        }
        head[num] = (head[num] - dir + 8) % 8;
    }
}
