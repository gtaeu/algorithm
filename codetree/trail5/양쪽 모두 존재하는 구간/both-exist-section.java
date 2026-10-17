import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        if (n == 1) {
            System.out.println(-1);
            System.exit(0);
        }

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
        cnt[arr[l]]--;
        set.add(arr[l]);
        int r = 1;
        while (l < n) {
            while (set.size() != m && r < n && cnt[arr[r]] != 1) {
                int temp = arr[r];
                cnt[temp]--;
                set.add(temp);
                r++;
            }

            if (r == n && set.size() != m) break;

            if (set.size() == m) {
                int len = r - l;
                ans = Math.min(ans, len);
            }
            
            cnt[arr[l]]++;
            if (cnt[arr[l]] == copy[arr[l]]) {
                set.remove(arr[l]);
            }
            l++;
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}