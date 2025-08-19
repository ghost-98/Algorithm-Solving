import java.util.Scanner;
public class s10726 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){
            int n = sc.nextInt();
            int m = sc.nextInt();

            if(check(m, n)) System.out.println("#"+tc+" ON");
            else System.out.println("#"+tc+" OFF");
        }
    }

    static boolean check(int m, int n){
        int compNum = 0;
        for(int i=0; i<n; i++) compNum += (1<<i);
        if((m & compNum) == compNum) return true;
        else return false;
    }
}
