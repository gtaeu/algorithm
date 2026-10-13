import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[2][n * n][t+1];
        dp2 = new int[n * n][2];

        for (int[][] rows : dp) {
            for (int[] row : rows) Arrays.fill(row, Integer.MIN_VALUE);
        }

        for (int[] row : dp2) Arrays.fill(row, Integer.MIN_VALUE);

        int ans = dfs(0, 0, t, 0); // 이러면 (0, 0, t, 1)이 안채워지나??
        int ans2 = dfs2(0, 0, 0);

        System.out.println(ans2);
    }

    static int n, t;
    static int[][] board;
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};
    static int[][][] dp;
    static int[][] dp2;

    // dfs(r, c, t): r,c에서 출발하여 t칸 갔을 때 얻을 수 있는 최대이익 문제는 호출부가 n^2임 5조
    // !! r,c에서 출발하여 시간을 쓰거나 안 쓰거나 할 때, 얻을 수 있는 최대 이득
    // dfs(r, c, t, use) 시간을 한 번 쓰면 쭉 써야함 
    // 시간을 안 쓸고 들고있을 순 있음
    // 즉 값이 필요할때 dp[r][c][t][1] 인 값을 호출하면 됨 
    // 상태공간 천이백만 = 4천만 바이트 = 40 메가바이트
    static int dfs(int r, int c, int cnt, int use) {
        if (cnt == 0) {
            return board[r][c];
        }
        if (dp[use][r*n+c][cnt] != Integer.MIN_VALUE) return dp[use][r*n+c][cnt];

        int res = Integer.MIN_VALUE / 2;

        for (int i = 0; i < 2; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            if (use == 0) {
                // 핵심 : board[r][c] + dfs(nr, nc, cnt-1, 1)이 아니라
                // board[r][c] + dfs(r, c, cnt, 1)을 호출해야했음
                res = Math.max(res, Math.max(board[r][c] + dfs(r, c, cnt, 1), dfs(nr, nc, cnt, use)));
            } else {
                res = Math.max(res, board[r][c] + dfs(nr, nc, cnt-1, use));
            }
        }

        if (res != Integer.MIN_VALUE / 2) dp[use][r*n+c][cnt] = res;
        return res;
    }

    // 상태공간: 2백만 = 8백만 바이트 = 8 메가바이트
    static int dfs2(int r, int c, int use) {
        if (r == n-1 && c == n-1) {
            return board[r][c];
        }

        if (dp2[r*n+c][use] != Integer.MIN_VALUE) return dp2[r*n+c][use];

        int res = Integer.MIN_VALUE / 2;

        for (int i = 0; i < 2; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            if (use == 0) {
                res = Math.max(res, board[r][c] + dfs2(nr, nc, use));
                if (n - r + n - c - 2 >= t && dp[1][r*n+c][t] != Integer.MIN_VALUE && dp[1][r*n+c][t] > 0) {
                    // System.out.println(1);
                    res = Math.max(res, dp[1][r*n+c][t] + board[r][c] + dfs2(nr, nc, 1));
                }
            }
            else {
                res = Math.max(res, board[r][c] + dfs2(nr, nc, 1));
            }
        }

        if (res != Integer.MIN_VALUE / 2) dp2[r*n+c][use] = res;

        return res;
    }
}


// dp, 상태 r, c, 0/1, 
// 천 * 천 * 2 * 
// 176MB 약 6천만개
// dfs(r, c, use) use상태이고 r,c부터 끝까지 갈때 얻을 수 있는 최대 가치
// r,c,t : 현재r,c일때 t초만큼 갈 때 얻을 수 있는 최대 이득
// r,c,t,use : r,c에서 출발해서 아직 사용안했으면 t초후 최대이득, 끝까지 갔을때 최대이득
// 아니면 dp2개로 나눠서 r,c,t // r,c,use
// n*2 * n*2 * 5 
// dp[r][c][2] : r,c에서 출발하여 끝까지 갔을 때 얻을 수 있는 최대 이득과 걸린시간(이동거리) 사실이건 r+c
// dp[r][c][0]: 최대이득, dp[r][c][1]: 이동거리 <- 중복이긴함
// 