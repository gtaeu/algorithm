import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        info = new int[n][2]; // info[i][0]: i번째 동전 타입, info[i][1]: i번째 동전 가치

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("A")) {
                info[i][0] = 0;
                info[i][1] = Integer.parseInt(st.nextToken());
            }
            else {
                info[i][0] = 1;
                info[i][1] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][m+1];
        for (int[] row : dp) Arrays.fill(row, -1);

        int ans = dfs(0, m);
        System.out.println(ans == 1001 ? -1 : ans);
    }

    static int n, m;
    static int[][] info;
    static int[][] dp;

    // dfs(idx, w): 현재 idx부터 고려할 때, w를 거슬러주기 위해서 필요한 동전의 최소 개수
    // (idx, w) -> 안씀(idx+1, w),  씀(idx + 0/1, w - 동전가치)
    // 천만 * 2 = 2천만
    static int dfs(int idx, int w) {
        if (idx == n) {
            if (w == 0) return 0;
            else return 1001;
        }

        if (w < 0) return 1001;

        if (dp[idx][w] != -1) return dp[idx][w];

        int res = 1001;

        res = Math.min(res, dfs(idx+1, w));

        if (info[idx][0] == 0) {
            res = Math.min(res, 1 + dfs(idx, w - info[idx][1]));
        }
        else {
            res = Math.min(res, 1 + dfs(idx+1, w - info[idx][1]));
        }

        return dp[idx][w] = res;
    }
}