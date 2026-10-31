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
        prefix = new int[n+1][m+1]; // prefix[i+1][j+1] : 0,0부터 i,j까지 직사각형의 칸 합
        prefix[1][1] = board[0][0];
        for (int i = 0; i < m; i++) {
            prefix[1][i+1] = prefix[1][i] + board[0][i];
        }
        for (int i = 0; i < n; i++) {
            prefix[i+1][1] = prefix[i][1] + board[i][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                prefix[i+1][j+1] = board[i][j] + prefix[i][j+1] + prefix[i+1][j] - prefix[i][j];
            }
        }

        int max = -30000;
        for (int k = 1; k < n; k++) {
            int a = -30000, b = -30000;
            for (int a1 = 0; a1 < k; a1++) {
                for (int a2 = 0; a2 < m; a2++) {
                    for (int a3 = a1; a3 < k; a3++) {
                        for (int a4 = a2; a4 < m; a4++) {
                            a = Math.max(a, getSum(a1, a2, a3, a4));
                        }
                    }
                }
            }
            for (int b1 = k; b1 < n; b1++) {
                for (int b2 = 0; b2 < m; b2++) {
                    for (int b3 = b1; b3 < n; b3++) {
                        for (int b4 = b2; b4 < m; b4++) {
                            b = Math.max(b, getSum(b1, b2, b3, b4));
                        }
                    }
                }
            }
            if (max < a + b) {
                max = a + b;
            }
        }

        for (int k = 1; k < n; k++) {
            int a = -30000, b = -30000;
            for (int a1 = 0; a1 < n; a1++) {
                for (int a2 = 0; a2 < k; a2++) {
                    for (int a3 = a1; a3 < n; a3++) {
                        for (int a4 = a2; a4 < k; a4++) {
                            a = Math.max(a, getSum(a1, a2, a3, a4));
                        }
                    }
                }
            }
            for (int b1 = 0; b1 < n; b1++) {
                for (int b2 = k; b2 < m; b2++) {
                    for (int b3 = b1; b3 < n; b3++) {
                        for (int b4 = b2; b4 < m; b4++) {
                            b = Math.max(b, getSum(b1, b2, b3, b4));
                        }
                    }
                }
            }
            if (max < a + b) {
                max = a + b;
            }
        }
        

        System.out.println(max);
    }

    static int n, m;
    static int[][] board;
    static int[][] prefix;

    static int getSum(int a1, int a2, int a3, int a4) {
        return prefix[a3+1][a4+1] - prefix[a3+1][a2] - prefix[a1][a4+1] + prefix[a1][a2];
    }
}