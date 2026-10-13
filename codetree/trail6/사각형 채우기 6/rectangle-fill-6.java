import java.util.*;
import java.io.*;

/*
깨달은것
논리적 비약이 있지만 백트래킹 dp 구분은 어떤 값 하나를 구하면 dp, 어떤 특정모양을 구하면 백트래킹으로 의심할 수 있다
이 의심을 가지고 시간복잡도를 계산해보면 
dp는 상태수 * 전이수로 계산할수있고
백트래킹은 전체 재귀호출수로 계산할 수 있다
이 문제의 경우 
fill의 시간복잡도는 어떤 특정 모양(나타날 수 있는 모든 상태)를 구하므로 백트래킹이고, 넉넉잡아 2^높이 만큼 걸린다
dfs의 경우 dp이므로 길이 * 2^높이 * 1(전이수)이다
시간 복잡도 상한은 길이 * 2^높이 * 2^높이 = 11 * 2^11 * 2^11 = 약 천만이다

백트래킹 dp를 더 구분하려면
어떤 문제를 작은 문제에 대해 재귀적으로 적용할 수 있게 상태를 구한다(db의 pk값을 구하는 느낌, 상태: 문제를 식별할 수 있는 수들의 조합)
즉 문제 정의 = 상태
현재 메서드에서 직전 메서드가 영향을 안주거나, 주는데 그 영향을 어떤 값으로 간단히 표현할 수 있으면 dp
그 값을 간단한 값으로 표현 못하면 백트래킹이다
예를 들어, 계단오르기 문제는 idx를 상태로 정의할 수 있고, 직전 메서드가 영향을 안 준다 -> dp
n퀸의 경우, 같은 열에 못 둔다는 정보를 배열에 나타내는데 이걸 상태에 반영을 못한다(배열이니까) 굳이굳이 한다면 비트마스크로 표현할 수 있지만 배열로 표현한다는 가정하에
즉, 직전 메서드가 영향을 주고, 그 영향을 상태에 간단히 표현을 못하니 백트래킹이다.

사실,
계단오르기 -> 어떤 값을 구한다
n퀸 -> 퀸들의 배치를 구한다
아까 쓴 논리적 비약 문장 이용해서 바로 구분할 수 있긴하다
*/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[m][1 << n];
        for (int[] row : dp) Arrays.fill(row, -1);

        System.out.println(dfs(0, 0));
    }

    static int n, m;
    static int[][] dp;

    static int dfs(int idx, int mask) {
        if (idx == m) {
            return mask == 0 ? 1 : 0;
        }

        if (dp[idx][mask] != -1) return dp[idx][mask];

        return dp[idx][mask] = fill(idx, n, mask, 0);
    }

    // fill(idx, h, col, now) : 현재높이 h이고, 상태가 col일때, col을 전부 1로 채우면 나타나는 다음 idx 상태들
    static int fill(int idx, int h, int col, int next) {
        if (h == 0) {
            if (col == (1 << n) - 1) {
                return dfs(idx+1, next);
            }
            return 0;
        }

        int res = 0;
        if (((col >> h-1) & 1) == 1) {
            res += fill(idx, h-1, col, next);
        }
        else {
            if (idx + 2 <= m) res += fill(idx, h-1, col | (1 << h-1), next | (1 << h-1)); // 가로칸
            if ((h-2 >= 0) && (((col >> h-2) & 1) == 0)) { // 세로칸
                int newCol = col | 0b11 << h-2;
                res += fill(idx, h-2, newCol, next);
            }
        }

        return res % 10007;
    }
}
