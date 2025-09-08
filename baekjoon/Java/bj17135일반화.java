import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.List;
import java.util.ArrayList;
// 09:04
// 다른 최적화 방법은 안 떠오르고, 완탐 해야 한다는 생각(n, m, d 작음). 하지만 백트래킹은 중요
// 문제 제대로 읽어라,, 궁수는 마지막 줄에만 배치 가능 - 나는 모든 맵에서 3명 구함

// 처음에 생각을 잘못하면 시간 다 써버리고,,
public class bj17135일반화 {
    static int n, m, d;
    static int ret;
    static boolean[][] map;
    static List<int[]> enemy;
    static List<int[]> attackEnemy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken()); // 3 <= n <= 15
        m = Integer.parseInt(st.nextToken()); // 3 <= n <= 15
        d = Integer.parseInt(st.nextToken()); // 1 <= d <= 10

        map = new boolean[n][m];
        enemy = new ArrayList<>();
        attackEnemy = new ArrayList<>();

        for(int r=0; r<n; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<m; c++){
                int input = Integer.parseInt(st.nextToken());
                if(input == 1){
                    map[r][c] = true; // 적이 있는 곳만 true;
                    enemy.add(new int[] {r, c});
                }
            }
        }
        
        ret = 0;
        // 궁수 3명이니 3중 for문 조합
        for(int i=0; i<n*m-2; i++){
            for(int j=i+1; j<n*m-1; j++){
                for(int k=j+1; k<n*m; k++){
                    int tmpRet = 0;
                    int y1 = i/m; int x1 = i%m;
                    int y2 = j/m; int x2 = j%m;
                    int y3 = k/m; int x3 = k%m;
                    
                    while(enemyIsLeft()){
                        // 궁수 각각 공격할 적 선택
                        chooseEnemyToAttack(y1, x1);
                        chooseEnemyToAttack(y2, x2);
                        chooseEnemyToAttack(y3, x3);

                        // 선택된 적들 공격
                        archorAttack();

                        // 남은 적 각각 이동
                        enemyMove();
                    }

                    if(tmpRet > ret) ret = tmpRet;
                }
            }
        }

        System.out.print(ret);
    }

    // 적 한칸씩 이동, map 변경
    static void enemyMove(){
        int enemyCnt = enemy.size();
        for(int i=0; i<enemyCnt; i++){
            int[] enemyPos = enemy.get(i);
            // map 변경
            map[enemyPos[0]][enemyPos[1]] = false;

            // 적 리스트 변경
            if(enemyPos[0] == n){
                enemy.remove(i);
                continue;
            }

            enemy.get(i)[i]++;
        }

        for(int i=0; i<enemyCnt; i++){
            int[] enemyPos = enemy.get(i);
            // map 변경
            map[enemyPos[0]][enemyPos[1]] = true;
        }
    }

    // 적이 성으로 다 들어가면 false 반환
    static boolean enemyIsLeft(){
        if(enemy.isEmpty()) return false;
        return true;
    }

    // 각각의 궁수가 공격할 적 선택
    static boolean chooseEnemyToAttack(int y, int x){
        // 공격 가능한 범위는 45도 기울인 정사각형(왼쪽부터 조회)
        // 적 있으면 넣고 리턴
        for(int i=1, tmpX=x-d; tmpX<=x+d; tmpX++, i++){
            int tmpDy = d - Math.abs(d-i);
            for(int tmpY=y-tmpDy; tmpY<=y+tmpDy; tmpY++){
                if(tmpY<0 || tmpX<0 || tmpY>=n || tmpX>=m) continue; // 벗어나면 패스

                if(map[tmpY][tmpX]){
                    attackEnemy.add(new int[] {tmpY, tmpX});
                    return true;
                }
            }
        }

        return false;
    }

    // 타겟 적 공격
    static void archorAttack(){
        int enemyCnt = attackEnemy.size();
        for(int i=0; i<enemyCnt; i++){
            int[] enemyToAttack = attackEnemy.get(i);
            if(!map[enemyToAttack[0]][enemyToAttack[1]]) continue;

            map[enemyToAttack[0]][enemyToAttack[1]] = false;

            // 적 리스트도 삭제
            enemyDead(enemyToAttack[0], enemyToAttack[1]);
        }
        
        attackEnemy.clear();
    }

    static void enemyDead(int y, int x){
        for(int i=0; i<enemy.size(); i++){
            if(enemy.get(i)[0] == y && enemy.get(i)[1] == x){
                enemy.remove(i);
                return;
            }
        }
    }
}
