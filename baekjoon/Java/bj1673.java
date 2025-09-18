import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class bj1673 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            long ret = 0, coupon = n, dozang = 0;
            while(true) {
                dozang += coupon;
                ret += n;

                if(dozang / k == 0) break;

                coupon += dozang / k;
                dozang %= k;
            }

            System.out.println(ret);
        }
    }
}
