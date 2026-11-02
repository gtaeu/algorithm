import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            int d = dir.equals("L") ? 0 : 1; // 왼쪽에서 밀면 0
            push(num-1, d);
            backtrack(num-1 - 1, 1 - d, -1);
            backtrack(num-1 + 1, 1 - d, 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(board[i][j]).append(' ');
            }
            if (i != n-1) sb.append('\n');
        }

        System.out.println(sb);
    }

    static int n, m, q;
    static int[][] board;


    // backtrack(r, lr, ud) : r행을 lr방향에 맞게 밀고, ud로 전파
    static void backtrack(int r, int lr, int ud) {
        if (r < 0 || r >= n) return;

        if (!check(r, ud)) return;

        push(r, lr);
        backtrack(r+ud, 1 - lr, ud);
    }

    static void push(int r, int lr) {
        if (lr == 0) { // 오른쪽으로 밀기
            int temp = board[r][m-1];
            for (int i = m-1; i >= 1; i--) {
                board[r][i] = board[r][i-1];
            }
            board[r][0] = temp;
        }
        else {
            int temp = board[r][0];
            for (int i = 0; i < m-1; i++) {
                board[r][i] = board[r][i+1];
            }
            board[r][m-1] = temp;
        }
    }

    static boolean check(int r, int ud) {
        for (int i = 0; i < m; i++) {
            if (board[r][i] == board[r-ud][i]) return true;
        }
        return false;
    }
}