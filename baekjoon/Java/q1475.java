import java.util.Scanner;

public class q1475 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] count = new int[10];
        int num = sc.nextInt();

        while(num != 0) {
            count[num%10]++;
            num /= 10;
        }

        count[6] = (count[6] + count[9] + 1) / 2;
        
        int max_ = 0;
        for(int i = 0; i < 9; i++) {
            if(max_ < count[i]) max_ = count[i];
        }

        System.out.println(max_);
    }
}