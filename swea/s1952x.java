import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s1952x {
    static int[] ticketFee, monthVisit, monthFee;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            ticketFee = new int[4];
            monthVisit = new int[12];
            monthFee = new int[12];
            dp = new int[13];
            
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++) ticketFee[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<12; i++) monthVisit[i] = Integer.parseInt(st.nextToken());

            // 로직
            // 1일 vs 1달
            for (int i = 0; i < 12; i++) {
                int oneDay = monthVisit[i] * ticketFee[0];
                monthFee[i] = Math.min(oneDay, ticketFee[1]);
            }

            // DP로 1일, 1달, 3달 고려 최소 합 구하기 (뒤에서부터)
            for (int i = 11; i >= 0; i--) {
                int cost1Month = monthFee[i] + dp[i + 1];
                int cost3Month = ticketFee[2] + (i + 3 <= 12 ? dp[i + 3] : 0);
                dp[i] = Math.min(cost1Month, cost3Month);
            }

            // 1년
            int ret = Math.min(dp[0], ticketFee[3]);

            System.out.println("#"+tc+" "+ret);
        }
    }
}
            /**
            // 구하는 로직
            // 1일, 1개월
            for (int i = 0; i < 12; i++) {
                int oneDay = monthVisit[i] * ticketFee[0];
                monthFee[i] = Math.min(oneDay, ticketFee[1]);
            }

            // 3개월
            int ret = 0;
            while(true){ // 순차 조회로는 최적 보장x -> DP?
                if(i+2 < 12){
                    int sum3Month = monthFee[i] + monthFee[i+1] + monthFee[i+2];
                    if(ticketFee[2] < sum3Month){
                        ret += ticketFee[2];
                        i += 3;
                    }else{
                        ret += monthFee[i];
                        i++;
                    }
                }else {
                    ret += monthFee[i];
                    i++;
                }
            }
            
            // 1년
            if(ret > ticketFee[3]) ret = ticketFee[3];
            **/