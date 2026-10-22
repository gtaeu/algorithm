class Solution {
    static final int inf = Integer.MAX_VALUE / 2;

    public String shortestSuperstring(String[] words) {
        Words = words;
        n = words.length;
        dp = new int[1<<n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        choose = new int[1<<n][n];
        lookup = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (!words[i].endsWith(words[j])) {
                    lookup[i][j] = words[i].length();
                }
                for (int k = 1; k < words[i].length(); k++) {
                    if (words[j].startsWith(words[i].substring(k, words[i].length()))) {
                        lookup[i][j] = k;
                        break;
                    }
                }
            }
        }

        int ans = inf;
        int start = -1;
        for (int i = 0; i < n; i++) {
            // ans = Math.min(ans, dfs(1 << i, i));
            int temp = dfs(1 << i, i);
            if (ans > temp) {
                ans = temp;
                start = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        build(1 << start, start, sb);

        return sb.toString();
    }

    static int n;
    static int[][] dp;
    static String[] Words;
    static int[][] lookup;
    static int[][] choose;
    
    // dfs(mask, idx) : 현재mask이고, idx위치일때, 가장 짧은 superstring의 길이
    // -> (newMask, newIdx)
    // 2^12 * 12^2 = 4천 * 백 = 40만 * 12 = 400만
    static int dfs(int mask, int idx) {
        if (mask == (1 << n) - 1) {
            return Words[idx].length();
        }

        if (dp[mask][idx] != -1) return dp[mask][idx];

        int res = inf;

        for (int i = 0; i < n; i++) {
            int newMask = mask | 1 << i;
            if (mask == newMask) continue;

            int temp = lookup[idx][i] + dfs(newMask, i);
            if (res > temp) {
                res = temp;
                choose[mask][idx] = i;
            }
        }

        return dp[mask][idx] = res;
    }

    static void build(int mask, int idx, StringBuilder sb) {
        if (mask == (1 << n) - 1) {
            sb.append(Words[idx]);
            return;
        }

        int next = choose[mask][idx];
        sb.append(Words[idx].substring(0, lookup[idx][next]));
        build(mask | 1 << next, next, sb);
    }
}