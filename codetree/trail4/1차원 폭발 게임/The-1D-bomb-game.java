import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int end = n-1;
        while (end>=0) {
            int num = arr[0];
            int cnt = 1;
            int exist = 0;
            for (int i = 1; i < end+1; i++) {
                if (arr[i] == num) cnt++;
                else {
                    if (cnt >= m) {
                        exist++;
                        for (int j = i-1-cnt+1; j <= i-1; j++) {
                            arr[j] = 0;
                        }
                    }
                    cnt = 1;
                    num = arr[i];
                }
            }
            if (cnt >= m) {
                exist++;
                for (int i = 0; i < cnt; i++) {
                    arr[end-i] = 0;
                }
            }

            if (exist == 0) break;

            int write = 0;
            for (int i = 0; i < end + 1; i++) {
                if (arr[i] != 0) arr[write++] = arr[i];
            }
            end = write - 1;
        }

        System.out.println(end+1);
        for (int i = 0; i < end + 1; i++) {
            System.out.println(arr[i]);
        }
    }
}