import java.util.*;
import java.io.*;

/*
종료조건의 기저조건
이 상태에서 집계할 항목 자체가 없다: 항등원 반환
이 상태에서 상태가 딱 하나 남았다: 그 값자체를 리턴
*/

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
        memo = new int[n][n][t+1];
        dp = new int[n][n][2];
        for (int[][] rows : memo) {
            for (int[] row : rows) Arrays.fill(row, -1);
        }
        for (int[][] rows : dp) {
            for (int[] row : rows) Arrays.fill(row, -1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memoi(i, j, t);
            }
        }

        int ans = dfs(0, 0, 0);
        System.out.println(ans);
    }

    static int n, t;
    static int[][] board;
    static int[][][] memo;
    static int[][][] dp;
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};


    // memoi(r,c,t) : 현재좌표에서 출발하여, t초동안 얻을 수 있는 최대 이득
    // memoi(r,c,t) -> memoi(nr,nc,t-1) 
    // 2의5승 * 백만 = 3천만
    static int memoi(int r, int c, int t) {
        if (t == 0) return board[r][c];

        if (memo[r][c][t] != -1) return memo[r][c][t];

        int res = -5000;

        for (int i = 0; i < 2; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

            res = Math.max(res, board[r][c] + memoi(nr, nc, t-1));
        }

        return memo[r][c][t] = res;
    }

    // dfs(r,c,use) : 현재좌표에서 출발하고, 타임머신 사용 여부가 use일 때, 끝까지 갈 때 얻을 수 있는 최대 이득
    // (r,c,use) -> (nr,nc,use) or (nr,nc,newUse) 최대 총 4가지
    // 백만 * 4 = 2백만
    static int dfs(int r, int c, int use) {
        if (r == n-1 && c == n-1) {
            return board[r][c];
        }

        if (dp[r][c][use] != -1) return dp[r][c][use];

        int res = -2_000_000;
        for (int i = 0; i < 2; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

            // 안쓰기
            res = Math.max(res, board[r][c] + dfs(nr, nc, use)); 
            
            // 안썼으면, 쓰기
            if (use == 0) res = Math.max(res, board[r][c] + memo[r][c][t] + dfs(nr, nc, 1));
        }

        return dp[r][c][use] = res;
    }
}

