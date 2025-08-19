import java.util.Scanner;

public class s1873 {
    static Scanner sc;
    static int h, w, n;
    static int[] car;
    static char[][] map;
    /**
     * 방향 배열 쓰면 좋음
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static char[] dirChar = {'^','v','<','>'};
     **/
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++){
            h = sc.nextInt();
            w = sc.nextInt();
            map = new char[h][w];

            car = new int[3]; // 전차의 y,x,방향
            fillMap();

            n = sc.nextInt();
            String input = sc.next();
            for(int i=0; i<n; i++){
                if(input.charAt(i) == 'S') shoot();
                else move(input.charAt(i));
            }

            sb.append("#"+tc+" ");
            for(int r=0; r<h; r++){
                for(int c=0; c<w; c++) sb.append(map[r][c]);
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }

    static void fillMap(){
        for(int r=0; r<h; r++){
            String line = sc.next();
            for(int c=0; c<w; c++){
                map[r][c] = line.charAt(c);
                if(line.charAt(c) == '^'){
                    car[0] = r; car[1] = c; car[2] = 1;
                }else if(line.charAt(c) == 'v'){
                    car[0] = r; car[1] = c; car[2] = 3;
                }else if(line.charAt(c) == '<'){
                    car[0] = r; car[1] = c; car[2] = 4;
                }else if(line.charAt(c) == '>'){
                    car[0] = r; car[1] = c; car[2] = 2;
                }
            }
        }
    }

    static void shoot(){
        int y = car[0];
        int x = car[1];
        if(car[2]==1){
            while(0 < y){
                y--;
                if(map[y][x] == '#') return;
                else if(map[y][x] == '*'){
                    map[y][x] = '.';
                    return;
                }
            }
        }else if(car[2]==2){
            while(x < w-1){
                x++;
                if(map[y][x] == '#') return;
                else if(map[y][x] == '*'){
                    map[y][x] = '.';
                    return;
                }
            }
        }else if(car[2]==3){
            while(y < h-1){
                y++;
                if(map[y][x] == '#') return;
                else if(map[y][x] == '*'){
                    map[y][x] = '.';
                    return;
                }
            }
        }else if(car[2]==4){
            while(0 < x){
                x--;
                if(map[y][x] == '#') return;
                else if(map[y][x] == '*'){
                    map[y][x] = '.';
                    return;
                }
            }
        }

        return;
    }

    static void move(char c){
        int y = car[0];
        int x = car[1];
        if(c == 'U'){
            if(y > 0 && map[y-1][x] == '.'){
                car[0]--;
                map[y-1][x] = '^';
                map[y][x] = '.';
            }else map[y][x] = '^';
            car[2] = 1;
        }else if(c == 'D'){
            if(y < h-1 && map[y+1][x] == '.'){
                car[0]++;
                map[y+1][x] = 'v';
                map[y][x] = '.';
            }else map[y][x] = 'v';
            car[2] = 3;
        }else if(c == 'L'){
            if(x > 0 && map[y][x-1] == '.'){
                car[1]--;
                map[y][x-1] = '<';
                map[y][x] = '.';
            }else map[y][x] = '<';
            car[2] = 4;
        }else if(c == 'R'){
            if(x < w-1 && map[y][x+1] == '.'){
                car[1]++;
                map[y][x+1] = '>';
                map[y][x] = '.';
            }else map[y][x] = '>';
            car[2] = 2;
        }
    }
}
