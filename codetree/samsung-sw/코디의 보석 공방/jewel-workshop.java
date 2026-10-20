/*
어렵지는 않은데
대체 시간이 어디서 초과되는지
또, 삼성의 이런 처음보는 유형(작업이 여러 종류이고, 그 작업은 최대 몇번 호출되는지)
즉 시그마(작업당시간 * 최대호출수)를 다 합해서 시간을 계산해야한다
솔직히 불가능하고, 귀찮다
각 작업마다 제일 효율적인 알고리즘을 구하는 것을 기본으로 두고,

1. 어떤 물건을 입고하거나, 없애거나 등등 기존 배열에 변화가 생기면
dirty 변수를 둬서 dirty가 false 일때는 기존 작업을 캐시히트해주고,
dirty가 true면 다시 작업을 한다

2. 입고되거나 팔려서 ArrayList두고 sort하는 것 보다
차라리 처음부터 int[] ws = new int[1100];으로 가장 크게 두고
if (it[0] == 1) ws[m++] = it[1]; 이런식으로 하는게 유의미하게 빠르다
객체보다 기본형이라서 속도가 빠르다

3. 다행인점은 탑다운 dp를 버려도 되지 않는다는 것, 여태까지 다 탑다운으로 풀었는데 익숙하게 쓸 수 있다.
그래도 삽질하면서 바텀업 풀이도 처음으로 익혀보았다. 핵심은 전이함수는 같다.
단지 탑다운이 idx가 idx+1을 호출하는 식(idx 증가)면, 바텀업은 for문의 idx를 감소하는 방향으로 작성해야 한다는 것이다.
*/


import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String next() {
        if (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }

    public static void main(String[] args) {
        int q = nextInt();
        nextInt();
        info = new ArrayList<>();
        boolean dirty = true;
        boolean sortDirty = true;
        n = nextInt();
        dp = new int[1100][3001];
        int[] ws = new int[1100];
        int m = 0;
        q--;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int w = nextInt();
            int v = nextInt();
            info.add(new int[]{1, w, v}); // 1이면 사용가능, 0이면 불가, 0-index
        }

        while (q --> 0) {
            int task = nextInt();
            if (task == 2) {
                info.add(new int[]{1, nextInt(), nextInt()});
                n++;
                dirty = true;
                sortDirty = true;
            }
            else if (task == 3) {
                int idx = nextInt() - 1;
                if (idx >= info.size() || info.get(idx)[0] == 0) {
                    sb.append(-1).append('\n');
                    continue;
                }
                sb.append(info.get(idx)[2]).append('\n');
                info.get(idx)[0] = 0;
                dirty = true;
                sortDirty = true;
            }
            else if (task == 4) {
                int w = nextInt();

                if (dirty) {
                    for (int[] row : dp) Arrays.fill(row, -1);
                    dirty = false;
                }
                
                sb.append(dfs(0,w)).append('\n');
            }
            else if (task == 5) {
                int d = nextInt();
                if (sortDirty) {
                    m = 0;
                    for (int i = 0; i < n; i++) {
                        if (info.get(i)[0] == 1) {
                            ws[m++] = info.get(i)[1];
                        }
                    }
                    Arrays.sort(ws, 0, m);
                    sortDirty = false;
                }
                // System.out.println(lst);
                int r = 0;
                int cnt = 0;
                for (int l = 0; l < m; l++) {
                    while (r != m && ws[r] - ws[l] <= d) {
                        r++;
                    }

                    if (r - l >= 2) {
                        cnt += r - l - 1;
                    }
                }

                sb.append(cnt).append('\n');
            }
        }
        System.out.println(sb);
    }

    static ArrayList<int[]> info;
    static int n;
    static int[][] dp;

    // dfs(idx, w): idx부터 고려, 남은 무게한계가 w일때, 최대 가치
    // (idx, w) -> (idx+1, w - ~) / (idx+1, w)
    // 천 * 3천 * 2 = 6백만 * 30(겉호출) = 1억8천
    static int dfs(int idx, int w) {
        if (idx == n) {
            if (w >= 0) return 0;
            else return Integer.MIN_VALUE;
        }

        if (info.get(idx)[0] == 0) return dfs(idx+1, w);

        if (w < 0) return Integer.MIN_VALUE;

        if (dp[idx][w] != -1) return dp[idx][w];

        int res = 0;

        res = Math.max(res, info.get(idx)[2] + dfs(idx+1, w - info.get(idx)[1]));
        res = Math.max(res, dfs(idx+1, w));

        return dp[idx][w] = res;
    }

}
