import java.io.*;
import java.util.*;

// 초딩 정보올림피아드..
// 생각 1 : 완탐 + 가지치기 최적화
// 생각 2 : 정렬된 수열에서 2개 뽑는 조합은 투포인터! O(n)
//      + 투포인터의 본질은?
public class bj2467한번더 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 2 이상 100,000 이하의 정수

        int[] liquids = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        int minRet = Integer.MAX_VALUE;
        int[] minIdx = new int[2];
        int left = 1, right = n;
        while (left < right) {
            int sum = liquids[left] + liquids[right];
            if (Math.abs(sum) < minRet) {
                minRet = Math.abs(liquids[left] + liquids[right]);
                minIdx[0] = liquids[left];
                minIdx[1] = liquids[right];
            }

            if (sum > 0)
                right--;
            else if (sum < 0)
                left++;
            else
                break; // 0이면 최소임
        }

        System.out.print(minIdx[0] + " " + minIdx[1]);

        /**
         * int changeSignIdx = n;
         * for (int i = 1; i <= n; i++) {
         * if (liquids[i] > 0) {
         * changeSignIdx = i;
         * break;
         * }
         * }
         * 
         * // 부호 바뀌는 시점 (입력시 양수 인덱스 -1 부터 따라가게?)
         * int globalMin = Integer.MAX_VALUE;
         * int[] elems = new int[2];
         * for (int c1 = 1; c1 < changeSignIdx || c1 < n; c1++) {
         * int localMin = Integer.MAX_VALUE;
         * for (int c2 = changeSignIdx - 1; c1 < c2 && c2 <= n; c2++) {
         * if (Math.abs(liquids[c1] + liquids[c2]) < localMin) {
         * localMin = Math.abs(liquids[c1] + liquids[c2]);
         * if (localMin < globalMin) {
         * globalMin = localMin;
         * elems[0] = liquids[c1];
         * elems[1] = liquids[c2];
         * }
         * } else
         * continue; // 이후는 가지치기
         * }
         * }
         **/
    }
}