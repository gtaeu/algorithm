class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        n = req_skills.length;
        dp = new Integer[1 << n];
        choice = new int[1 << n];
        Req_skills = req_skills;
        People = people;
        map = new HashMap<>();
        for (int i = 0; i < req_skills.length; i++) {
            map.put(req_skills[i], i);
        }
        dfs(0);

        List<Integer> lst = new ArrayList<>();
        build(0, lst);

        return lst.stream().mapToInt(Integer::intValue).toArray();

    }

    static String[] Req_skills;
    static List<List<String>> People;
    static Integer[] dp;
    static int n;
    static HashMap<String, Integer> map;
    static int[] choice;


    // 현재 조합이 mask일때 모든 요구기술을 만족하기 위해 필요한 사람 수의 최소
    static int dfs(int mask) {
        if (mask == (1 << n) - 1) {
            return 0;
        }

        if (dp[mask] != null) return dp[mask];

        int res = Integer.MAX_VALUE;
        int bestPerson = -1;

        for (int i = 0; i < People.size(); i++) {
            int newMask = mask;
            for (String s : People.get(i)) {
                newMask |= (1 << map.get(s));
            }
            if (newMask == mask) continue;
            int temp = 1 + dfs(newMask);
            if (res > temp) {
                res = temp;
                bestPerson = i;
            }
        }


        choice[mask] = bestPerson;
        return dp[mask] = res;
    }

    static void build(int mask, List<Integer> lst) {
        if (mask == (1 << n) - 1) {
            return;
        }

        int idx = choice[mask];
        lst.add(idx);
        for (String s : People.get(idx)) {
            mask |= 1 << map.get(s);
        }
        build(mask, lst);
    }
}