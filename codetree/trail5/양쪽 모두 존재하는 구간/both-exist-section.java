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
        HashSet<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            arr[i] = a;
            cnt[a]++;
            copy[a]++;
        }
        
        int ans = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        set.add(arr[l]);
        cnt[arr[l]]--;
        int pass = -1;
        while (l <= r && r < n) {
            while (true) {
                if (set.size() == m && pass == 1) ans = Math.min(ans, r - l + 1);

                if (r == n-1) break;

                int temp = arr[r+1];
                if (cnt[temp] == 1) break;

                cnt[temp]--;
                set.add(temp);
                r++;

                if (set.size() == m) {
                    ans = Math.min(ans, r - l + 1);
                    pass = 1;
                    break;
                }
            }

            // System.out.println(l + " " + r );

            cnt[arr[l]]++;
            if (cnt[arr[l]] == copy[arr[l]]) {
                set.remove(arr[l]);
                pass = 0;
            }
            l++;
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}