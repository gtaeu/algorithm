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
        for (int i = 0; i < n; i++) {
            ans += check(i, 0) ? 1 : 0;
            ans += check(i, 1) ? 1 : 0;
        }

        System.out.println(ans);
    }

    static int n, m;
    static int[][] board;

    static boolean check(int idx, int d) {
        if (m == 1) return true;
        
        if (d == 0) { // 오른쪽방향
            int cnt = 1;
            int now = 0;

            while (now != n-1) {
                if (board[idx][now+1] == board[idx][now]) {
                    cnt++;
                    if (cnt == m) return true;
                }
                else {
                    cnt = 1;
                }
                now++;
            }
        }
        if (d == 1) { // 아래쪽방향
            int cnt = 1;
            int now = 0;

            while (now != n-1) {
                if (board[now+1][idx] == board[now][idx]) {
                    cnt++;
                    if (cnt == m) return true;
                }
                else {
                    cnt = 1;
                }
                now++;
            }
        }
        return false;
    }
}