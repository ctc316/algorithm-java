public class Solution {
    /*
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> results = new ArrayList<>();

        if (num == null || num.length == 0) {
            return results;
        }

        Arrays.sort(num);
        dfs(num, 0, target, new ArrayList<Integer>(), results);

        return results;
    }

    private void dfs(int[] num,
                     int startIdx,
                     int remain,
                     List<Integer> combination,
                     List<List<Integer>> results) {

        if (remain == 0) {
            results.add(new ArrayList<Integer>(combination));
            return;
        }

        for (int i = startIdx; i < num.length; i++) {
            if (i > startIdx && num[i] == num[i - 1]) {
                continue;
            }

            if (remain < num[i]) {
                break;
            }

            combination.add(num[i]);
            dfs(num, i + 1, remain - num[i], combination, results);
            combination.remove(combination.size() - 1);
        }
    }
}