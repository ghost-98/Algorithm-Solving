import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj11659 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] sum = new int[n+1];
        st = new StringTokenizer(br.readLine());
        sum[0] = 0;
        for(int i=1; i<=n; i++){
            sum[i] = Integer.parseInt(st.nextToken());
            sum[i] += sum[i-1];
        }

        for(int k=0; k<m; k++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            System.out.println(sum[j]-sum[i-1]); 
        }
    }
}