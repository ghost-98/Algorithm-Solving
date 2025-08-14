import java.util.Scanner;

// 비트마스킹 가능
// 조합 가능 (1Cn + ... + nCn)
// dp 가능
public class s1486y {
    static int[] heights;
    static int n, b, tmpSum, minSum;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){
            n = sc.nextInt();
            b = sc.nextInt();
            heights = new int[n];

            for(int i=0; i<n; i++) heights[i] = sc.nextInt();
            /** 비트마스킹
            for(int i=1; i<(1<<n); i++){
                int tmpSum = 0;
                for(int j=0; j<n; j++){
                    if((1<<j & i) == 1<<j) tmpSum += heights[j];
                }
                if(tmpSum >= b && ret > tmpSum) ret = tmpSum;
            }
            ret -= b;
            **/

            tmpSum = 0;
            minSum = Integer.MAX_VALUE;
            comb(0, 0);
    
            System.out.println("#" + tc + " " + (minSum-b));
        }
    }

    static void comb(int depth, int tmpSum){
        // 가지치기 된 효율화 로직
        if(tmpSum >= b) { 
            if(tmpSum < minSum) minSum = tmpSum;
            return; 
        }
        if(depth == n) return;
        /** 기존 코드
        if(depth == n){
            if(tmpSum >= b && minSum > tmpSum) minSum = tmpSum;
            return;
        }
        **/

        comb(depth+1, tmpSum + heights[depth]);
        comb(depth+1, tmpSum);
    }
    /** 조합2
    static void comb2(int depth, int start){
        if(tmpSum >= b) { 
            minSum = Math.min(minSum, tmpSum);
            return; 
        }
        if(depth == n) return;

        for(int i=start; i<n; i++){
            tmpSum += heights[i];
            comb2(depth + 1, i + 1);
            tmpSum -= heights[i];
        }
    }
    **/
}
