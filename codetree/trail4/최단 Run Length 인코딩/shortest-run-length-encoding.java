import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int len = str.length();

        char[] arr = new char[len * 2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = str.charAt(i % len);
        }

        int ans = 20;
        for (int i = 0; i < len; i++) {
            StringBuilder sb = new StringBuilder();
            int cnt = 1;
            char c = str.charAt(i);
            for (int j = i+1; j < i + len; j++) {
                if (arr[j] == arr[j-1]) {
                    cnt++;
                    continue;
                }
                sb.append(c).append(cnt);
                cnt = 1;
                c = arr[j];
            }
            sb.append(c).append(cnt);
            if (ans > sb.length()) {
                ans = sb.length();
            }
        }

        System.out.println(ans);
    }
}