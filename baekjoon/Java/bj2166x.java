import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 첫 접근 : 한 점 두고 나머지 n-2개의 변과의 삼각형 면적 구하기 -> 이걸 간략화하면 벡터외적 공식이 됨
// 다음 접근 : shoerace 공식이 있다. 벡터 외적으로 (long 타입)
public class bj2166x {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        long[][] dots = new long[n + 1][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            dots[i][0] = Integer.parseInt(st.nextToken());
            dots[i][1] = Integer.parseInt(st.nextToken());
        }

        // 계산
        double area = 0;
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            area += dots[i][0] * dots[j][1] - dots[j][0] * dots[i][1];
        }
        System.out.printf("%.1f\n", Math.abs(area) / 2.0);
    }
}
