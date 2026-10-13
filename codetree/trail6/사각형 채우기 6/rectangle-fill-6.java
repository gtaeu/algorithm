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


++ 추가적으로 상태로 요약은 되는데 상태공간이 너무 크면 상태를 다른걸로 바꿔보거나 아예 잘못 잡은것이다
ex) 시간, 경험치 문제 (0/1 냅색)
[인덱스][경험치] = 시간 으로 잡으니 경험치가 상한이 1억이다 -> 불가
역으로 [인덱스][시간]을 써서 최대 경험치를 구하는 식으로 가능

+++ 추가로 문제에서 얻을 수 있는 dp 힌트는 방향성의 제한이다
일반적으로 dp에서 visited는 상태로 두지 않는다. 그러면 상태전이가 양방향으로 일어나면? 그걸 제한할 수 없다
즉 문제에서 방향성을 제한한다 <- 우리는 이걸 힌트로 쓸 수 있다!
- 일반적으로 idx를 상태로 둘 수 있다면 idx+ 방향으로 방향성을 제한할 수 있다
- 예를 들어 격자에서 dp면(grid dp) 갈 수 있는 방향을 제한을 두거나(오른쪽이나 아래쪽으로만 이동) or 칸의 값이 오름차순으로만 등반할 수 있게 제한한다
- 위상정렬을 dp문제로 쓸 수 있는 이유가 여러 정점과 간선이 있지만 위상정렬로 방향성이 정해지니까 쓸 수 있다.
방향성을 제한하는 이유는 방향성을 제한 안 한다면 우리는 비트마스크를 써서 상태에 넣어야하고, 비트마스크의 경우 20만 되도 2의20승 (약 백만)이다
문제의 메모리가 64MB로 제한이면 쓸 수 있는 상태공간은 64 * 천 * 천 / 4 = 16 * 백만 = 약 2천만이다 (64MB = int 변수 2천만개)
즉 n이 20만 되도 백만인데, 여기에 다른 상태의 범위가 20만 되도 2천만이고, 메모리 초과가 난다
그럼 문제에서 방향성을 제시한다? -> dp의심
방향성 제시는 없지만 어떤 최소나 최대를 구하는거고, n이 20이하다? -> 비트마스크 dp(tsp, 비트마스크 bfs, 조합원들의 능력치 합 등등) 의심
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

    // fill(idx, h, col, next) : 현재높이 h이고, 상태가 col일때, col을 전부 1로 채우면 나타나는 다음 idx 상태들
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
