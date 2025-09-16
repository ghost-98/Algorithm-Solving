import java.util.*;
import java.io.*;

public class bj28707x {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 2 <= n <= 8

        int[] A = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) A[i] = Integer.parseInt(st.nextToken()); // 1 <= A[i] <= 10

        int m = Integer.parseInt(br.readLine()); // 1 <= m <= 10
        int[][] control = new int[m][3];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            control[i][0] = Integer.parseInt(st.nextToken()); // 1 ~ n
            control[i][1] = Integer.parseInt(st.nextToken()); // 1 ~ n
            control[i][2] = Integer.parseInt(st.nextToken()); // 1 ~ 10
        }
        

    }
}
