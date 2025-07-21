import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bj16926 {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotateArr(arr, n, m, r);

        StringBuilder sb = new StringBuilder();
        for(int[] row : arr){
            for(int num : row){
                sb.append(num).append(" ");
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static void rotateArr(int[][] arr, int n, int m, int r){
        int shells = Math.min(n, m) / 2;  // 문제 조건 : min(n, m) mod 2 = 0 이므로 홀짝 고려x
        for(int shell=0; shell<shells; shell++){
            List<Integer> elmts = new ArrayList<>();

            for(int i=shell; i<m-shell; i++) elmts.add(arr[shell][i]);
            for(int i=shell+1; i<n-shell-1; i++) elmts.add(arr[i][m-shell-1]);
            for(int i=m-shell-1; i>=shell; i--) elmts.add(arr[n-shell-1][i]);
            for(int i=n-shell-2; i>shell; i--) elmts.add(arr[i][shell]);
            
            int len = elmts.size();
            int rotate_cnt = r%len; 
            List<Integer> rotated = new ArrayList<>();
            for(int i=0; i<len; i++) rotated.add(elmts.get((i+rotate_cnt)%len));

            int idx=0;
            for(int i=shell; i<m-shell; i++) arr[shell][i] = rotated.get(idx++);
            for(int i=shell+1; i<n-shell-1; i++) arr[i][m-shell-1] = rotated.get(idx++);
            for(int i=m-shell-1; i>=shell; i--) arr[n-shell-1][i] = rotated.get(idx++);
            for(int i=n-shell-2; i>shell; i--) arr[i][shell] = rotated.get(idx++);
        }
    }

}


