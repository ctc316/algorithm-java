public class Solution {
    /*
     * @param nums: a mountain sequence which increase firstly and then decrease
     * @return: then mountain top
     */
    public int mountainSequence(int[] nums) {
       if (nums == null || nums.length == 0) {
            return 0;
       }

       int left = 0;
       int right = nums.length - 1;
       int mid = 0;
       while (left <= right) {
            mid = left + (right - left) / 2;
            //decending
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                right = mid - 1;
            }
            //ascending
            else if (mid + 1 < nums.length - 1 && nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            }
            else {
                return nums[mid];
            }
       }
       return nums[mid];
    }
}