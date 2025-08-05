import java.util.Scanner;
import java.util.Stack;

public class bj4949 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            String str = sc.nextLine();

            if(str.equals(".")) break; // 값, 주소 일치?, ''안됨
            
            if(checkSentenceBalance(str)) System.out.println("yes");
            else System.out.println("no");
        }

    }

    static boolean checkSentenceBalance(String str){
        Stack<Character> stk = new Stack<>();

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)=='(' || str.charAt(i)=='[') stk.push(str.charAt(i)); // [] 접근 아닌 charAt()등 String 메서드 숙지
            else if(str.charAt(i)==')'){
                if(!stk.empty() && stk.peek()=='(') stk.pop();
                else return false;
            }else if(str.charAt(i)==']'){
                if(!stk.empty() && stk.peek()=='[') stk.pop();
                else return false; 
            }
        }

        if(stk.empty()) return true;
        else return false;
    }
}
