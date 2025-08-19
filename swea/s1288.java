import java.util.Scanner;
public class s1288 {
    static int bitmasking;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){
            int n = sc.nextInt();
            
            bitmasking = 0;
            int cnt = 0;
            while(!check(bitmasking)){
                cnt++;
                int tmp = n*cnt;
                while(tmp > 0){
                    bitmasking |= (1<<tmp%10);
                    tmp /= 10;
                }
            }

            System.out.println("#"+tc+" "+n*cnt);
        }
    }

    static boolean check(int bitmasking){
        return(bitmasking & ((1<<10)-1)) == ((1<<10)-1);
    }
}
