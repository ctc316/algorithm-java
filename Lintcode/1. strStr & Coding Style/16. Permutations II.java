public class Solution {
    /*
     * @param :  A list of integers
     * @return: A list of unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        if(nums == null || nums.length == 0) {
            ans.add(new ArrayList());
            return ans;
        }

        List<Integer> numsList = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            numsList.add(nums[i]);
        }

        Collections.sort(numsList);

        recursion(ans, numsList, new ArrayList());
        return ans;
    }

    public void recursion(List<List<Integer>> ans, List<Integer> remains, List<Integer> curr){
        if(remains.size() == 0) {
            ans.add(new ArrayList<Integer>(curr));
            return;
        }

        for(int i = 0; i < remains.size(); i++) {
            if (i+1 < remains.size() && remains.get(i) == remains.get(i+1)) {
                continue;
            }
            /* Method I: deep copy array */
            // List<Integer> n_remains = new ArrayList<Integer>(remains);
            // List<Integer> n_curr = new ArrayList<Integer>(curr);
            // n_curr.add(n_remains.get(i));
            // n_remains.remove(i);
            // recursion(ans, n_remains, n_curr);

            /* Method II: backtrace */
            int val = remains.get(i);
            curr.add(val);
            remains.remove(i);
            recursion(ans, remains, curr);
            remains.add(i, val);
            curr.remove(curr.size()-1);
        }
    }
};
