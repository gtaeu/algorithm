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

        ArrayList<int[][]> lst = new ArrayList<>();
        lst.add(new int[][]{{0, 0}, {1, 0}, {1, 1}});
        lst.add(new int[][]{{0, 0}, {0, 1}, {0, 2}});

        int ans = 0;

        for (int[][] lego : lst) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < 4; k++) {
                        ans = Math.max(ans, getSum(i, j, lego));
                        lego = rotate(lego);
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static int n, m;
    static int[][] board;

    static int getSum(int i, int j, int[][] lego) {
        int res = 0;
        for (int[] loc : lego) {
            int r = i + loc[0];
            int c = i + loc[1];
            if (r < 0 || c < 0 || r >= n || c >= n) return 0;
            res += board[r][c];
        }
        return res;
    }

    static int[][] rotate(int[][] a) {
        int[][] temp = new int[a.length][2];
        for (int i = 0; i < a.length; i++) {
            temp[i][0] = a[i][1];
            temp[i][1] = -a[i][0];
        }
        return temp;
    }
}