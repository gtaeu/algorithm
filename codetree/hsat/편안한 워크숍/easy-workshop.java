import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        dp = new int[n][n][k];
        for (int[][] rows : dp) {
            for (int[] row : rows) Arrays.fill(row, -1);
        }
        

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ans > dfs(i, j, k-1)) {
                    ans = dfs(i, j, k-1);
                }
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int n, k;
    static int[][] board;
    static int[][][] dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    // dfs(r, c, rem) : r,c에서 출발하여 rem번 갈 수 있을때, 경로 차이들의 최댓값의 최소
    // (r, c, rem) -> (nr, nc, rem-1)
    // 100 * 100 * 100 = 100만 * 2 -> 200만
    static int dfs(int r, int c, int rem) {
        if (rem == 0) {
            return 0;
        }

        if (dp[r][c][rem] != -1) return dp[r][c][rem];

        int res = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

            if (board[nr][nc] <= board[r][c]) continue;

            res = Math.min(res, Math.max(board[nr][nc] - board[r][c], dfs(nr, nc, rem-1)));
        }

        return dp[r][c][rem] = res;
    }
}