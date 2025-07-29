import java.util.Scanner;

public class Main_16926 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();

        int[][] arr = new int[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        int shellNum = Math.min(n/2, m/2);
        for(int i=0; i<r; i++){
            for(int j=0; j<shellNum; j++){
                int eleNum = ((n - 2*j - 1) + (m- 2*j - 1)) * 2;
                int[] tmpArr = new int[eleNum + 1];

                int idx = 0;
                for(int k=j; k<m-j-1; k++) tmpArr[idx++] = arr[j][k];
                for(int k=j; k<n-j-1; k++) tmpArr[idx++] = arr[k][m-j-1];
                for(int k=m-j-1; k>j; k--) tmpArr[idx++] = arr[n-j-1][k];
                for(int k=n-j-1; k>j; k--) tmpArr[idx++] = arr[k][j];
                tmpArr[idx] = tmpArr[0];

                idx = 1;
                for(int k=j; k<m-j-1; k++) arr[j][k]= tmpArr[idx++];
                for(int k=j; k<n-j-1; k++) arr[k][m-j-1] = tmpArr[idx++];
                for(int k=m-j-1; k>j; k--) arr[n-j-1][k] = tmpArr[idx++];
                for(int k=n-j-1; k>j; k--) arr[k][j] = tmpArr[idx++];
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    // 배열을 참조하는 함수정의활용으로 모듈화
    // 입출력 최적화 (9만개 입력 통과하긴 하네)
    // 껍데기 길이 모듈러 연산으로 연산 횟수 최적화
    // dx dy 배열로 4개 for문 대체 가능
    // 함수 파라미터로 갈때도 기본형은 값 복사, 참조형은 주소 복사 but 다 call by value
}
