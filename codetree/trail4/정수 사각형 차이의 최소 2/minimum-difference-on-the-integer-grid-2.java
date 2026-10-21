import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        dp = new int[n][n][101];
        for (int[][] rows : dp) {
            for (int[] row : rows) Arrays.fill(row, -1);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 100;
        for (int i = 1; i <= 100; i++) {
            int temp = dfs(0, 0, i);
            if (temp == 101) continue;
            // System.out.println(temp + " " + i);
            ans = Math.min(ans, temp - i);
        }

        System.out.println(ans);
    }

    static int n;
    static int[][] board;
    static int[][][] dp;
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};


    // dfs(r, c, min) : r,c를 출발점으로 하여 도착점까지 갈 떄, min(하계)이상인 값에 대하여, 최대의 최소
    // (r, c, min) -> (nr, nc, min)
    static int dfs(int r, int c, int min) {
        if (r == n-1 && c == n-1) {
            if (board[r][c] >= min) return board[r][c];
            else return 101;
        }

        if (board[r][c] < min) return 101;

        if (dp[r][c][min] != -1) return dp[r][c][min];

        int res = 101;
        
        for (int i = 0; i < 2; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

            int max = board[r][c];
            max = Math.max(max, dfs(nr, nc, min));

            res = Math.min(res, max);
        }

        return dp[r][c][min] = res;
    }
}