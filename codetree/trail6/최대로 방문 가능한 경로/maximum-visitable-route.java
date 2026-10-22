import java.util.*;
import java.io.*;

public class Main {
    static final int inf = -1 * Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] dp = new int[n+1]; // dp[i] : i가 경로의 도착지일때, 가장 긴 경로의 길이
        Arrays.fill(dp, inf);
        choose = new int[n+1]; // choose[i] : dp[i]의 이후 선택지
        Arrays.fill(choose, n+1);

        int[] indegree = new int[n+1];
        copy = new int[n+1];
        ArrayList<Integer>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adj[e].add(s);
            indegree[s]++;
            copy[s]++;
        }

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) q.addLast(i);
        }
        dp[n] = 0;

        while (!q.isEmpty()) {
            int now = q.pollFirst();

            for (int next : adj[now]) {
                indegree[next]--;

                if (dp[now] != inf) {
                    if (dp[next] < dp[now] + 1) {
                        dp[next] = dp[now] + 1;
                        choose[next] = now;
                    }
                    else if (dp[next] == dp[now] + 1) {
                        choose[next] = Math.min(choose[next], now);
                    }
                }

                if (indegree[next] == 0) {
                    q.addLast(next);
                }
            }
        }

        if (dp[1] == inf) {
            System.out.println(-1);
            System.exit(0);
        }
        
        List<Integer> lst = new ArrayList<>();
        build(1, lst);

        StringBuilder sb = new StringBuilder();
        sb.append(dp[1]+1).append('\n');
        for (int a : lst) sb.append(a).append(' ');
        
        System.out.println(sb);
    }

    static int[] copy;
    static int[] choose;

    static void build(int now, List<Integer> lst) {
        if (copy[now] == 0) {
            lst.add(now);
            return;
        }

        lst.add(now);
        build(choose[now], lst);
    }
}
