import java.io.*;
import java.util.*;

// 이분 탐색 이용한 LIS, 길이만 구할 수 있음
public class bj12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 1,000,000

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken()); // 1 ≤ Ai ≤ 1,000,000

        int[] dp = new int[n];
        int length = 0;
        for(int num : arr){
            int idx = Arrays.binarySearch(dp, 0, length, num);
            if(idx < 0) idx = -(idx+1);
            dp[idx] = num;
            if(idx == length) length++;
        }

        System.out.print(length);
    }
}
