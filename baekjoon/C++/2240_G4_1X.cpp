#include <iostream>

using namespace std;
int T, W;
int plum[1001], dp[1001][31];

int main() {
    cin >> T >> W;
    for(int i=1; i<=T; i++) cin >> plum[i];
    
    // dp[t][w] 시간과 바꾼횟수에 따른 최대 자두값
    for(int t=1; t<=T; t++){
        for(int w=0; w<=W; w++){ // 1초안에 여러번 바꾸는것도 가능하므로
            int tree = w%2+1;

            // 이동 안 한 경우
            dp[t][w] = dp[t-1][w];
            if(plum[t]==tree) dp[t][w]++;
            
            // 이동 한 경우
            if(w>0){
                int temp = dp[t-1][w-1];
                if(plum[t]==tree) temp++;
                dp[t][w] = max(dp[t][w], temp);
            }
        }
    }

    int ret=0;
    for(int w=0; w<=W; w++) ret = max(ret, dp[T][w]);
    cout << ret;
    
    return 0;
}
// 완탐시 1000C30 시간복잡도 -> DP