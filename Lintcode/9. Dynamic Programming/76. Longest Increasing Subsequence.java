public class Solution {
    /**
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        // initialize
        int len = nums.length;
        int[] count = new int[len];
        for (int i = 0; i < len; i++) {
            count[i] = 1;
        }

        // states
        int longest = 1;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                // function
                if (nums[j] > nums[i] && count[i] + 1 > count[j]) {
                    count[j] = count[i] + 1;
                    if (count[j] > longest) {
                        longest = count[j];
                    }
                }
            }
        }

        // answer
        return longest;
    }
}