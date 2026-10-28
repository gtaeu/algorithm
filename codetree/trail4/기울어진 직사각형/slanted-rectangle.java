import java.util.*;
import java.io.*;

public class Main {
    static final int inf = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        len = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int r = i, c = j;
                int res = 0;
                for (int a = 1; a < n; a++) {
                    for (int b = 1; b < n; b++) {
                        len[0] = b; // 오른쪽위 b번
                        len[1] = a; // 왼쪽위 a번
                        // System.out.println(backtrack(r, c, 0, 0));
                        ans = Math.max(ans, backtrack(r, c, 0, 0));
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static int[][] board;
    static int n;
    static int[] dr = {-1, -1, 1, 1};
    static int[] dc = {1, -1, -1, 1};
    static int[] len;

    static int backtrack(int r, int c, int d, int cnt) {
        if (r < 0 || c < 0 || r >= n || c >= n) return -inf;

        if (cnt == len[d % 2]) {
            if (d == 3) return 0;
            else {
                d++;
                cnt = 0;
            }
        }

        int res = board[r][c] + backtrack(r + dr[d], c + dc[d], d, cnt+1);

        return res;
    }
}