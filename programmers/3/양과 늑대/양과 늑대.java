import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        n = info.length + 1; // 양 + 늑대 수
        Info = info;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        
        dp = new int[1 << n];
        Arrays.fill(dp, -1);
        
        return dfs(1, 1, 0);
    }
    
    static ArrayList<Integer>[] adj;
    static int[] dp;
    static int n;
    static int[] Info;
    
    // dfs(mask, sheep, wolf) : 선택된 번호가 mask이고, 양의 개수가 sheep이고, 늑대 개수가 wolf일 때, 얻을 수 있는 최대 양의 개수
    // (mask, sheep, wolf) -> (newMask, newSheep, newWolf)
    // 2의 18승 * 최대4? = 백만정도
    static int dfs(int mask, int sheep, int wolf) {
        if (sheep <= wolf) {
            return Integer.MIN_VALUE;   
        }
        
        if (mask == 1 << n - 1) return 0;
        
        if (dp[mask] != -1) return dp[mask];
        
        int res = sheep;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) { // i 비트 켜짐
                for (int next : adj[i]) {
                    int newMask = mask | (1 << next);
                    if (mask == newMask) continue;
                    if (Info[next] == 0) {
                        res = Math.max(res, dfs(newMask, sheep+1, wolf));
                    }
                    else {
                        res = Math.max(res, dfs(newMask, sheep, wolf+1));
                    }
                }
            }
        }
        
        return dp[mask] = res;
    }
}