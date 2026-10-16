import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String next() {
        if (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }

    public static void main(String[] args) throws IOException {

        q = nextInt();
        nextInt();
        n = nextInt();
        info = new ArrayList<>(); // info.get(i) = {사용가능(1), 보석무게, 보석가치}
        cnt = new int[3001];
        memo = new int[1100][3001];
        int[] dp = new int[3001];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int w = nextInt();
            int v = nextInt();
            info.add(new int[]{1, w, v}); // 0인덱스
            
        }
        for (int r = 0; r < info.size() - 1; r++) {
            for (int c = r+1; c < info.size(); c++) {
                int abs = Math.abs(info.get(r)[1] - info.get(c)[1]);
                cnt[abs]++;
            }
        }
        for (int i = 0; i < q-1; i++) {
            int num = nextInt();
            if (num == 2) {
                int w = nextInt();
                int v = nextInt();
                for (int j = 0; j < info.size(); j++) {
                    if (info.get(j)[0] == 0) continue;
                    int abs = Math.abs(w - info.get(j)[1]);
                    cnt[abs]++;
                }
                info.add(new int[]{1, w, v});
                n++;
                
            }
            else if (num == 3) {
                int idx = nextInt() - 1;

                
                if (idx > info.size() - 1) {
                    sb.append(-1).append('\n');
                    continue;
                }
 
                if (info.get(idx)[0] != 0) {
                    for (int j = 0; j < info.size(); j++) {
                        if (info.get(j)[0] == 0) continue;
                        if (j == idx) continue;
                        int abs = Math.abs(info.get(idx)[1] - info.get(j)[1]);
                        cnt[abs]--;
                    }
                }

                if (info.get(idx)[0] == 0) sb.append(-1).append('\n');
                else {
                    sb.append(info.get(idx)[2]).append('\n');
                    info.get(idx)[0] = 0;
                }
            }
            else if (num == 4) {
                int w = nextInt();
                Arrays.fill(dp, 0); 
                for (int[] arr : info) {
                    if (arr[0] == 0) continue;

                    for (int j = w; j >= arr[1]; j--) {
                        dp[j] = Math.max(dp[j], dp[j - arr[1]] + arr[2]);
                    }
                }

                sb.append(dp[w]).append('\n');
            }
            else {
                d = nextInt();

                // 남아있는 보석의 무게만 모아서 오름차순 정렬
                int[] ws = new int[info.size()];
                int m = 0;
                for (int[] a : info) {
                    if (a[0] == 1) ws[m++] = a[1];
                }
                Arrays.sort(ws, 0, m);

                // 투포인터: k와 무게 차이가 d 이하인 "k 뒤쪽" 보석의 개수 = j - k - 1
                int ans = 0;
                int j = 0;
                for (int k = 0; k < m; k++) {
                    while (j < m && ws[j] - ws[k] <= d) j++;
                    ans += j - k - 1;
                }
                sb.append(ans).append('\n');
            }
        }
        System.out.print(sb);
    }

    static int q, n, d;
    static ArrayList<int[]> info;
    static int[][] memo;
    static int[] cnt;

    // show(idx,w): idx부터 고려하고, 채울수있는 무게 한계가 w일때, 고른 보석들의 가치합 최대
    // (idx,w) -> (idx+1, w - ~) / (idx, w) 사용가능 상태인지 따져야함
    static int show(int idx, int w) {
        if (idx == n) {
            if (w >= 0) return 0;
            else return Integer.MIN_VALUE;
        }

        if (w < 0) return Integer.MIN_VALUE;

        if (memo[idx][w] != -1) return memo[idx][w];

        int res = Integer.MIN_VALUE;

        int[] arr = info.get(idx);
        if (arr[0] == 0) {
            res = Math.max(res, show(idx+1, w));
        }
        else {
            res = Math.max(res, show(idx+1, w));
            res = Math.max(res, arr[2] + show(idx+1, w - arr[1]));
        }

        return memo[idx][w] = res;
    }
}