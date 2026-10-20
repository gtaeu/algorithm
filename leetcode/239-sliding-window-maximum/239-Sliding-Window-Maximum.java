/*
변수들 간의 싱크 맞추기
q, l, r이 독립적인 상태가 아니므로 싱크를 맞춰야한다
즉 하나가 변하면 그에 따라 다른 변수들도 동기화해야한다
동기화를 안하면 l을 변화하고, q를 읽으면 불일치 상태됨
*/


class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        List<Integer> lst = new ArrayList<>();

        int r = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int l = 0; l < n - k + 1; l++) {
            while (r != n && r-l != k) {
                while (!q.isEmpty() && nums[q.peekLast()] < nums[r]) {
                    q.pollLast();
                }
                q.addLast(r);
                r++;
            }

            lst.add(nums[q.peekFirst()]);

            if (q.peekFirst() == l) {
                q.pollFirst();
            }

        }
        return lst.stream().mapToInt(Integer::intValue).toArray();
    }
}
