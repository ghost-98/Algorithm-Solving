import java.util.*;
import java.io.*;
// 25m 틀림
/*
 * < 내 생각 >
 * 그냥 간단한 완탐 문제로 보이나, n과 m의 범위를 고려하면 최적화 필요(완탐 최악 20억) -> DP의 메모이제이션
 * dp[start][end]의 2차원 메모이제이션 배열 만들어서 처음에 다 저장해놓고 접근해서 쓰겠다는 생각
 * 
 * <실수>
 * 기존 아이디어 : start - end - 팰린드롬 판별의 3중 for문. 최적화 없이 무식한 방법 -> 시간 초과
 * 개선 -> len별로 작은 길이부터 쌓아나가기 1과 2 기반으로
 * 출력에 sb안쓰니까 시간 초과....
 */
public class bj10942 {
    static int n, m;
    static int[] nums;
    static boolean[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 2,000
        nums = new int[n + 1]; // 1-based

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) nums[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken()); // 1 ≤ M ≤ 1,000,000

        dp = new boolean[n+1][n+1]; // 상태 : [start][end]. true면 팰린드롬
        initMemoization();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(dp[s][e] ? 1 : 0).append("\n");
        }

        System.out.print(sb);
    }

    static void initMemoization(){
        for(int i = 1; i <= n; i++) dp[i][i] = true;
        for(int i = 1; i < n; i++){
            if(nums[i] == nums[i+1]) dp[i][i+1] = true;
        }

        for(int len = 3; len <= n; len++) {
            for(int start = 1; start + len - 1 <= n; start++) {
                int end = start + len - 1;
                dp[start][end] = (nums[start] == nums[end]) && dp[start+1][end-1]; // len과 이 부분이 핵심 아이디어
            }
        }
    }
}
