import java.util.*;
import java.io.*;

public class bj11779x {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int departCity = Integer.parseInt(st.nextToken());
            int arriveCity = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken()); // 0 ~ 100000

        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dist[start][end]);
    }
}
