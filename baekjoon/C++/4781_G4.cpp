#include <iostream>
#include <vector>
using namespace std;

// 15m
// DP + 소수점 둘째짜리라 *100 해서 정수로 봄
// 정답이긴 하나, dp[0]~dp[m*100]에서 max를 구해야함
int main() {
    int n;
    double m;

    while(cin >> n >> m){
        if(n == 0) break;

        vector<int> calos(n);
        vector<double> prices(n);
        for(int i=0; i<n; i++) cin >> calos[i] >> prices[i];

        vector<int> dp(m*100+1, 0);
        for(int i=0; i<n; i++){
            for(int j=prices[i]*100; j<=m*100; j++){
                dp[j] = max(dp[j], dp[j-prices[i]*100]+calos[i]);
            }
        }
        
        cout << dp[m*100] << "\n";
    }

    return 0;
}