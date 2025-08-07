import java.io.*;
import java.util.*;

public class s1210 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0; i<10; i++){
            int tc = Integer.parseInt(br.readLine());

            int[][] maze = new int[101][101];
            for(int j=0; j<100; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<100; k++){
                    maze[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int end=0;
            for(int j=0; j<100; j++){
                if(maze[99][j]==2){
                    end = j;
                    break;
                }
            }

            // 2인 end에서 출발
            int height = 98;
            while(height > 0){
                if(end > 0 && maze[height][end-1] == 1){
                    while(end > 0 && maze[height][end-1] == 1){
                        end--;
                    }
                }else if(end < 99 && maze[height][end+1] == 1){
                    while(end < 99 && maze[height][end+1] == 1){
                        end++;
                    }
                }
                height--;
            }

            System.out.println("#" + tc + " " + end);
        }
    }
}
// 문제 이해 잘못한 점 : 한번 이동하면 쭉 이동 가능
// while문 로직 생각하기. 한쪽으로 몰아서 하는 if 문 + while문 로직 헷갈림