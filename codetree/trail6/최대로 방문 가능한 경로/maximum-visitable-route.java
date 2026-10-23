import java.util.*;
import java.io.*;

public class Main {
    static final int inf = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        ArrayList<Integer>[] revAdj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) revAdj[i] = new ArrayList<>();
        int[] indegree = new int[n+1];
        int[] dp = new int[n+1];
        int[] bef = new int[n+1]; // bef[i] : (n에서 출발하여) i를 도착할 때, i직전의 노드 번호
        Arrays.fill(dp, -inf);
        Arrays.fill(bef, inf);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[b].add(a);
            revAdj[a].add(b);
            indegree[a]++;
        }

        // Arrays.stream(adj).forEach(i -> System.out.println(i));

        dp[n] = 0; //dp[i] : (n에서 출발하여) i를 도착지로 했을때의 거리

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) q.addLast(i);
        }

        while (!q.isEmpty()) {
            int now = q.pollFirst();

            for (int next : adj[now]) {
                indegree[next]--;

                if (dp[now] != -inf) {
                    if (dp[next] < dp[now] + 1) {
                        dp[next] = dp[now] + 1;
                        bef[next] = now;
                    }
                    else if (dp[next] == dp[now] + 1) {
                        if (bef[next] > now) {
                            bef[next] = now;
                        }
                    }
                }

                if (indegree[next] == 0) {
                    q.addLast(next);
                }
            }
        }

        // System.out.println(Arrays.toString(dp));
        if (dp[1] == -inf) {
            System.out.println(-1);
            System.exit(0);
        }

        // List<Integer> lst = new ArrayList<>();
        // int now = 1;
        // while (now != n) {
        //     lst.add(now);
        //     now = bef[now];
        // }
        // lst.add(n);

        Arrays.stream(revAdj).filter(i -> i != null).forEach(i -> i.sort((o1, o2) -> Integer.compare(o1, o2)));
        
        StringBuilder sb = new StringBuilder();
        sb.append(dp[1] + 1).append('\n');

        int now = 1;
        while (now != n) {
            sb.append(now).append(' ');
            for (int next : revAdj[now]) {
                if (dp[next] + 1 == dp[now]) {
                    now = next;
                    break;
                }
            }
        }
        sb.append(n);

        // StringBuilder sb = new StringBuilder();
        // sb.append(dp[1] + 1).append('\n');
        // for (int a : lst) sb.append(a).append(' ');

        System.out.println(sb);
    }
}