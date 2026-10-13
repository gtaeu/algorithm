import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[m][1 << n];
        for (int[] row : dp) Arrays.fill(row, -1);

        System.out.println(dfs(0, 0));
    }

    static int n, m;
    static int[][] dp;

    static int dfs(int idx, int mask) {
        if (idx == m) {
            return mask == 0 ? 1 : 0;
        }

        if (dp[idx][mask] != -1) return dp[idx][mask];

        return dp[idx][mask] = fill(idx, n, mask, 0);
    }

    // fill(idx, h, col, now) : 현재높이 h이고, 상태가 col일때, col을 전부 1로 채우면 나타나는 다음 idx 상태들
    static int fill(int idx, int h, int col, int next) {
        if (h == 0) {
            if (col == (1 << n) - 1) {
                return dfs(idx+1, next);
            }
            return 0;
        }

        int res = 0;
        if (((col >> h-1) & 1) == 1) {
            res += fill(idx, h-1, col, next);
        }
        else {
            if (idx + 2 <= m) res += fill(idx, h-1, col | (1 << h-1), next | (1 << h-1)); // 가로칸
            if ((h-2 >= 0) && (((col >> h-2) & 1) == 0)) { // 세로칸
                int newCol = col | 0b11 << h-2;
                res += fill(idx, h-2, newCol, next);
            }
        }

        return res % 10007;
    }
}