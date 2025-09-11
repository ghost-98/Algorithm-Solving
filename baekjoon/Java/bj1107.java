import java.util.Scanner;
// 11:48
public class bj1107 {
    static int n, m;
    static boolean[] unusable;
    static int[] cnt;

    static int[] digit = {1, 10, 100, 1000, 10000, 100000}; // 자릿수 더 줄이거나 배열 안 써도되는법?
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 0 ≤ N ≤ 500,000
        m = sc.nextInt(); // 0 ≤ M ≤ 10
        
        unusable = new boolean[10];
        for(int i = 0; i < m; i++){
            int button = sc.nextInt();
            unusable[button] = true;
        }

        cnt = new int[n+1];
        for(int channel = 0; channel <= n; channel++) cnt[channel] = Math.abs(channel - n); // +, -버튼으로만 이동한 횟수로 초기화

        int cur = 100; // 현재 위치
        int maxNum = 0, minNum = 0;
        for(int i = 0; i <= 9; i++) if(!unusable[i]) { minNum = i; break; }
        for(int i = 9; i >= 0; i--) if(!unusable[i]) { minNum = i; break; }
        
        int ret = getSize(n); // n의 자릿수로 초기화
        // 자릿수대로 내려오기
        for(int i = 5; i >= 0; i--) {
            if(n / digit[i] == 0) continue; 
            if(!unusable[n / digit[i]]) continue;

            int num = n / digit[i];
            // 자릿수가 고장났을때
        }
    }

    // 변수명 이상, 데체 메서드 있나?
    static int getSize(int n) {
        int v = 0;
        while(true) {
            if(n == 0) break;
            n /= 10; v++;
        }

        return v;
    }
}
/*
 * 1. 사용 가능한 버튼으로 누를 수 있는 타겟 번호의 가장 가까운 번호로 이동
 * 2. 현재에서 근접하고 + - 로만 조작가능한 채널들은? -> +, -로만 한걸로 초기화 후 Math.min
 * 
 * 아예 다른 알고리즘 이용한 아이디어는?
 */