import java.io.*;
// 50m
// 완탐 : 문자열 직접 조작 없이 결과 예측해서 출력하는 것 + 문자열 처리
// 나머지 코드는 다 짬, 문자열 처리만 내가 못함
public class bj5430문자열처리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine()); // 100 이하
        for(int tc = 1; tc <= t; tc++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine()); // 0 ≤ n ≤ 100,000

            // 리스트 형태 문자열 입력 받아 처리 (문자열 다루기!!)
            String input = br.readLine();
            input = input.replaceAll("\\[|\\]", ""); // 대괄호 제거
            String[] elem;
            if (input.isEmpty()) elem = new String[0]; // 테케 덕에 처리함
            else elem = input.split(",");
            int[] nums = new int[elem.length];
            for(int i = 0; i < nums.length; i++) nums[i] = Integer.parseInt(elem[i].trim());

            boolean reverseFlag = false;
            int frontD = 0, backD = 0;
            for(int i = 0; i < p.length(); i++) {
                char func = p.charAt(i);

                if(func == 'D'){
                    if(reverseFlag) backD++;
                    else frontD++;
                }else reverseFlag = !reverseFlag;
            }

            // 수의 개수 보다 delete 함수가 많을 때
            if(frontD + backD > n) {
                System.out.println("error");
                continue;
            }

            int retSize = n - frontD - backD;
            int[] ret = new int[retSize];

            int idx = 0;
            if(reverseFlag) {
                for(int i = n - backD - 1; i >= frontD; i--) ret[idx++] = nums[i];
            }else {
                for(int i = frontD; i < n - backD; i++) ret[idx++] = nums[i];
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(int i = 0; i < retSize; i++) {
                sb.append(ret[i]);
                if (i != retSize - 1) sb.append(","); // 처리
            }
            sb.append("]");

            System.out.println(sb);
        }
    }
}
