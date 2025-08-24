import java.util.Scanner;
// 0번 노드가 항상 루트노드는 아닐 가능성이 있음을 문제 읽으며 알았어야 함
// -> 조건문에 parentNode[i]!=-1 포함

// 트리니까 재귀로 풀어보자
// 물론 트리 인접리스트 + dfs + boolean deleted 이런걸로 풀면 쉽다는거 앎
public class bj1068아직 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int MIN = Integer.MIN_VALUE;
        int n = sc.nextInt();
        int[] parentNode = new int[n];
        for(int i=0; i<n; i++) parentNode[i] = sc.nextInt();
        int deleteNode = sc.nextInt();
        parentNode[deleteNode] = MIN;

        // 반응 없을때까지 여러번 순회하는건데 효율적인가?
        boolean flag = true;
        while(flag){
            flag = false;
            for(int i=0; i<n; i++){
                if(parentNode[i]!=MIN && parentNode[i]!=-1 && parentNode[parentNode[i]]==MIN){
                    parentNode[i] = MIN;
                    flag = true;
                }
            }
        }

        /** 이렇게 했었는데 꼬일 수가 있음. 아직 부모 확인 안한 노드를 min처리 한다든지,,,
        int nodeCnt = 0;
        for(int i=0; i<n; i++){
            if(parentNode[i]!=MIN && parentNode[i]!=-1) parentNode[parentNode[i]] = MIN;
        }
        for(int i=0; i<n; i++){
            if(parentNode[i] != MIN) nodeCnt++;
        }
        **/

        int nodeCnt = 0;
        int parentNodeCnt = 0;
        
        boolean[] parentNodeCheck = new boolean[n];
        for(int i=0; i<n; i++){
            if(parentNode[i]!=MIN && parentNode[i]!=-1) parentNodeCheck[parentNode[i]] = true;
        }

        for(int i=0; i<n; i++){
            if(parentNode[i] != MIN) nodeCnt++;
        }
        for(int i=0; i<n; i++){
            if(parentNodeCheck[i]) parentNodeCnt++;
        }

        System.out.print(nodeCnt - parentNodeCnt);
    }
}