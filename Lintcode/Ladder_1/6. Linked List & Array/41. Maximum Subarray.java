public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        /*
         *  Prefix Method
         *    [−2,2,−3,4,−1,2,1,−5,3]
         *  [0,-2,0,-3,1, 0,2,3,-2,1]
         */
        int maxSum = Integer.MIN_VALUE,
            minSum = 0,
            sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            maxSum = Math.max(maxSum, sum - minSum);
            minSum = Math.min(minSum, sum);
        }

        return maxSum;


        /*
         * Greedy Method
         *  pick only contiguous postive sums
         */
        // int max = Integer.MIN_VALUE, sum = 0;
        // for (int i = 0; i < nums.length; i++) {
        //     sum += nums[i];
        //     max = Math.max(max, sum);
        //     sum = Math.max(sum, 0);
        // }

        // return max;
    }
}