import java.util.Scanner;
// 51m
public class s4008 {
    static int n, maxRet, minRet;
    static int[] nums, ops, selected;
    static boolean[] visited;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int[] opCnt = new int[4];

        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){ // t <= 50
            maxRet = Integer.MIN_VALUE;
            minRet = Integer.MAX_VALUE;

            n = sc.nextInt(); // n <= 12
            nums = new int[n];

            for(int i=0; i<4; i++) opCnt[i] = sc.nextInt();
            for(int i=0; i<n; i++) nums[i] = sc.nextInt();

            // 연산자의 모든 조합 순서 : (n-1)!/opCnt[0]!/opCnt[1]!/opCnt[2]!/opCnt[3]!개
            ops = new int[n-1];
            int idx = 0;
            for(int i=0; i<4; i++){
                for(int j=0; j<opCnt[i]; j++){
                    ops[idx++] = i+1;
                }
            }

            visited = new boolean[n-1];
            selected = new int[n-1];
            permute(0);

            int ret = maxRet-minRet;
            System.out.println("#" + tc + " " + ret);
        }      
    }

    // 중복 방지 순열
    static void permute(int depth){
        if(depth == n-1){
            int eval = evaluate();
            maxRet = Math.max(maxRet, eval);
            minRet = Math.min(minRet, eval);
            return;
        }

        int prev = -1; // 같은 깊이에서 같은 연산자 또 사용 방지
        for(int i=0; i<n-1; i++){
            if(!visited[i] && ops[i] != prev){
                visited[i] = true;
                selected[depth] = ops[i];
                prev = ops[i];
                permute(depth + 1);
                visited[i] = false;
            }
        }
    }

    static int evaluate(){
        int cur = nums[0];
        for(int i=0; i<n-1; i++){
            if(selected[i] == 1) cur += nums[i+1];
            else if(selected[i] == 2) cur -= nums[i+1];
            else if(selected[i] == 3) cur *= nums[i+1];
            else if(selected[i] == 4) cur /= nums[i+1];
        }
        
        return cur;
    }
}