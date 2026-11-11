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
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m1 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());
        m3 = Integer.parseInt(st.nextToken());
        m4 = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        rotate(r-1, c-1, m1, m2, m3, m4, d);
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

    static int n, r, c, m1, m2, m3, m4, d;
    static int[][] board;

    static void rotate(int r, int c, int m1, int m2, int m3, int m4, int d) {
        if (d == 0) { // 반시계방향
            int temp = board[r][c];
            for (int i = 0; i < m4; i++) {
                board[r][c] = board[--r][--c];
            }
            for (int i = 0; i < m3; i++) {
                board[r][c] = board[--r][++c];
            }
            for (int i = 0; i < m2; i++) {
                board[r][c] = board[++r][++c];
            }
            for (int i = 0; i < m1; i++) {
                board[r][c] = board[++r][--c];
            }
            board[r-1][c+1] = temp;
        }
        else {
            int temp = board[r][c];
            for (int i = 0; i < m1; i++) board[r][c] = board[--r][++c];
            for (int i = 0; i < m2; i++) board[r][c] = board[--r][--c];
            for (int i = 0; i < m3; i++) board[r][c] = board[++r][--c];
            for (int i = 0; i < m4; i++) board[r][c] = board[++r][++c];
            board[r-1][c-1] = temp;
        }
    }
}