import java.io.*;
import java.util.*;

public class bj11403 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        boolean[][] matrix = new boolean[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                int val = Integer.parseInt(st.nextToken());
                if(val == 1) matrix[i][j] = true;
            }
        }

        for(int mid = 1; mid <= n; mid++) {
            for(int start = 1; start <= n; start++) {
                for(int end = 1; end <= n; end++) {
                    if(matrix[start][mid] && matrix[mid][end]) matrix[start][end] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(matrix[i][j]) sb.append(1);
                else sb.append(0);
                sb.append(' ');
            }
            sb.append('\n');
        }
        
        System.out.print(sb);
    }
}