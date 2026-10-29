import java.util.*;
import java.io.*;

public class Main {
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
        len = new int[]{0, 0};
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                for (int a = 1; a < n; a++) {
                    for (int b = 1; b < n; b++) {
                        len[0] = b;
                        len[1] = a;
                        ans = Math.max(ans, backtrack(r, c, 0, 0));
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static int n;
    static int[][] board;
    static int[] len;
    static int[] dr = {-1, -1, 1, 1};
    static int[] dc = {1, -1, -1, 1};

    static int backtrack(int r, int c, int d, int cnt) {
        if (r < 0 || c < 0 || r >= n || c >= n) return Integer.MIN_VALUE;

        if (cnt == len[d % 2]) {
            if (d == 3) return 0;
            else {
                d++;
                cnt = 0;
            }
        }

        return board[r][c] + backtrack(r + dr[d], c + dc[d], d, cnt+1);
    }



}