import java.util.Scanner;
// 8, 비트마스킹도 가능
public class bj1094 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int stick = 64, ret = 0;

        while(x != 0){
            if(x < stick) stick /= 2;
            else{
                ret++;
                x -= stick;
            }
        }

        System.out.print(ret);
    }
}