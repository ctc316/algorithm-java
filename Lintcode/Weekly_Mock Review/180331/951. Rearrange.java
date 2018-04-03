public class Solution {
    /**
     * @param nums: the num arrays
     * @return: the num arrays after rearranging
     */
    public int[] rearrange(int[] nums) {
        if (nums == null ||nums.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums);
        int n = nums.length;
        int[] results = new int[n];

        int half = n / 2;

        // first half -> even
        for (int i = 0; i < half; i++) {
            results[i * 2] = nums[i];
        }
        //second half -> odd
        for (int i = 0; half + i < n; i++) {
            results[i * 2 + 1] = nums[half + i];
        }

        return results;
    }
}