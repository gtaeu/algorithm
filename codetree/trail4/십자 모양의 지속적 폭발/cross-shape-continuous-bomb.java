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

        for (int i = 0; i < m; i++) {
            int c = Integer.parseInt(br.readLine());
            bomb(c-1);
            reconstruct();
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

    static int n, m;
    static int[][] board;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void bomb(int col) {
        int cnt = 0;
        int row = 0;
        for (int i = 0; i < n; i++) {
            if (board[i][col] != 0) {
                cnt = board[i][col];
                row = i;
                board[i][col] = 0;
                break;
            }
        }
        for (int d = 0; d < 4; d++) {
            int nr = row;
            int nc = col;
            for (int j = 0; j < cnt-1; j++) {
                nr += dr[d];
                nc += dc[d];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) break;
                board[nr][nc] = 0;
            }
        }
    }

    static void reconstruct() {
        for (int i = 0; i < n; i++) {
            int[] temp = new int[n];
            int idx = n-1;
            for (int j = n-1; j >= 0; j--) {
                if (board[j][i] != 0) temp[idx--] = board[j][i];
            }
            for (int j = 0; j < n; j++) {
                board[j][i] = temp[j];
            }
        }
    }

}