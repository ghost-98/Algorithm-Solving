import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// 크루스칼 - 가중치 낮은애부터 선택
// 커스텀 자료형은 클래스로 만들면 됨
// 수학 - 루트, 제곱
// sort, comparator
// 실수 자릿수 표현 - printf
public class bj4386크루스칼과배운점 {
    static int[] parent;
    static List<double[]> star;
    static List<double[]> edge; // int, int, double 담는 클래스 만드는게 더 안전
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        star = new ArrayList<>(n+1);
        star.add(new double[] {0, 0});
        for(int i=0; i<n; i++){
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            star.add(new double[] {x, y});
        }

        edge = new ArrayList<>();
        for(int i=1; i<=n-1; i++){
            for(int j=i+1; j<=n; j++){
                edge.add(new double[] {i, j, getWeight(i, j)});
            }
        }
        edge.sort((e1, e2) -> Double.compare(e1[2], e2[2])); // sort와 comparator 사용법

        parent = new int[n+1];
        for(int i=1; i<=n; i++) parent[i] = i;
        
        double ret = 0;
        for(int edgeIdx=0, edgeCnt=0; edgeCnt<n-1; edgeIdx++){
            int x = (int)edge.get(edgeIdx)[0]; // 좀 조잡한 형변환..
            int y = (int)edge.get(edgeIdx)[1];
            if(find(x) == find(y)) continue;
            union(x, y);
            ret += edge.get(edgeIdx)[2];
            edgeCnt++;
        }

        System.out.printf("%.2f", ret); // 실수 자릿수 표현 printf
    }

    static double getWeight(int i, int j){
        double x1 = star.get(i)[0];
        double y1 = star.get(i)[1];
        double x2 = star.get(j)[0];
        double y2 = star.get(j)[1];
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)); // 수학 - 루트
    }

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) parent[rootY] = rootX;
    }
}