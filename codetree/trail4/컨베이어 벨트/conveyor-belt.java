import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[] arr = new int[n * 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[n + i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < t; i++) {
            int temp = arr[n*2 - 1];
            for (int j = 2*n-1; j >= 1; j--) {
                arr[j] = arr[j-1];
            }
            arr[0] = temp;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append(' ');
        }
        sb.append('\n');
        for (int i = 0; i < n; i++) {
            sb.append(arr[n + i]).append(' ');
        }

        System.out.println(sb);
    }
}


// 1 2 3 6 5 1
// 1 1 2 3 6 5