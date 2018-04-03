public class Solution {
    /*
     * @param candidates: A list of integers
     * @param target: An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();

        if(candidates == null || candidates.length == 0) {
            return results;
        }

        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<Integer>(), results);

        return results;
    }

    private void dfs(int[] candidates,
                     int startIdx,
                     int remain,
                     List<Integer> combination,
                     List<List<Integer>> results) {

        if (remain == 0) {
            results.add(new ArrayList<Integer>(combination));
            return;
        }

        for (int i = startIdx; i < candidates.length; i++) {
            if (i > 0  && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (remain - candidates[i] < 0) {
                break;
            }

            combination.add(candidates[i]);
            dfs(candidates, i, remain - candidates[i], combination, results);
            combination.remove(combination.size() - 1);
        }
    }
}