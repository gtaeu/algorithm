import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        next = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (k-- > 0) {
            explodeAll();
            rotate();
            build();
        }

        explodeAll();

        // StringBuilder sb = new StringBuilder();

        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         sb.append(board[i][j]);
        //         if (j != n-1) sb.append(' ');
        //     }
        //     if (i != n-1) sb.append('\n');
        // }

        // System.out.println(sb);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) ans++;
            }
        }

        System.out.println(ans);
    }

    static int n, m, k;
    static int[][] board, next;

    static boolean explode(int c) {
        int i = 0;
        boolean tof = false;
        while (i < n) {
            if (board[i][c] == 0) {
                i++;
                continue;
            }
            int j = i;
            while (j < n && board[j][c] == board[i][c]) {
                j++;
            }

            if (j - i >= m) {
                tof = true;
                for (int r = i; r < j; r++) board[r][c] = 0;
            }

            i = j;
        }

        return tof;
    }

    static void build() {
        int[] temp;
        int idx = n-1;
        for (int c = 0; c < n; c++) {
            idx = n-1;
            temp = new int[n];
            for (int r = n-1; r >= 0; r--) {
                if (board[r][c] != 0) {
                    temp[idx--] = board[r][c];
                }
            }

            for (int r = 0; r < n; r++) {
                board[r][c] = temp[r];
            }
        }
    }

    static void rotate() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                next[j][n - 1 - i] = board[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = next[i][j];
            }
        }
    }

    static void explodeAll() {
        for (int i = 0; i < n; i++) {
            while (explode(i)) {
                build();
            }
        }
    }
}

