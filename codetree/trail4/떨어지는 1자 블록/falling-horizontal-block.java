import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        k--;

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        idx = -1;
        while(go()) idx++;

        if (idx != -1) {
            for (int i = k; i < k+m; i++) {
                board[idx][i] = 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static int n, m, k;
    static int idx;
    static int[][] board;

    static boolean go() {
        for (int i = k; i < k+m; i++) {
            if (board[idx+1][i] != 0) return false;
        }
        return true;
    }
}