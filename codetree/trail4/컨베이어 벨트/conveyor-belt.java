import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[] arr = new int[n*2];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i*n + j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] res = new int[n*2];
        for (int i = 0; i < 2*n; i++) {
            res[(i + t) % (2*n)] = arr[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(res[i]).append(' ');
        }
        sb.append('\n');
        for (int i = 0; i < n; i++) {
            sb.append(res[i + n]).append(' ');
        }

        System.out.println(sb);
    }
}