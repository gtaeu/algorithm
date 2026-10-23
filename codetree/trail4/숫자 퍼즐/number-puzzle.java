import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n][m+1][m+1];
        for (int[][] rows : dp) {
            for (int[] row : rows) Arrays.fill(row, -1);
        }

        int res = dfs(0, m, 1);

        int idx = 0;
        int sum = m;
        int bef = 1;
        StringBuilder sb = new StringBuilder();
        while (idx != n) {
            for (int i = bef; i <= m; i++) {
                int temp = dfs(idx+1, sum - i, i);
                if (temp >= k) {
                    idx++;
                    sum -= i;
                    bef = i;
                    sb.append(i).append(' ');
                    break;
                }
                else {
                    k -= temp;
                }
            }
        }

        System.out.println(sb);
    }

    static int n, m, k;
    static int[][][] dp;

    // dfs(idx, sum, bef) : 현재 idx이고, sum을 만들어야 하고, bef일 때, 가능한 경우의 수
    // (idx, sum, bef) -> (idx+1, sum - i, i);
    // 10 * 200 * 200 = 40만 * 200(전이) = 8천 * 만 거의 1억
    static int dfs(int idx, int sum, int bef) {
        if (idx == n) {
            return sum == 0 ? 1 : 0;
        }

        if (sum < 0) return 0;

        if (dp[idx][sum][bef] != -1) return dp[idx][sum][bef];

        int res = 0;
        for (int i = bef; i <= m; i++) {
            res += dfs(idx+1, sum - i, i);
        }

        return dp[idx][sum][bef] = res;
    }
}