import java.util.Scanner;
public class s3316 {
    static int bitmasking;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){
            int n = sc.nextInt();


            System.out.println("#"+tc+" "+n*cnt);
        }
    }

}
// 전날 1명 - 다음날 8개
// 전날 2명 - 다음날 12개
// 전날 3명 - 다음날 14개
// 전날 4명 - 다음날 15개