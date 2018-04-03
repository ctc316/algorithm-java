public class Solution {

    private boolean[][] isPalindrome;

    private void getPalindrom(String s) {
        int n = s.length();
        isPalindrome = new boolean[n][n];

        // a'aa'abbcd, init 2 adjcent chars to be starting points
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i] = true;  // odd
            isPalindrome[i][i+1] = (s.charAt(i) == s.charAt(i+1)); //even
        }
        isPalindrome[n - 1][n - 1] = true;

        // expansion - from small sizes to large ones
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }

        System.out.println(Arrays.deepToString(isPalindrome));
    }

    /*
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {

        List<List<String>> results = new ArrayList<>();

        if (s.length() == 0) {
           return results;
        }

        // pre-process palindrome
        getPalindrom(s);

        List<String> partition = new ArrayList<String>();
        dfs(s, 0, partition, results);

        return results;
    }

    private void dfs (String s,
                      int startIdx,
                      List<String> partition,
                      List<List<String>> results) {

        if (startIdx == s.length()) {
            results.add(new ArrayList<String>(partition));
            return;
        }

        for (int i = startIdx; i < s.length(); i++) {
            // pre-porcess method
            if (!isPalindrome[startIdx][i]) {
                continue;
            }

            //  a a b b c
            // s 1 2 3 4 5
            String sub = s.substring(startIdx, i + 1);

            // normal method
            // if (!isPalindrome(sub)) {
            //     continue;
            // }

            partition.add(sub);
            dfs(s, i + 1, partition, results);
            partition.remove(partition.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}