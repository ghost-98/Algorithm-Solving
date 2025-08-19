import java.util.Scanner;

// 16m. 그리디로 풀었는데, 조합이 안맞을 때가 있을거같아
public class bj1285 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine(); // 개행 버퍼에 남는게 ?
        int[][] table = new int[n][n];
        for(int row=0; row<n; row++){
            String rows = sc.nextLine();
            for(int col=0; col<n; col++){
                table[row][col] = rows.charAt(col); 
            }
        }
        
        boolean flag = true;
        int ret = 0;
        while(flag){
            flag = false;
            ret = 0;
            for(int i=0; i<n; i++){
                // 행
                int rowHcnt = 0;
                for(int col=0; col<n; col++){
                    if(table[i][col] == 'H'){
                        rowHcnt++;
                        ret++;
                    }
                }
                if(rowHcnt >= n/2+1){
                    flag = true;
                    for(int col=0; col<n; col++){
                        if(table[i][col] == 'H') table[i][col] = 'T';
                        else if(table[i][col] == 'T') table[i][col] = 'H';
                    }
                    break;
                }
                
                // 열
                int colHcnt = 0;
                for(int row=0; row<n; row++){
                    if(table[row][i] == 'H'){
                        colHcnt++;
                        ret++;
                    }
                }
                if(colHcnt >= n/2+1){
                    flag = true;
                    for(int row=0; row<n; row++){
                        if(table[row][i] == 'H') table[row][i] = 'T';
                        else if(table[row][i] == 'T') table[row][i] = 'H';
                    }
                    break;
                }
            }      
        }
        System.out.print(ret/2);
    }
}
// N/2+1 이상인 행이나 열을 뒤집고, 순회하면서 없으면 끝
