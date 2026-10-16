import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        dp = new int[n][n][k+1];
        for (int[][] rows : dp) {
            for (int[] row : rows) Arrays.fill(row, -1);
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // System.out.println(Arrays.deepToString(board));

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = dfs(i, j, k);
                if (ans > temp) {
                    ans = temp;
                    // System.out.println(i + " " + j);
                }
            }
        }
        // System.out.println(dfs(0, 0, k));
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int n, k;
    static int[][] board;
    static int[][][] dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    // 현재좌표부터 고려할때로 짜도 어짜피 n^2으로 호출해야한다
    // 그래서 그냥 문제를 현재좌표부터 출발할때로 하자
    // dfs(r,c,cnt) 현재좌표부터 출발하고 k칸 갈 수 있을때, 얻을 수 있는 인접경로차들의 최대의 최소
    // dfs(r,c,cnt) = 최대(dfs(nr,nc,cnt-1), 현재좌표 nr,nc의 차이)들 중 최소
    static int dfs(int r, int c, int cnt) {
        if (cnt == 1) return 0;

        if (dp[r][c][cnt] != -1) return dp[r][c][cnt];

        int res = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

            if (board[nr][nc] > board[r][c]) {
                int temp = dfs(nr, nc, cnt - 1);
                int temp2 = board[nr][nc] - board[r][c];
                int max = Math.max(temp, temp2);
                res = Math.min(res, max);
            }
        }
        // System.out.println(res);

        return dp[r][c][cnt] = res;
    }
}