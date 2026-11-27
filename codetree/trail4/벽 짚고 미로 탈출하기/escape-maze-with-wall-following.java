import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        r--; c--;

        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        int t = 0;
        boolean tof = true;
        int d = 0;
        int cnt = 0;
        while (!(r < 0 || c < 0 || r >= n || c >= n)) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!inRange(nr, nc)) {
                t++;
                break;
            }

            if (board[nr][nc] == '!') {
                tof = false;
                break;
            }

            if (board[nr][nc] == '.') {
                int nnr = nr + dr[(d+1)%4];
                int nnc = nc + dc[(d+1)%4];
                cnt = 0;
                if (board[nnr][nnc] == '#') {
                    t++;
                    r = nr;
                    c = nc;
                    board[r][c] = '!';
                }
                else {
                    board[nr][nc] = '!';
                    board[nnr][nnc] = '!';
                    r = nnr;
                    c = nnc;
                    t += 2;
                    d = (d+1) % 4;
                }
            }
            else {
                d = (d-1 + 4) % 4;
                cnt++;
                if (cnt == 4) {
                    tof = false;
                    break;
                }
            }
        }

        if (tof) System.out.println(t);
        else System.out.println(-1);
    }

    static int n, r, c;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static boolean inRange(int r, int c) {
        return !(r < 0 || c < 0 || r >= n || c >= n);
    }
}