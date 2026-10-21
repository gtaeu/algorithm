import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        HashMap<String, Integer> map = new HashMap<>();
        
        int m = 0;
        for (String gem : gems) {
            if (map.containsKey(gem)) continue;
            map.put(gem, m++);
        }
        int[] in = new int[m];
        int kind = 0;
        int n = gems.length;
        int r = 0;
        int[] ans = {0, 0};
        int min = Integer.MAX_VALUE;
        
        for (int l = 0; l < n; l++) {
            while (r != n && kind != m) {
                if (in[map.get(gems[r])] == 0) {
                    kind++;
                }
                in[map.get(gems[r])]++;
                r++;
            }
            
            if (kind == m) {
                if (min > r - l) {
                    min = r - l;
                    ans[0] = l+1;
                    ans[1] = r;
                }
            }
            
            if (in[map.get(gems[l])] == 1) {
                kind--;
            }
            in[map.get(gems[l])]--;
        }
        
        return ans;
    }
}