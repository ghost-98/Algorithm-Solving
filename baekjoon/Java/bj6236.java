import java.util.Scanner;

// 디버깅까지 25m
public class bj6236 {
    public static final int max_ = 1000000000;
    public static int n, m, start;
    public static int[] charge = new int[100001]; // 0-based

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
        
        for(int day=0; day<n; day++){
            charge[day] = sc.nextInt();
            start = Math.max(start, charge[day]);
        }
        
        int end = max_, mid;
        while(start < end){ // 이분 탐색 조건 제대로
            mid = (start + end) / 2;
            if (check(mid)) end = mid;
            else start = mid + 1;
        }
        
        System.out.print(start);
	}
	
	public static boolean check (int k) {
        int cnt = 1, wallet = k;
        for(int i=0; i<n; i++){
            if(wallet < charge[i]){
                cnt++;
                wallet = k;
            }
            wallet -= charge[i];
        }
        
        if (cnt <= m) return true;
        else return false;
    }
}

// **** 풀기 전에 생각한 점들
// 완탐으로 푸려면 k원을 지정해놓고 줄이면서 다 해보면 걸리는 지점 -> (당일 사용 금액-1/k)+1의 n일 합 -> 시간 복잡도 (n*최대값)
// 선형으로 내려오면서 search할지 -> 이분 탐색으로
// m보다 작거나 같아도 됨. k는 어디가 최대 지점? - n의 최대값 * 금액의 최대 값 = 10^9

// **** 풀고 나서 얻은 점들
// 이분 탐색 조건 제대로
// start, end 값 설정 : 이번 문제에서는 하루 최대 지출을 start로 사용해야 함. end는 맞아
// 초기 start 값과 이분 탐색 내의 조건등 조건 꼼꼼히 확인