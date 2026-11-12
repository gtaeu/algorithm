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

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int cnt = board[r][c];
        board[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            bomb(r, c, i, cnt-1);
        }

        for (int i = 0; i < n; i++) {
            int[] temp = new int[n];
            int idx = n-1;
            for (int j = n-1; j >= 0; j--) {
                if (board[j][i] == 0) continue;
                temp[idx] = board[j][i];
                idx--;
            }
            for (int j = 0; j < n; j++) {
                board[j][i] = temp[j];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
                if (j != n-1) sb.append(' ');
            }
            if (i != n-1) sb.append('\n');
        }
        System.out.println(sb);
    }

    static int n;
    static int[][] board;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void bomb(int r, int c, int d, int cnt) {
        if (cnt == 0) return;

        int nr = r + dr[d];
        int nc = c + dc[d];
        if (nr < 0 || nc < 0 || nr >= n || nc >= n) return;

        board[nr][nc] = 0;
        bomb(nr, nc, d, cnt-1);
    }
}