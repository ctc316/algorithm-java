// Version 1: O(n) time, O(n) space
public class Solution {
    /*
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        if (nums.length == 0) {
            return 0
        }

        Set<Integer> set = new HashSet<>();
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (!set.contains(n)) {
                set.add(n);
                nums[pos] = n;
                pos++;
            }
        }
        return pos;
    }
}


// Version 2: Sort, O(nlogn) time, O(1) space
public class Solution {
    /*
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int tail = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (nums[i] != nums[tail]) {
                nums[++tail] = nums[i];
            }
        }
        return tail + 1;
    }
}