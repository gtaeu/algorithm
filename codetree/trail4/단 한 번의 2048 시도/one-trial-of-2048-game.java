import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new HashMap<>();
        map.put("U", new int[]{-1, 0});
        map.put("D", new int[]{1, 0});
        map.put("L", new int[]{0, -1});
        map.put("R", new int[]{1, 1});

        board = new int[4][4];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        String dir = br.readLine();

        push(dir);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(board[i][j]);
                if (j != 3) sb.append(' ');
            }
            if (i != 3) sb.append('\n');
        
        }

        System.out.println(sb);
    }

    static int[][] board;
    static HashMap<String, int[]> map;



    static void push(String dir) {
        int[] dd = map.get(dir);
        int dr = dd[0];
        int dc = dd[1];

        if (dir.equals("R")) {
            for (int i = 0; i < 4; i++) {
                int[] temp = new int[4];
                int idx = 3;
                int flag = 0;
                for (int j = 3; j >= 0; j--) {
                    if (board[i][j] == 0) continue;
                    else {
                        if (flag != 1 && idx != 3 && temp[idx+1] == board[i][j]) {
                            temp[idx+1] *= 2;
                            flag = 1;
                        }
                        else {
                            temp[idx--] = board[i][j];
                            flag = 0;
                        }
                    }
                    
                }
                board[i] = temp;
            }
        }
        else if (dir.equals("L")) {
            for (int i = 0; i < 4; i++) {
                int[] temp = new int[4];
                int idx = 0;
                int flag = 0;
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] == 0) continue;
                    else {
                        if (flag != 1 && idx != 0 && temp[idx-1] == board[i][j]) {
                            temp[idx-1] *= 2;
                            flag = 1;
                        }
                        else {
                            temp[idx++] = board[i][j];
                            flag = 0;
                        }
                    }
                    
                }
                board[i] = temp;
            }
        }
        else if (dir.equals("U")) {
            for (int i = 0; i < 4; i++) {
                int[] temp = new int[4];
                int idx = 0;
                int flag = 0;
                for (int j = 0; j < 4; j++) {
                    if (board[j][i] == 0) continue;
                    else {
                        if (flag != 1 && idx != 0 && temp[idx-1] == board[j][i]) {
                            temp[idx-1] *= 2;
                            flag = 1;
                        }
                        else {
                            temp[idx++] = board[j][i];
                            flag = 0;
                        }
                    }
                    
                }
                for (int j = 0; j < 4; j++) {
                    board[j][i] = temp[j];
                }
            }
        }
        else {
            for (int i = 0; i < 4; i++) {
                int[] temp = new int[4];
                int idx = 3;
                int flag = 0;
                for (int j = 3; j >= 0; j--) {
                    if (board[j][i] == 0) continue;
                    else {
                        if (flag != 1 && idx != 3 && temp[idx+1] == board[j][i]) {
                            temp[idx+1] *= 2;
                            flag = 1;
                        }
                        else {
                            temp[idx--] = board[j][i];
                            flag = 0;
                        }
                    }
                    
                }
                for (int j = 0; j < 4; j++) {
                    board[j][i] = temp[j];
                }
            }
        }
    }
}
