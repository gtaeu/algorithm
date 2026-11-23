import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        copy = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, n);
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                explode(i, j);
                build();
                // show();
                ans = Math.max(ans, count());
                for (int k = 0; k < n; k++) {
                    System.arraycopy(copy[k], 0, board[k], 0, n);
                }                  
            }
        }

        System.out.println(ans);
    }

    static int[][] board, copy;
    static int n;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void show() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
                sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void explode(int r, int c) {
        int pow = board[r][c];
        board[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int sr = r;
            int sc = c;
            for (int j = 0; j < pow-1; j++) {
                sr += dr[i];
                sc += dc[i];
                if (sr < 0 || sc < 0 || sr >= n || sc >= n) break;
                board[sr][sc] = 0;
            }
        }
    }

    static void build() {
        int[] temp = new int[n];
        int idx = n-1;
        for (int i = 0; i < n; i++) {
            idx = n-1;
            Arrays.fill(temp, 0);
            for (int j = n-1; j >= 0; j--) {
                if (board[j][i] != 0) temp[idx--] = board[j][i];
            }

            for (int j = 0; j < n; j++) board[j][i] = temp[j];
        }
    }

    static int count() {
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                if (board[i][j] == 0) continue;
                if (board[i][j] == board[i][j+1]) cnt++;
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n-1; i++) {
                if (board[i][j] == 0) continue;
                if (board[i][j] == board[i+1][j]) cnt++;
            }
        }

        return cnt;
    }
}

/*
1. 인접쌍의 수 세기(가로, 세로)
2. 폭발
3. build()

*/