import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        copy = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                board[i][j] = temp;
                copy[i][j] = temp;
            }
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());
            rotate(sr-1, sc-1, er-1, ec-1);
            for (int c = 0; c < n; c++) System.arraycopy(board[c], 0, copy[c], 0, m);

            for (int r = sr-1; r < er; r++) {
                for (int c = sc-1; c < ec; c++) {
                    toAvg(r, c);
                }
            }
            for (int c = 0; c < n; c++) System.arraycopy(copy[c], 0, board[c], 0, m);    

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static int n, m, q;
    static int[][] board, copy;

    static void rotate(int sr, int sc, int er, int ec) {
        int temp = board[sr][sc];
        for (int i = sr; i < er; i++) board[i][sc] = board[i+1][sc];
        for (int i = sc; i < ec; i++) board[er][i] = board[er][i+1];
        for (int i = er; i > sr; i--) board[i][ec] = board[i-1][ec];
        for (int i = ec; i > sc; i--) board[sr][i] = board[sr][i-1];
        board[sr][sc+1] = temp;
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void toAvg(int r, int c) {
        int sum = board[r][c];
        int cnt = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
            sum += board[nr][nc];
            cnt++;
        }
        copy[r][c] = sum / cnt;
    }
}