import java.util.*;
import java.io.*;

// 첫 접근 : 이해 잘못해서 정렬해서 뒤에서부터 더하는식으로 함..
// 두번째 접근 : 투 포인터
public class bj1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 10 ≤ N < 100,000
        int s = Integer.parseInt(st.nextToken()); // 0 < S ≤ 100,000,000

        int[] nums = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken()); // 1 ~ 10000
        }

        int sum = 0, left = 0, right = 0;
        int ret = Integer.MAX_VALUE;
        while (true) {
            // sum이 S 이상이면 최소 길이 갱신 후 left 이동
            if (sum >= s) {
                ret = Math.min(ret, right - left);
                sum -= nums[left++];
            }
            // sum이 S 미만이면 right 이동
            else if (right == n)
                break;
            else {
                sum += nums[right++];
            }
        }

        if (ret == Integer.MAX_VALUE)
            ret = 0;
        System.out.print(ret);
    }
}