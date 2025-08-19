import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.List;
import java.util.LinkedList;

public class s1230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        for(int tc=1; tc<=10; tc++){
            int n = Integer.parseInt(br.readLine());

            List<Integer> arr = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                int value = Integer.parseInt(st.nextToken());
                arr.add(value);
            }

            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<m; i++){
                char imp = st.nextToken().charAt(0);
                if(imp == 'I'){
                    int idx = Integer.parseInt(st.nextToken());
                    int cnt = Integer.parseInt(st.nextToken());
                    for(int j=0; j<cnt; j++){
                        int value = Integer.parseInt(st.nextToken());
                        arr.add(idx+j, value);
                    }
                }else if(imp == 'D'){
                    int idx = Integer.parseInt(st.nextToken());
                    int cnt = Integer.parseInt(st.nextToken());
                    for(int j=0; j<cnt; j++) arr.remove(idx);
                }else if(imp == 'A'){
                    int cnt = Integer.parseInt(st.nextToken());
                    for(int j=0; j<cnt; j++){
                        int value = Integer.parseInt(st.nextToken());
                        arr.add(value);
                    }
                }
            }

            sb = new StringBuilder();
            sb.append("#"+tc+" ");
            int size = Math.min(10, arr.size());
            for(int i=0; i<size; i++) sb.append(arr.get(i)).append(" ");
            System.out.println(sb);
        }
    }
}
