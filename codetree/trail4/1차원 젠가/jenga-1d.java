import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            ArrayList<Integer> lst = new ArrayList<>();
            int idx = 0;
            for (int j = 0; j < arr.length; j++) {
                if (j >= s-1 && j <= e-1) continue;
                lst.add(arr[j]);
            }
            arr = lst.stream().mapToInt(Integer::intValue).toArray();
        }

        // System.out.println(Arrays.toString(arr));
        System.out.println(arr.length);
        for (int a : arr) {
            System.out.println(a);
        }
    }
}