public class Solution {
    /*
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum5(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        Arrays.sort(nums);

        //[-1,1,2,3,4,5,6,7]  9
        int count = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                // -1 + 7 < 9  =>  -1 + (1~6) < 9
                count += right - left;
                left++;
            }
        }

        return count;
    }
}