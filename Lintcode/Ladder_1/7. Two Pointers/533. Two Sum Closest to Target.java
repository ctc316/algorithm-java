public class Solution {
    /*
     * @param nums: an integer array
     * @param target: An integer
     * @return: the difference between the sum and the target
     */
    public int twoSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return Integer.MAX_VALUE;
        }

        Arrays.sort(nums);

        int closest = Integer.MAX_VALUE;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int remain = target - nums[left] - nums[right];

            if (remain > 0) {
                left++;
            } else if (remain < 0) {
                right--;
            } else {
                return 0;
            }

            closest = Math.min(closest, Math.abs(remain));
        }

        return closest;
    }
}