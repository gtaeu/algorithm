import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] dp = new int[n+1]; // dp[i] : n번에서 출발하여 i가 경로의 도착지일때, 가장 긴 경로의 길이
        // 경로의 시작점이 n이다를 어떻게 처리하지?
        // 지금 이런식으로하면 1로 도착하는 가장 긴 경로들 중 사전순 1등을 출력하는데
        // 그것의 출발점이 n이라는 보장이 없음
        // 출발지가 여러개인데, 그렇다고 출발지를 n으로만 하면 진입차수가 깨지고
        // 먼저 n에서 출발하여 도달가능한 번호수만으로 구성해야하나
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
            // dp[s] = 1;
            // dp[e] = 1;
            adj[e].add(s); // 역방향
            // indegree[s]++;
            // copy[s]++;
        }

        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        visited[n] = true;
        q.addLast(n);
        while (!q.isEmpty()) {
            int now = q.pollFirst();
            for (int next : adj[now]) {
                if (visited[next]) continue;
                dist[next] = dist[now] + 1;
                visited[next] = true;
                q.add(next);
            }
        }

        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        for (int i = 1; i <= n-1; i++) {
            if (dist[i] != 0) set.add(i);
        }

        for (int i = 1; i <= n; i++) {
            for (int next : adj[i]) {
                if (!set.contains(i) || !set.contains(next)) continue;
                indegree[next]++;
                copy[next]++;
                dp[i] = 1;
                dp[next] = 1;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) q.addLast(i);
        }

        while (!q.isEmpty()) {
            int now = q.pollFirst();

            for (int next : adj[now]) {
                indegree[next]--;

                if (dp[next] < dp[now] + 1) {
                    dp[next] = dp[now] + 1;
                    choose[next] = now;
                }
                else if (dp[next] == dp[now] + 1) {
                    choose[next] = Math.min(choose[next], now);
                }

                if (indegree[next] == 0) {
                    q.addLast(next);
                }
            }
        }

        if (dp[1] == 1) { // 1번에 도착하는 경로 자체가 없음
            System.out.println(-1);
            System.exit(0);
        }

        List<Integer> lst = new ArrayList<>();
        build(1, lst);
        
        StringBuilder sb = new StringBuilder();
        sb.append(dp[1]).append('\n');
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