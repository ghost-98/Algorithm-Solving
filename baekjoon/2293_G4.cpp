#include <iostream>
#include <vector>

using namespace std;

// 완전 탐색 + 백트래킹 : dfs 로는 초과. dp 판단 사고 흐름??
int main() {
    int n, k;
    cin >> n >> k;

    vector<int> coins(n);
    for(int i=0; i<n; i++) cin >> coins[i];

    // vector 선언 및 사용법?
    vector<int> dp(k+1, 0);
    dp[0] = 1;
    
    // 범위 기반 for문, 동전 구성 중복 없으니)
    for(int coin : coins){
        for(int i=coin; i<=k; i++){
            dp[i] += dp[i - coin];
        }
    }

    cout << dp[k];
    
    return 0;
}