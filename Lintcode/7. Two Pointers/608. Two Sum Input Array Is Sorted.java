public class Solution {
    /*
     * @param nums: an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
             int remain = target - nums[left] - nums[right];
             if (remain > 0) {
                 left++;
             } else if (remain < 0) {
                 right--;
             } else {
                 return new int[]{left + 1, right + 1};
             }
        }

        return new int[0];
    }
}