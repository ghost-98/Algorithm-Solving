import java.io.*;
import java.util.*;

public class s3499 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());

            String[] cards = new String[n];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) cards[i] = st.nextToken();

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc);
            for(int i = 0; i < n / 2; i++) {
                sb.append(" ").append(cards[i]);
                if(n % 2 == 0) sb.append(" ").append(cards[n / 2 + i]);
                else sb.append(" ").append(cards[n / 2 + 1 + i]);
            }
            if(n % 2 == 1) sb.append(" ").append(cards[n / 2]);

            System.out.println(sb);
        }
    }
}
