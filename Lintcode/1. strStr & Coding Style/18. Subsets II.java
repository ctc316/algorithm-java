public class Solution {
    /*
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            ans.add(new ArrayList());
            return ans;
        }

        Arrays.sort(nums);

        recursion(nums, 0, new ArrayList<>(), ans);

        return ans;
    }

    public void recursion(  int[] nums,
                            int startIndex,
                            ArrayList<Integer> subset,
                            List<List<Integer>> ans) {

        ans.add(new ArrayList<Integer>(subset));

        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            subset.add(nums[i]);
            recursion(nums, i + 1, subset, ans);
            subset.remove(subset.size() - 1);
        }
    }
}