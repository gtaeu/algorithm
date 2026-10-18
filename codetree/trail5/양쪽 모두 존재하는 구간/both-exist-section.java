import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] in = new int[m+1];
        int[] out = new int[m+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            arr[i] = temp;
            out[temp]++;
        }

        int outKind = (int) Arrays.stream(out).filter(i -> i >= 2).count();


        if (outKind != m) {
            System.out.println(-1);
            System.exit(0);
        }

        int inKind = 1;
        in[arr[0]]++;
        out[arr[0]]--;
        int r = 1;
        int ans = Integer.MAX_VALUE;

        for (int l = 0; l < n; l++) {
            while (r != n && inKind != m && out[arr[r]] != 1) {
                if (in[arr[r]] == 0) {
                    inKind++;
                }
                in[arr[r]]++;
                r++;
            }

            if (inKind == m) {
                ans = Math.min(ans, r - l);
            }

            in[arr[l]]--;
            if (in[arr[l]] == 0) inKind--;
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}