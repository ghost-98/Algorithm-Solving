import java.util.Scanner;
// 기존 아이디어 :  initDP + slice 크기 0부터 올려가면서 성공할때 return 하려했음 -> 조합크기 너무 커서x
// 생각 못한 아이디어 : int[]에 i번까지의 최소 슬라이스 개수를 담으며 바텀 업 방식의 dp
public class bj1509x {
    static int size;
    static String str;
    static boolean[][] dp;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        str = sc.next();

        size = str.length();
        dp = new boolean[size][size]; // 0-based
        initDP();

        // initDP 이후 아이디어가 없었음
        int[] minCut = new int[size]; // n까지의 최소 분할 횟수를 메모이제이션
        for(int i = 0; i < size; i++){
            if(dp[0][i]){
                minCut[i] = 1;
            } else {
                minCut[i] = Integer.MAX_VALUE;
                for(int j = 0; j < i; j++){ 
                    // 기존 minCut[i]보다 작은 경우 있을때 갱신하는 if문
                    if(dp[j+1][i] && minCut[j] + 1 < minCut[i]) minCut[i] = minCut[j] + 1;
                }
            }
        }

        System.out.println(minCut[size-1]);
    }

    // 먼저 dp[][]에 str의 팰린드롬 판별을 len 1, 2 초기화 후 3이상 판별하며 메모이제이션
    static void initDP(){
        for(int i = 0; i < size; i++) dp[i][i] = true;
        for(int i = 0; i < size - 1 ; i++) {
            if(str.charAt(i) == str.charAt(i+1)) dp[i][i+1] = true;
        }

        for(int len = 3; len <= size; len++) {
            for(int start = 0; start < size - len + 1; start++) {
                int end = start + len - 1;
                dp[start][end] = dp[start + 1][end - 1] && (str.charAt(start) == str.charAt(end));
            }
        }
    }
}
