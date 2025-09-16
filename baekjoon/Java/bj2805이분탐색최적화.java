import java.io.*;
import java.util.*;
// 23m
// 이분탐색
// 개선 : 이분탐색 코드 더 깔끔 최적화해서 짤 수 있어
public class bj2805이분탐색최적화 {
    static int n, m;
    static int[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 1,000,000 -> 100만
        m = Integer.parseInt(st.nextToken()); // 1 ≤ M ≤ 2,000,000,000 -> 20억
        trees = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) trees[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(trees); // 정렬 O(nlogn);

        long cutLen = 0;

        // 이분 탐색 * cut 함수 = O(log n) * O(n)
        int left = trees[0], right = trees[n - 1], mid = 0;;
        while(left <= right) { 
            mid = (left + right) / 2;
            cutLen = cut(mid);

            if(cutLen < m) right = mid - 1;
            else if(cutLen > m) left = mid + 1;
            else break; 
        }

        // cutLen != m 인 후속 처리 (cutLen > m 은 고려 x)
        if(cutLen < m) {
            while(true) {
                if(cut(--mid) >= m) break;
            }
        }

        System.out.print(mid);
    }

    static long cut(int h) {
        long sum = 0;
        for(int i = 0; i < n; i++) {
            if(trees[i] > h) sum += trees[i] - h;
        }

        return sum;
    }
}

/* 정렬 없이 입력 받을때 Math.max로 가장 maxTree 구하고 left는 0에서 시작 가능
 * 
 *      < 이분 탐색 최적화 코드>
 *      int left = 0;
        int right = maxTree;
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            long sum = cut(mid);

            if (sum >= m) { // 충분히 나무가 나오면 높이 올려서 더 탐색
                ans = mid;
                left = mid + 1;
            } else { // 부족하면 높이 낮춤
                right = mid - 1;
            }
        }

        + 추가로 cut 함수에서 자른 목표치 이상이면 중단 시키는 코드 ( if(sum >= m) break; )
 */