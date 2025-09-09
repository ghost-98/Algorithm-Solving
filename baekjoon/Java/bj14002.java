import java.io.*;
import java.util.*;

// 이분 탐색 이용한 LIS + 복원. 시간 복잡도 nlogn으로 동일
public class bj14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 1,000,000
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken()); // 1 ≤ Ai ≤ 1,000,000

        int[] dp = new int[n];
        int[] pos = new int[n]; // 복원용. 원소가 dp에 들어간 위치 저장
        int length = 0;
        for(int i=0; i<n; i++){
            int num = arr[i];
            int idx = Arrays.binarySearch(dp, 0, length, num);
            if(idx < 0) idx = -(idx+1);
            dp[idx] = num;
            pos[i] = idx; // 복원용 기록
            if(idx == length) length++;
        }

        // LIS 복원
        int[] lis = new int[length];
        int cur = length - 1;
        for(int i=n-1; i>=0; i--){
            if(pos[i] == cur){
                lis[cur] = arr[i];
                cur--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(length + "\n");
        for(int i=0; i<length; i++) sb.append(lis[i] + " ");

        System.out.print(sb);
    }
}
 