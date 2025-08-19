import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.LinkedList;
public class s5122 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            List<Integer> arr = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                int value = Integer.parseInt(st.nextToken());
                arr.add(value);
            }

            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                char imp = st.nextToken().charAt(0);
                if(imp == 'I'){
                    int idx = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());
                    arr.add(idx, value);
                }else if(imp == 'D'){
                    int idx = Integer.parseInt(st.nextToken());
                    arr.remove(idx);
                }else if(imp == 'C'){
                    int idx = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());
                    arr.set(idx, value);
                }
            }

            if(arr.size()<l) System.out.println("#"+tc+" -1");
            else System.out.println("#"+tc+" "+arr.get(l));
        }
    }

    static boolean check(int bitmasking){
        return(bitmasking & ((1<<10)-1)) == ((1<<10)-1);
    }
}
