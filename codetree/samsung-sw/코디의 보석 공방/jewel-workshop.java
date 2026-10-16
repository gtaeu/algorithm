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
        cnt = new int[3001];
        memo = new int[1100][3001];

        for (int i = 0; i < n; i++) {
            int w = nextInt();
            int v = nextInt();
            info.add(new int[]{1, w, v}); // 0인덱스
            
        }
        for (int r = 0; r < info.size() - 1; r++) {
            for (int c = r+1; c < info.size(); c++) {
                int abs = Math.abs(info.get(r)[1] - info.get(c)[1]);
                cnt[abs]++;
            }
        }
        for (int i = 0; i < q-1; i++) {
            int num = nextInt();
            if (num == 2) {
                int w = nextInt();
                int v = nextInt();
                for (int j = 0; j < info.size(); j++) {
                    if (info.get(j)[0] == 0) continue;
                    int abs = Math.abs(w - info.get(j)[1]);
                    cnt[abs]++;
                }
                info.add(new int[]{1, w, v});
                n++;
                
            }
            else if (num == 3) {
                int idx = nextInt() - 1;

                
                if (idx > info.size() - 1) {
                    System.out.println(-1);
                    continue;
                }
 
                if (info.get(idx)[0] != 0) {
                    for (int j = 0; j < info.size(); j++) {
                        if (info.get(j)[0] == 0) continue;
                        if (j == idx) continue;
                        int abs = Math.abs(info.get(idx)[1] - info.get(j)[1]);
                        cnt[abs]--;
                    }
                }

                if (info.get(idx)[0] == 0) System.out.println(-1);
                else {
                    System.out.println(info.get(idx)[2]);
                    info.get(idx)[0] = 0;
                }
            }
            else if (num == 4) {
                int w = nextInt();
                for (int[] row : memo) Arrays.fill(row, -1);
                int ans = show(0, w);
                System.out.println(ans == Integer.MIN_VALUE ? 0 : ans);
            }
            else {
                d = nextInt();
                int ans = 0;
                for (int k = 0; k <= d; k++) {
                    ans += cnt[k];
                }
                System.out.println(ans);
            }
        }
    }

    static int q, n, d;
    static ArrayList<int[]> info;
    static int[][] memo;
    static int[] cnt;


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

    // dfs(idx, cnt) : idx부터 고려했을 때, 고를 수 있는 보석 개수가 cnt개 일 때, 무게차이가 D이하인 세트의 개수
    // (idx, cnt) -> (idx+1, cnt-1) / (idx+1, cnt)
    // 1000 * 2 = 2천 근데 캐싱이 된다는 가정하에 입력이 여러번이어도 2천인데, 팔릴수도있고, 입고될수도있는데
    // 만약 세트구성할때마다 새로운 상태면 2의 n승인데.. 몰론 이것보다는 작겠지만 최악의경우
    // 고려로 어떻게 풀어야할지 모르겠다

    // dfs(idx, cnt) : idx부터 고려했을 때, 고를 수 있는 보석 개수가 cnt일 때, 무게차이가 D이하인 세트의 개수
    // (idx, 2) -> (idx+1, 1)

    // dfs(idx, bef) : 인덱스부터 고려할때 bef와 idx의 무게차이가 d이하인 경우의 수
    // (idx, -1) -> (idx+1, bef)

    // dfs(idx, w) : idx를 포함할때 idx이후의 수들 중 차이가 w이하인 것들의 경우의 수
    // 1000 * 3000 = 3백만 겉 호출이 n^2이지만 dp를 반복마다 초기화하지 않기에 3백만임
    // static int dfs(int idx, int w) {
    //     if (dp[idx][w] != -1) return dp[idx][w];

    //     int res = 0;

    //     for (int i = idx+1; i < info.size(); i++) {
    //         int[] arr = info.get(i);
    //         if (arr[0] == 0) continue;

    //         if (Math.abs(info.get(idx)[1] - arr[1]) <= w) res++;
    //     }

    //     return dp[idx][w] = res;
    // }
}
