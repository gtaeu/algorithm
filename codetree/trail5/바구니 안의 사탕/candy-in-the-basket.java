import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[1_000_000 + 1];

        int maxPos = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());
            arr[pos] = cnt;
            maxPos = Math.max(maxPos, pos);
        }
        

        int r = 0;
        int cnt = 0;
        int ans = 0;


        if (maxPos - 2*k < 0) {
            System.out.println(Arrays.stream(arr).sum());
            System.exit(0);
        }
        
        for (int l = 0; l <= maxPos - 2*k; l++) {
            while (r != maxPos+1 && r-l < 2*k+1) {
                cnt += arr[r];
                r++;
            }

            if (r-l == 2*k+1) {
                ans = Math.max(ans, cnt);
            }

            cnt -= arr[l];
        }

        System.out.println(ans);

    }
}