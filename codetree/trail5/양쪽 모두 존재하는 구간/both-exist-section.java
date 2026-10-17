import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        
        int[] cnt = new int[m+1];
        int[] copy = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            arr[i] = a;
            cnt[a]++;
            copy[a]++;
        }

        int ans = Integer.MAX_VALUE;

        int l = 0;
        int r = n-1;
        for (int i = l; i <= r; i++) {
            if (cnt[arr[i]] <= 1) {
                l = i;
                break;
            }
            else {
                cnt[arr[i]]--;
            }
        }
        for (int i = r; i >= l; i--) {
            if (cnt[arr[i]] <= 1) {
                r = i;
                break;
            }
            else {
                cnt[arr[i]]--;
            }
        }

        int leftFirst = 1;

        for (int i = 1; i <= m; i++) {
            if (cnt[i] == 0 || cnt[i] == copy[i]) {
                leftFirst = 0;
            }
        }
        if (leftFirst == 1) ans = Math.min(r - l + 1, ans);

        l = 0;
        r = n-1;
        System.arraycopy(copy, 0, cnt, 0, m);
        for (int i = r; i >= l; i--) {
            if (cnt[arr[i]] <= 1) {
                r = i;
                break;
            }
            else {
                cnt[arr[i]]--;
            }
        }
        for (int i = l; i <= r; i++) {
            if (cnt[arr[i]] <= 1) {
                l = i;
                break;
            }
            else {
                cnt[arr[i]]--;
            }
        }

        int rightFirst = 1;

        for (int i = 1; i <= m; i++) {
            if (cnt[i] == 0 || cnt[i] == copy[i]) {
                rightFirst = 0;
            }
        }

        if (rightFirst == 1) ans = Math.min(r - l + 1, ans);
        
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}