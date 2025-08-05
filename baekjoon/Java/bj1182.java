import java.util.*;

public class bj1182 {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      
      int n = sc.nextInt();
      int s = sc.nextInt();
      int[] arr = new int[n];
      
      for(int i=0; i<n; i++){
        arr[i] = sc.nextInt();
      }
      
      int ret=0;
      for(int i=1; i<(1<<n); i++){
        int sum=0;
        for(int j=0; j<n; j++){
          if(((1<<j)&i) == (1<<j)) sum += arr[j];
        }
        if(sum == s) ret++;
      }
      
      System.out.print(ret);
  }
}