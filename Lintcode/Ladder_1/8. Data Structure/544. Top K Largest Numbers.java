public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return new int[k];
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for (int i = 0; i < nums.length; i++) {
            minHeap.offer(nums[i]);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] results = new int[k];
        for (int i = 0; i < k; i++) {
            results[k - 1 - i] = minHeap.poll();
        }

        return results;
    }
}