public class Solution {

    private static final int PIVOT = 1;

    /**
     * @param nums: A list of integer which is 0, 1 or 2
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // [2,0,0,1,2,0,2]
        // [2,0,0,1,2,0,2]
        // [0,0,0,1,2,2,2]

        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] < PIVOT) {
                swap(nums, i, left);
                left++;
                i++;
            } else if (nums[i] > PIVOT) {
                swap(nums, i, right);
                right--;
            } else {
                i++;
            }
        }
    }

    private void swap (int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}