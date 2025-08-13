import java.util.Scanner;

public class s1486 {
    static int[] heights;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){
            int ret = Integer.MAX_VALUE;
            int n = sc.nextInt();
            int b = sc.nextInt();
            heights = new int[n];

            for(int i=0; i<n; i++) heights[i] = sc.nextInt();

            for(int i=1; i<(1<<n); i++){
                int tmpSum = 0;
                for(int j=0; j<n; j++){
                    if((1<<j & i) == 1<<j) tmpSum += heights[j];
                }
                if(tmpSum >= b && ret > tmpSum) ret = tmpSum;
            }
            
            ret -= b;
            System.out.println("#" + tc + " " + ret);
        }
    }
}
