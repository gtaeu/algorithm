class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        List<Integer> lst = new ArrayList<>();

        int r = 0;
        // o[0]: 값, o[1]: 인덱스
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int l = 0; l < n - k + 1; l++) {
            while (r != n && r-l != k) {
                while (!q.isEmpty() && nums[q.peekLast()] < nums[r]) {
                    q.pollLast();
                }
                q.addLast(r);
                r++;
            }

            if (r - l == k) {
                while (q.peekFirst() < l) {
                    q.pollFirst();
                }
                lst.add(nums[q.peekFirst()]);
                // lst.add(nums[maxIdx]);
            }

        }
        return lst.stream().mapToInt(Integer::intValue).toArray();
    }
}