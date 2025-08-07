import java.io.*;
import java.util.*;

public class s6808X {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int[] nums = new int[9];
        int[] mynums = new int[9];

        boolean[] isUsed;

        for(int tc=0; tc<T; tc++){
            isUsed = new boolean[19];
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++){
                nums[j] = Integer.parseInt(st.nextToken());
                isUsed[nums[j]] = true;
            }

            // 인영이 수
            int idx = 0;
            for(int j=1; j<=18; j++){
                if(!isUsed[j]) mynums[idx++] = j;
            }

            int winCnt = 0, loseCnt = 0;
            // 백트래킹 하면서 결과


            System.out.println("#" + tc + " " + winCnt +  " " + loseCnt);
        }
    }
}