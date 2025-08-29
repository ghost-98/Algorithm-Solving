import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// boolean[] 4개 리스트 + 헤드위치 int[] + 회전 방향 int[]

// 배운점
// 자료구조 제대로 설계할 것
// 로직과 함수(매개변수, 리턴) 잘 설계할 것
public class s4013 {
    static int ret;
    static List<boolean[]> magnetics;  // true N극 false S극
    static int[] head; // 4개 자석의 헤드 위치
    static int[] rotateDir; // 1 시계, -1 반시계, 0 회전x
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            int k = Integer.parseInt(br.readLine()); // k <= 20

            // 자석 4개와 하위 날 8개 담을 큐 리스트 생성
            magnetics = new ArrayList<>();
            for(int i=0; i<4; i++) magnetics.add(new boolean[8]);

            // 자석의 현재 헤드 위치
            head = new int[4];
            for(int i=0; i<4; i++) head[i] = 0;

            // 자석 정보
            for(int i=0; i<4; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<8; j++){
                    int blade = Integer.parseInt(st.nextToken());
                    if(blade == 1) magnetics.get(i)[j] = true;
                    else magnetics.get(i)[j] = false;
                }
            }

            for(int i=0; i<k; i++){
                // 회전 정보
                st = new StringTokenizer(br.readLine());
                int magnetic = Integer.parseInt(st.nextToken());
                int rotateDirInput = Integer.parseInt(st.nextToken()); // 1 시계방향, -1 반시계방향
                
                rotateDir = new int[4];
                rotateDir[magnetic-1] = rotateDirInput;

                // 모든 톱니의 회전 방향 구하기
                initRotateDir(magnetic-1, true);
                initRotateDir(magnetic-1, false);

                // 회전
                rotate();
            }

            // 점수 구하기
            ret = 0;
            getScore();

            System.out.println("#" + tc + " " + ret);
        }
    }

   static void initRotateDir(int num, boolean isLeft){
    if(rotateDir[num] == 0) return; // 톱니 회전 안하면 멈추기

    if(isLeft){
        if(num == 0) return;
        if(magnetics.get(num)[(head[num]+6)%8] != magnetics.get(num-1)[(head[num-1]+2)%8]){
            rotateDir[num-1] = -rotateDir[num]; // 기준 톱니 방향 반대로
            initRotateDir(num-1, true);
        }
    } else {
        if(num == 3) return;
        if(magnetics.get(num)[(head[num]+2)%8] != magnetics.get(num+1)[(head[num+1]+6)%8]){
            rotateDir[num+1] = -rotateDir[num]; // 기준 톱니 방향 반대로
            initRotateDir(num+1, false);
        }
    }
}

    // 모든 톱니 회전 방향 토대로 회전
    static void rotate(){
        for(int i=0; i<4; i++){
            if(rotateDir[i] == 0) continue;
            else if(rotateDir[i] == 1) head[i] = (head[i]+7)%8;
            else if(rotateDir[i] == -1) head[i] = (head[i]+1)%8;
        }
    }

    // 회전 끝나고 점수 구하기
    static void getScore(){
        for(int i=0; i<4; i++){
            if(magnetics.get(i)[head[i]]) ret += (1<<i);
        }
    }
}