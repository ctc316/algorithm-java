public class Solution {
    /*
     * @param : a string to be split
     * @return: all possible split string array
     */
    public List<List<String>> splitString(String s) {
        if (s == null) {
            return new ArrayList<>();
        }

        List<List<String>> results = new ArrayList<>();
        dfs(s, 0, new ArrayList<String>(), results);

        return results;
    }

    private void dfs(String s, int start, List<String> combination, List<List<String>> results) {
        if(start >= s.length()) {
            results.add(new ArrayList<String>(combination));
            return;
        }

        // single char
        combination.add(s.substring(start, start + 1));
        dfs(s, start + 1, combination, results);
        combination.remove(combination.size() - 1);

        // double chars
        if (start < s.length() - 1) {
            combination.add(s.substring(start, start + 2));
            dfs(s, start + 2, combination, results);
            combination.remove(combination.size() - 1);
        }
    }
}