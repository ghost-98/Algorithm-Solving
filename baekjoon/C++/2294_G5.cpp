#include <iostream>
#include <vector>
using namespace std;

const int INF = 1e9;

int main() {
    int n, k;
    cin >> n >> k;

    vector<int> coins(n);
    for(int i=0; i<n; i++) cin >> coins[i];

    vector<int> dp(k+1, INF);
    dp[0] = 0;
    
    for(int coin : coins){
        for(int i=coin; i<=k; i++){
            if(dp[i-coin] != INF){
                dp[i] = min(dp[i], dp[i-coin] + 1);
            }
        }
    }

    if(dp[k] == INF) cout << -1;
    else cout << dp[k];
    
    return 0;
}

// 초기값 설정 : 최대, 최소
// 상수, max와 min 많이 쓰기