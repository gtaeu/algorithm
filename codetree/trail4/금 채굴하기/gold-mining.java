import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, backtrack(i, j));
            }
        }

        System.out.println(ans);
    }

    static int[][] dd = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    static int n, m;
    static int[][] board;

    // backtrack(r, c, k, cnt) : r,c이고 길이k일때 금이득 - 손해
    static int backtrack(int r, int c) {

        int res = 0;
        int cnt = 0;

        for (int k = 0; k <= 2*n-2; k++) {
            int sr = r - k;
            int sc = c;            
            for (int[] d : dd) {
                for (int i = 0; i < k; i++) {
                    if (!(sr < 0 || sc < 0 || sr >= n || sc >= n)) {
                        if (board[sr][sc] == 1) cnt++;
                    }
                    sr += d[0];
                    sc += d[1];
                }
            }
            int temp = cnt * m - (k*k + (k+1)*(k+1));
            if (temp >= 0) {
                res = Math.max(res, cnt);
                System.out.println(r + " " + c + " " + k + " "  + res);
            }
        }

        return res;
    }
}