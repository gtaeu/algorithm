import java.util.*;
import java.io.*;

/*
종료조건의 기저조건
이 상태에서 집계할 항목 자체가 없다: 항등원 반환 - 편안한 워크숍
이 상태에서 상태가 딱 하나 남았다: 그 값자체를 리턴 - 이 문제
예제:
일단 이 말을 구분하자. 남은 항목이 없다 = 성공 / 남은 항목이 있지만, 더 이상 갈 수 있는 후보가 없다 = 실패 / 남은 항목이 딱 1개고, 그걸 이룰 후보가 있다 = 값자체
1. 합의최대를 찾아야 한다. 나는 기저조건에 왔고 남은 항목이 없다. 이때 리턴은? 항등원(0) - 합을 구하기 위해서 호출된 상태임, -무한대라고하면 틀림, 7번과 구분 중요
2. 합의최대를 찾아야 한다. 나는 기저조건에 왔고 남은 항목이 하나 있다. 이때 리턴은? 값 자체(board[r][c]) 등등..
3. 전체 경우의수를 찾아야 한다. 나는 기저조건에 왔고 남은 항목이 없다. 이때 리턴은? 1(값자체, 경우의 수 1개)
4. 전체 경우의수를 찾아야 한다. 나는 기저조건에 왔고 더 이상 갈 수 있는 후보가 없다. 이때 리턴은? 0(경우의 수에대한 항등원)
5. 인접한 칸의 차이의 최대의 최소를 찾아야 한다. 나는 기저조건에 왔고, 남은 항목이 없다. 이때 리턴은? 날 호출한 곳은 최댓값을 구하는 로직부분. 즉 항등원(0)
6. 인접한 칸의 차이의 최대의 최소를 구해야 한다. 나는 이 함수의 리턴값 변수에 초기값을 할당하려한다. 이때 초기값은? 양의무한대
7. 합의 최대를 찾아야 한다. 이 함수의 리턴값 변수에 초깃값을 넣으려 한다. 이떄 초기값은? -무한대(대신 음수가없고, 어떻게든 0이상이 되면 0도 되긴한다) (1번과 구분, 1번은 합을 구하기 위해 호출한 상태이다)
*/

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        memo = new int[n][n][t+1];
        dp = new int[n][n][2];
        for (int[][] rows : memo) {
            for (int[] row : rows) Arrays.fill(row, -1);
        }
        for (int[][] rows : dp) {
            for (int[] row : rows) Arrays.fill(row, -1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memoi(i, j, t);
            }
        }

        int ans = dfs(0, 0, 0);
        System.out.println(ans);
    }

    static int n, t;
    static int[][] board;
    static int[][][] memo;
    static int[][][] dp;
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};


    // memoi(r,c,t) : 현재좌표에서 출발하여, t초동안 얻을 수 있는 최대 이득
    // memoi(r,c,t) -> memoi(nr,nc,t-1) 
    // 2의5승 * 백만 = 3천만
    static int memoi(int r, int c, int t) {
        if (t == 0) return board[r][c];

        if (memo[r][c][t] != -1) return memo[r][c][t];

        int res = -5000;

        for (int i = 0; i < 2; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

            res = Math.max(res, board[r][c] + memoi(nr, nc, t-1));
        }

        return memo[r][c][t] = res;
    }

    // dfs(r,c,use) : 현재좌표에서 출발하고, 타임머신 사용 여부가 use일 때, 끝까지 갈 때 얻을 수 있는 최대 이득
    // (r,c,use) -> (nr,nc,use) or (nr,nc,newUse) 최대 총 4가지
    // 백만 * 4 = 2백만
    static int dfs(int r, int c, int use) {
        if (r == n-1 && c == n-1) {
            return board[r][c];
        }

        if (dp[r][c][use] != -1) return dp[r][c][use];

        int res = -2_000_000;
        for (int i = 0; i < 2; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

            // 안쓰기
            res = Math.max(res, board[r][c] + dfs(nr, nc, use)); 
            
            // 안썼으면, 쓰기
            if (use == 0) res = Math.max(res, board[r][c] + memo[r][c][t] + dfs(nr, nc, 1));
        }

        return dp[r][c][use] = res;
    }
}

