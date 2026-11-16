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
        copy = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (k --> 0) {
            for (int c = 0; c < n; c++) {
                while (toBomb(c)) {
                    int cnt = 1;
                    int num = board[0][c];
                    int idx = 0;
                    for (int i = 0; i < n; i++) {
                        if (board[i][c] != 0) {
                            num = board[i][c];
                            idx = i;
                            cnt = 1;
                            break;
                        }

                    }
                    for (int i = idx+1; i < n; i++) {
                        if (board[i][c] == num) cnt++;
                        else {
                            if (cnt >= m) {
                                for (int j = 0; j < cnt; j++) {
                                    board[idx++][c] = 0;
                                }
                            }
                            cnt = 1;
                            num = board[i][c];
                            idx = i;
                        }
                    }
                    if (cnt >= m) {
                        for (int j = 0; j < cnt; j++) {
                            board[idx++][c] = 0;
                        }
                    }
                    build();
                }
                
                
            }

            rotate();
            build();

        }

        

        for (int c = 0; c < n; c++) {
            while (toBomb(c)) {
                int cnt = 1;
                int num = board[0][c];
                int idx = 0;
                for (int i = 0; i < n; i++) {
                    if (board[i][c] != 0) {
                        num = board[i][c];
                        idx = i;
                        cnt = 1;
                        break;
                    }

                }
                for (int i = idx+1; i < n; i++) {
                    if (board[i][c] == num) cnt++;
                    else {
                        if (cnt >= m) {
                            for (int j = 0; j < cnt; j++) {
                                board[idx++][c] = 0;
                            }
                        }
                        cnt = 1;
                        num = board[i][c];
                        idx = i;
                    }
                }
                if (cnt >= m) {
                    for (int j = 0; j < cnt; j++) {
                        board[idx++][c] = 0;
                    }
                }
            }
            build();
            
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) ans++;
            }
        }
        System.out.println(ans);
    }

    static int n, m, k;
    static int[][] board;
    static int[][] copy;

    static boolean toBomb(int c) {
        int cnt = 0;
        int num = 0;
        int idx = -1;
        for (int i =0; i < n; i++) {
            if (board[i][c] != 0) {
                cnt = 1;
                num = board[i][c];
                idx = i;
                break;
            }
        }

        if (num == 0) return false;

        for (int i = idx+1; i < n; i++) {
            if (board[i][c] == num) {
                cnt++;
                if (cnt >= m) return true;
            }
            else {
                cnt = 1;
                num = board[i][c];
            }
        }

        return false;
    }

    static void build() {
        for (int c = 0; c < n; c++) {
            int[] temp = new int[n];
            int idx = n-1;
            for (int r = n-1; r >= 0; r--) {
                if (board[r][c] != 0) temp[idx--] = board[r][c];
            }

            for (int i = 0; i < n; i++) {
                board[i][c] = temp[i];
            }
        }
    }

    static void rotate() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                copy[c][n-1-r] = board[r][c];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = copy[i][j];
            }
        }
    }

    static void show() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}