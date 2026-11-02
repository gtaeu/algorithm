import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[] arr = new int[n*3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i*n + j] = Integer.parseInt(st.nextToken());
            }
        }

        int len = n * 3;
        int[] res = new int[len];

        for (int i = 0; i < len; i++) {
            res[(i + t) % len] = arr[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(res[i*n + j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}