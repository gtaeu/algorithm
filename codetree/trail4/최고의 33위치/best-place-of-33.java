import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n-2; i++) {
            for (int j = 0; j < n-2; j++) {
                int temp = 0;
                for (int a = i; a < i+3; a++) {
                    for (int b = j; b < j+3; b++) {
                        temp += board[a][b];
                    }
                }
                ans = Math.max(ans, temp);
            }
        }

        System.out.println(ans);
    }
}