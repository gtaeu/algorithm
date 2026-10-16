import java.util.*;
import java.io.*;

/*
원인: 기저에 "성공"과 "불가능"을 둘 다 INF로 씀 → 규칙: 기저값 = 상위 연산의 항등원
예시: 최대의 항등원은 0(만약 음수가 가능하면 Integer.MIN_VALUE), 더하기의 항등원은 0, 곱하기의 항등원은 1

함수정의를 이 좌표부터 시작할때 vs 이 좌표부터 고려할때의 기준
시작점이 자유로운 경로 문제는 "어디서든 시작 가능"이 곧 "모든 시작점의 최솟값"입니다.
배열처럼 순서가 있으면 "선택/넘어가기"로 단일 호출이 가능합니다.
격자나 그래프처럼 이동이 인접성으로만 정해지면 시작점 루프가 필요합니다. (가상의 시작 노드를 모든 칸에 연결하는 방법이 있지만, 결과는 같습니다.)

이 판단 기준만 알아 두면, 문제를 처음 볼 때 점화식을 "선택/넘어가기"로 세울지 "출발점에서 끝까지"로 세울지 바로 정할 수 있습니다.
*/

public class Main {
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        dp = new int[n][n][k+1];
        for (int[][] rows : dp) {
            for (int[] row : rows) Arrays.fill(row, -1);
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // System.out.println(Arrays.deepToString(board));

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = dfs(i, j, k);
                if (ans > temp) {
                    ans = temp;
                    // System.out.println(i + " " + j);
                }
            }
        }
        // System.out.println(dfs(0, 0, k));
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int n, k;
    static int[][] board;
    static int[][][] dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    // 현재좌표부터 고려할때로 짜도 어짜피 n^2으로 호출해야한다
    // 그래서 그냥 문제를 현재좌표부터 출발할때로 하자
    // dfs(r,c,cnt) 현재좌표부터 출발하고 k칸 갈 수 있을때, 얻을 수 있는 인접경로차들의 최대의 최소
    // dfs(r,c,cnt) = 최대(dfs(nr,nc,cnt-1), 현재좌표 nr,nc의 차이)들 중 최소
    static int dfs(int r, int c, int cnt) {
        if (cnt == 1) return 0;

        if (dp[r][c][cnt] != -1) return dp[r][c][cnt];

        int res = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

            if (board[nr][nc] > board[r][c]) {
                int temp = dfs(nr, nc, cnt - 1);
                int temp2 = board[nr][nc] - board[r][c];
                int max = Math.max(temp, temp2);
                res = Math.min(res, max);
            }
        }
        // System.out.println(res);

        return dp[r][c][cnt] = res;
    }
}
