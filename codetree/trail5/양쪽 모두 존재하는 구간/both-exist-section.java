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
        while (r < n-1) {
            if (set.size() == m) {
                ans = Math.min(ans, r - l + 1);
                cnt[arr[l]]++;
                if (cnt[arr[l]] == copy[arr[l]]) set.remove(arr[l]);
                l++;
                continue;
            }

            r++;
            cnt[arr[r]]--;
            if (!set.contains(arr[r])) {
                set.add(arr[r]);
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}