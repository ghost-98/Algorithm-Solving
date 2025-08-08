import java.util.Scanner;

// 12m
public class bj2961 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] sour = new int[n];
        int[] bitter = new int[n];

        for(int i=0; i<n; i++){
            sour[i] = sc.nextInt();
            bitter[i] = sc.nextInt();
        }

        int ret = (int)1e9;
        for(int i=1; i<(1<<n); i++){
            int totalSour = 1;
            int totalBitter = 0;
            for(int j=0; j<n; j++){
                if((1<<j & i) == 1<<j){
                    totalSour *= sour[j];
                    totalBitter += bitter[j];
                }
            }
            int diff = Math.abs(totalSour - totalBitter);
            if(ret > diff) ret = diff;
        }

        System.out.print(ret);
    }
}
// 비트마스킹 외 다른 방법 해보기