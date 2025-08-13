import java.util.Scanner;

public class s4796 {
    static int[] heights;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){
            int n = sc.nextInt();
            heights = new int[n+1];
            for(int i=0; i<n; i++) heights[i+1] = sc.nextInt();

            int ret = 0;

            boolean flag = false; // false는 상승, true는 하강
            int increaseCnt = 0, decreaseCnt = 0;
            int curHeight = heights[1];
            for(int i=2; i<=n; i++){
                if(!flag){ // 상승
                    if(curHeight > heights[i]){
                        flag = true;
                        decreaseCnt = 1;
                    }else increaseCnt++;
                }else{ // 하강
                    if(curHeight < heights[i]){
                        ret += increaseCnt * decreaseCnt;
                        flag = false;
                        increaseCnt = 1;
                        decreaseCnt = 0;
                    }else decreaseCnt++;
                }
                curHeight = heights[i];

                if(i == n) ret += increaseCnt * decreaseCnt;
            }

            System.out.println("#" + tc + " " + ret);
        }
    }
}
