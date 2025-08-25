import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 60m
// 정수가 아닌 문자열의 unionFind 맵으로(int[] parent 불가)
// 맵을 이용한 key -> value 방향 트리 (즉, value가 부모. map은 value -> key 지원x)
// 시간 초과였는데 경로 압축으로 해결
public class bj4195다시 {
    static Map<String, String> parent;
    static Map<String, Integer> size; // v 개수 구하기용 맵
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc=0; tc<t; tc++){
            parent = new HashMap<>();
            size = new HashMap<>();

            int f = Integer.parseInt(br.readLine());
            for(int i=0; i<f; i++){
                st = new StringTokenizer(br.readLine());
                String str1 = st.nextToken();
                String str2 = st.nextToken();
                union(str1, str2);

                if(find(str1).equals(find(str2))) System.out.println(size.get(find(str1))); // 두 문자열을 루트로 하는 문자열 개수 (2개 포함)
                else System.out.println(2);
            }
        }
    }

    static String find(String str){
        if(!parent.containsKey(str)){
            parent.put(str, str);
            size.put(str, 1);
        }
 
        String root = parent.get(str);
        if (!root.equals(str)) {
            root = find(root);
            parent.put(str, root); // 경로 압축
        }
        return root;
    }

    // rank 안함
    static void union(String str1, String str2){
        String str1Root = find(str1);
        String str2Root = find(str2);
        if(!str1Root.equals(str2Root)){
            parent.put(str1Root, str2Root);
            // 맵 키의 값은 하나만 가능하구나
            size.put(str2Root, size.get(str2Root) + size.get(str1Root));
        }
    }
}
