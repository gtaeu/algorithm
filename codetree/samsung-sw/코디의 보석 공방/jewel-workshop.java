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

    public static void main(String[] args) {
        int q = nextInt();
        nextInt();
        info = new ArrayList<>();
        n = nextInt();
        q--;
        dp = new int[1101][3001];
        int[] ws = new int[1100];
        boolean dpDirty = true;
        boolean sortDirty = true;
        for (int i = 0; i < n; i++) {
            int w = nextInt();
            int v = nextInt();
            info.add(new int[]{1, w, v}); // 1이면 사용가능, 0이면 불가, 0-index
        }
        int m = info.size();

        while (q --> 0) {
            int task = nextInt();
            if (task == 2) {
                info.add(new int[]{1, nextInt(), nextInt()});
                n++;
                dpDirty = true;
                sortDirty = true;
            }
            else if (task == 3) {
                int idx = nextInt() - 1;
                if (idx >= info.size() || info.get(idx)[0] == 0) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(info.get(idx)[2]);
                info.get(idx)[0] = 0;
                dpDirty = true;
                sortDirty = true;
            }
            else if (task == 4) {
                int w = nextInt();
                if (dpDirty) {
                    for (int i = 0; i <= 3000; i++) dp[n][i] = 0;

                    for (int i = n-1; i >= 0; i--) {
                        int[] it = info.get(i);
                        for (int j = 0; j <= 3000; j++) {
                            int res = 0;
                            res = dp[i+1][j];
                            if (it[0] != 0 && j - it[1] >= 0) {
                                res = Math.max(res, it[2] + dp[i+1][j-it[1]]);
                            }
                            dp[i][j] = res;
                        }
                    }
                    dpDirty = false;
                }

                System.out.println(dp[0][w]);
            }
            else if (task == 5) {
                int d = nextInt();
                if (sortDirty) {
                    m = 0;
                    for (int[] t : info) if (t[0] == 1) ws[m++] = t[1];
                    Arrays.sort(ws, 0, m);
                    sortDirty = false;
                }
                // System.out.println(lst);
                int r = 0;
                int cnt = 0;
                for (int l = 0; l < m - 1; l++) {
                    while (r != m && ws[r] - ws[l] <= d) {
                        r++;
                    }

                    if (r - l >= 2) {
                        cnt += r - l - 1;
                    }
                }

                System.out.println(cnt);
            }
        }
        
    }

    static ArrayList<int[]> info;
    static int n;
    static int[][] dp;

    // dfs(idx, w): idx부터 고려, 남은 무게한계가 w일때, 최대 가치
    // (idx, w) -> (idx+1, w - ~) / (idx+1, w)
    // 천 * 3천 * 2 = 6백만 * 30(겉호출) = 1억8천
    static int dfs(int idx, int w) {
        if (idx == n) {
            if (w >= 0) return 0;
            else return Integer.MIN_VALUE;
        }

        if (info.get(idx)[0] == 0) return dfs(idx+1, w);

        if (w < 0) return Integer.MIN_VALUE;

        if (dp[idx][w] != -1) return dp[idx][w];

        int res = 0;

        res = Math.max(res, info.get(idx)[2] + dfs(idx+1, w - info.get(idx)[1]));
        res = Math.max(res, dfs(idx+1, w));

        return dp[idx][w] = res;
    }

}
