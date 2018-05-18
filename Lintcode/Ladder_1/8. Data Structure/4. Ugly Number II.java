public class Solution {

    final long[] ugly_factor = new long[]{2, 3, 5}; 

    /**
     * @param n: An integer
     * @return: the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        if (n < 1) {
            return 0;
        }

        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();

        pq.offer(1L);
        set.add(1L);

        int count = 0;
        while (count < n - 1) {
            long num = pq.poll();
            count++;
            for (long u : ugly_factor) {
                long next = num * u;
                if (!set.contains(next)) {
                    pq.offer(next);
                    set.add(next);
                }
            }
        }

        return pq.poll().intValue();
    }
}