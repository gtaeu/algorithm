import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        lookup = new int[n][m]; // lookup[i][j] : 현재위치기준 가장 오른쪽으로 갈 수 있는 길이 [)
        for (int[] row : lookup) Arrays.fill(row, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                build(i, j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] <= 0) continue;
                int res = lookup[i][j];
                int minLen = lookup[i][j];
                for (int k = i+1; k < n; k++) {
                    minLen = Math.min(minLen, lookup[k][j]);
                    res = Math.max(res, (k - i + 1) * minLen);
                }
                ans = Math.max(ans, res);
            }
        }

        System.out.println(ans == 0 ? -1 : ans);
    }

    static int n, m;
    static int[][] board;
    static int[][] lookup;
    static int ans;

    static int build(int i, int j) {
        if (j == m) return 0;
        if (board[i][j] <= 0) return 0;

        if (lookup[i][j] != -1) return lookup[i][j];

        int res = 1;
        res += build(i, j+1);

        return lookup[i][j] = res;
    }

}
