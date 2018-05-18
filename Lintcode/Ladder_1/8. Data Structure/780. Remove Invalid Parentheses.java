public class Solution {
    /**
     * @param s: The input string
     * @return: Return all possible results
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> results = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            results.add("");
            return results;
        }

        // count how many left and right parentheses need to be removed
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else if (ch == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        // DFS, find the combinations
        dfs(s, 0, left, right, results);

        return results;
    }

    private void dfs(String s, int start, int left, int right, List<String> results) {
        if (left == 0 && right == 0) {
            if (isValid(s)) {
                results.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (i > start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            if (s.charAt(i) == '(' && left > 0) {
                String next = s.substring(0, i) + s.substring(i + 1);
                dfs (next, i, left - 1, right, results);
            } else if (s.charAt(i) == ')' && right > 0) {
                String next = s.substring(0, i) + s.substring(i + 1);
                dfs (next, i, left, right - 1, results);
            }
        }
    }

    private boolean isValid(String s) {
        int leftPt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftPt++;
            } else if (s.charAt(i) == ')') {
                leftPt--;
                if (leftPt < 0) {
                    return false;
                }
            }
        }

        if (leftPt != 0) {
            return false;
        }

        return true;
    }
}