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

        int ans = 0;
        int[] col = new int[n];
        for (int i = 0; i < n; i++) {
            ans += check(board[i]);
            for (int j = 0; j < n; j++) col[j] = board[j][i];
            ans += check(col);
        }

        System.out.println(ans);
    }

    static int n, m;
    static int[][] board;

    static int check(int[] a) {
        if (m == 1) return 1;
        int cnt = 1;
        for (int i = 0; i < n-1; i++) {
            if (a[i] == a[i+1]) {
                cnt++;
                if (cnt == m) return 1;
            }
            else {
                cnt = 1;
            }
        }
        return 0;
    }
}