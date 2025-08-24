import java.util.Scanner;
// 비트마스킹 완탐(조합) 
// 규칙성 찾아서 그리디 할까 하다가,, 실패 (그리디는 명제에 약간이라도 사각 있으면 패스)
// dfs로 풀어보기
public class bj16637dfs풀이도 {
    static int n;
    static int[] nums;
    static char[] ops;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        String susik = sc.next();

        nums = new int[n/2+1];
        ops = new char[n/2];
        for(int i=0; i<n; i++){
            if(i%2 == 0) nums[i/2] = susik.charAt(i) - '0';
            else ops[i/2] = susik.charAt(i);
        }

        int ret = Integer.MIN_VALUE;
        for(int i=0; i<(1<<n/2); i++){
            if(check(i)){
                int tmpRet = run(i);
                if(tmpRet > ret) ret = tmpRet;
            }
        }

        System.out.print(ret);
    }

    // 연속된 괄호는 겹쳐지므로 배제
    static boolean check(int x){
        for(int i=0; i<n/2-1; i++){
            if(((x & 1<<i) != 0) && ((x & 1<<i+1) != 0)) return false;
        }
        return true;
    }
    
    // 괄호 넣은 수식 계산
    static int run(int x){
        int ret = nums[0];
        for(int i=0; i<n/2; i++){
            if ((x & (1 << (i+1))) != 0 && i<n/2-1){
                ret = calc(ret, calc(nums[i+1], nums[i+2], ops[i+1]), ops[i]);
                i++;
            }else ret = calc(ret, nums[i+1], ops[i]);
        }

        return ret;
    }
    
    // char 연산자 실제 연산
    static int calc(int x, int y, char c){
        if(c == '+') return x+y;
        else if(c == '-') return x-y;
        else return x*y; // else if로 명시적 해주고 싶은데 그렇게 하면 리턴이 애매해짐
    }
}
