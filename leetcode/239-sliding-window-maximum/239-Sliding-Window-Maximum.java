class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        List<Integer> lst = new ArrayList<>();

        int r = 0;
        // o[0]: 값, o[1]: 인덱스
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? Integer.compare(o2[1], o1[1]) : Integer.compare(o2[0], o1[0]));
        int maxIdx = -1;

        for (int l = 0; l < n - k + 1; l++) {
            while (r != n && r-l != k) {
                pq.add(new int[]{nums[r], r});
                if (maxIdx == -1 || nums[maxIdx] <= nums[r]) maxIdx = r;
                r++;
            }

            if (r - l == k) {
                lst.add(nums[maxIdx]);
                // lst.add(nums[maxIdx]);
            }

            if (l  == maxIdx) {
                pq.poll();
                while (!pq.isEmpty() && pq.peek()[1] < l) {
                    pq.poll();
                }
                if (pq.isEmpty()) {
                    maxIdx = -1;
                }
                else {
                    maxIdx = pq.peek()[1];
                }
            }
        }
        return lst.stream().mapToInt(Integer::intValue).toArray();
    }
}