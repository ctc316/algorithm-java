public class Solution {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // [0,2,1,3,1,2,1,3,2,4]  l:1, r:6
        // [0,1,1,3,1,2,3,3,2,4]  l:3, r:4
        // [0,1,1,1,3,2,3,3,2,4]  l:4, r:3

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            while (left <= right && nums[left] < k) {
                left++;
            } 
            while (left <= right && nums[right] >= k) {
                right--;
            }

            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        return left;
    }
}