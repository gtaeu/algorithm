class Solution {
    public int maximalRectangle(char[][] matrix) {
        board = matrix;
        w = matrix[0].length;
        h = matrix.length;

        dp = new int[h][w][w+1];
        for (int[][] rows : dp) {
            for (int[] row : rows) Arrays.fill(row, -1);
        }

        int ans = 0;
        maxW = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                maxW[i][j] = dfs2(i, j);
            }
        }

        System.out.println(Arrays.deepToString(maxW));

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] == '0') continue;
                for (int len = 1; j + len <= w; len++) {
                    int temp = dfs(i, j, len);
                    
                    if (ans < temp * len) {
                        ans = temp * len;
                        // System.out.println(i + " " + j + " " + len + " " + temp);
                    }
                }
            }
        }

        return ans;
    }

    static char[][] board;
    static int[][][] dp;
    static int[][] maxW;
    static int w, h;

    // dfs(r,c,w) : 좌표가 r,c이고 가로너비가 w일때, 가능한 최대 세로
    // (r,c,w) -> (r+1, c, w)
    // 200 * 200 * 200 = 8백만
    static int dfs(int r, int c, int w) {
        if (r == h) {
            return 0;
        }

        if (maxW[r][c] < w) return 0;

        if (dp[r][c][w] != -1) return dp[r][c][w];

        int res = 1;
        int temp = dfs(r+1, c, w);

        return dp[r][c][w] = res + temp;
    }


    // 이 좌표부터 시작할때, 가장 긴 오른쪽 1의 길이
    // (r, c) -> (r, c+1)
    static int dfs2(int r, int c) {
        if (c == w) return 0;

        if (board[r][c] == '0') return 0;

        int res = 0;

        res = 1 + dfs2(r, c+1);

        return res;
    }
}