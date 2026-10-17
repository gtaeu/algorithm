import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        int[] b = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int aIdx = 0;
        int ans = 1;

        for (int i = 0; i < m; i++) {

            while (aIdx < n && a[aIdx] != b[i]) {
                aIdx++;
            }

            if (aIdx == n) {
                ans = 0;
                break;
            }
        }

        System.out.println(ans == 1 ? "Yes" : "No");
    }
}