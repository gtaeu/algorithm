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

    public static void main(String[] args) throws IOException {

        q = nextInt();
        nextInt();
        n = nextInt();
        info = new ArrayList<>(); // info.get(i) = {사용가능(1), 보석무게, 보석가치}
        for (int i = 0; i < n; i++) {
            int w = nextInt();
            int v = nextInt();
            info.add(new int[]{1, w, v}); // 0인덱스
        }
        for (int i = 0; i < q-1; i++) {
            int num = nextInt();
            if (num == 2) {
                int w = nextInt();
                int v = nextInt();
                info.add(new int[]{1, w, v});
                n++;
            }
            else if (num == 3) {
                int idx = nextInt() - 1;
                if (idx > info.size() - 1) {
                    System.out.println(-1);
                    continue;
                }

                if (info.get(idx)[0] == 0) System.out.println(-1);
                else {
                    System.out.println(info.get(idx)[2]);
                    info.get(idx)[0] = 0;
                }
            }
            else if (num == 4) {
                int w = nextInt();
                memo = new int[n][w+1];
                for (int[] row : memo) Arrays.fill(row, -1);
                int ans = show(0, w);
                System.out.println(ans == Integer.MIN_VALUE ? 0 : ans);
            }
            else {
                d = nextInt();
                int ans = dfs(0, new ArrayList<>());
                System.out.println(ans);
            }
        }
    }

    static int q, n, d;
    static ArrayList<int[]> info;
    static int[][] memo;

    // show(idx,w): idx부터 고려하고, 채울수있는 무게 한계가 w일때, 고른 보석들의 가치합 최대
    // (idx,w) -> (idx+1, w - ~) / (idx, w) 사용가능 상태인지 따져야함
    // 3백만
    static int show(int idx, int w) {
        if (idx == n) {
            if (w >= 0) return 0;
            else return Integer.MIN_VALUE;
        }

        if (w < 0) return Integer.MIN_VALUE;

        if (memo[idx][w] != -1) return memo[idx][w];



        int res = Integer.MIN_VALUE;

        int[] arr = info.get(idx);
        if (arr[0] == 0) {
            res = Math.max(res, show(idx+1, w));
        }
        else {
            res = Math.max(res, show(idx+1, w));
            res = Math.max(res, arr[2] + show(idx+1, w - arr[1]));
        }

        return memo[idx][w] = res;
    }

    // dfs(idx, cnt) : idx부터 고려했을 때, 고를 수 있는 보석 개수가 2개 일 때, 무게차이가 D이하인 세트의 개수
    // 1000C2
    static int dfs(int idx, List<Integer> lst) {
        if (lst.size() == 2) {
            if (Math.abs(lst.get(0) - lst.get(1)) <= d) {
                return 1;
            }
            else return 0;
        }

        int res = 0;

        for (int i = idx; i < info.size(); i++) {
            int[] arr = info.get(i);
            if (arr[0] == 0) continue;

            lst.add(arr[1]);
            res += dfs(i+1, lst);
            lst.remove(lst.size() - 1);
        }

        return res;
    }
}