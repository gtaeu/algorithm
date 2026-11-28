import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        r--; c--;

        st = new StringTokenizer(br.readLine());
        task = new char[m];
        board = new int[n][n];
        for (int i = 0; i < m; i++) {
            task[i] = st.nextToken().charAt(0);
        }

        HashMap<String, Integer> map = new HashMap<>();
        map.put("U", 0);
        map.put("D", 1);
        map.put("L", 2);
        map.put("R", 3);

        top = 1;
        front = 2;
        right = 3;

        
        board[r][c] = 6;
        for (char ch : task) {
            int d = map.get(ch + "");
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

            roll(d);

            board[nr][nc] = 7 - top;
            
            r = nr;
            c = nc;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += board[i][j];
            }
        }

        System.out.println(ans);

    }

    static int n, m, r, c;
    static int top, front, right;
    static char[] task;
    static int[][] board;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void roll(int d) {
        if (d == 0) {
            int temp = top;
            top = front;
            front = 7 - temp;
        }
        else if (d == 1) {
            int temp = front;
            front = top;
            top = 7 - temp;
        }
        else if (d == 2) {
            int temp = top;
            top = right;
            right = 7 - temp;
        }
        else {
            int temp = right;
            right = top;
            top = 7 - temp;
        }
    }
}