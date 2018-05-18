public class Solution {
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        if (n < 1 || k < 1 || k > n) {
            return results;
        }

        dfs(n, k, 1, new ArrayList<Integer>(), results);
        return results;
    }

    private void dfs(int n, int remain, int start,
                    List<Integer> combination,
                    List<List<Integer>> results) {
        if (remain == 0) {
            results.add(new ArrayList<Integer>(combination));
            return;
        }

        for (int i = start; i <= n; i++) {
            combination.add(i);
            dfs(n, remain - 1, i + 1, combination, results);
            combination.remove(combination.size() - 1);
        }
    }
}