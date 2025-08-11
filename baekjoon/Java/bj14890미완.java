import java.util.Scanner;

// 40m 걸렸는데 미완에 조잡함
public class bj14890미완 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int l = sc.nextInt();

        int[][] map = new int[n][n];
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                map[r][c] = sc.nextInt();
            }
        }

        // 가로줄 카운팅
        int ret = 0;
        for(int r=0; r<n; r++){
            int curVal = map[r][0];
            int curIdx = 1;
            boolean flag = true;
            for(int c=1; c<n; c++){
                if(curVal == map[r][c]) curIdx++;
                // 뭔가 로직 조잡하고, 쓸데없는 순회 남음
                else if((curVal-map[r][c])==1){
                    if(c+l>n){
                        flag = false;
                        break;
                    }
                    for(int i=0; i<l; i++){
                        if(map[r][c+i] != curVal-1){
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) break;
                    else{
                        c += l-1;
                        curIdx = 1;
                    }
                }
                else if((curVal-map[r][c])==-1 && curIdx>=l){
                    curVal = map[r][c];
                    curIdx = 1;
                }else{
                    flag = false;
                    break;
                }
            }
            
            if(flag){
                System.out.println(r);
                ret++;
            }
        }

        // 세로줄 카운팅
        for(int c=0; c<n; c++){
            int curVal = map[0][c];
            int curIdx = 1;
            boolean flag = true;
            for(int r=1; r<n; r++){
                if(curVal == map[r][c]) curIdx++;
                // 뭔가 로직 조잡하고, 쓸데없는 순회 남음
                else if((curVal-map[r][c])==1){
                    if(r+l>n){
                        flag = false;
                        break;
                    }
                    for(int i=0; i<l; i++){
                        if(map[r+i][c] != curVal-1){
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) break;
                    else{
                        r += l-1;
                        curIdx = 1;
                    }
                }
                else if(curVal-map[r][c]==-1 && curIdx>=l){
                    curVal = map[r][c];
                    curIdx = 1;
                }else{
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.println(c);
                ret++;
            }
        }   

        System.out.print(ret);     
    }
}