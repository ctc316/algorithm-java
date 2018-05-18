public class Solution {

    private int theMissing;

    /**
     * @param n: An integer
     * @param str: a string with number from 1-n in random order and miss one number
     * @return: An integer
     */
    public int findMissing2(int n, String str) {
        theMissing = -1;
        boolean[] isFound = new boolean[n + 1];
        dfs(n, str, 0, isFound);
        return theMissing;
    }

    private void dfs(int n, String str, int start, boolean[] isFound) {
        // already found answer
        if (theMissing != -1) {
            return;
        }

        // finished the str, get the missing num
        if (start == str.length()) {
            for (int i = 1; i <= n; i++) {
                if (!isFound[i]) {
                    theMissing = i;
                    return;
                }
            }
            return;
        }

        // if first char is 0, the combination is not gonna work
        if (str.charAt(start) == '0') {
            return;
        }

        // check single & double chars separately
        for (int ch = 1; ch <= 2 && start + ch <= str.length(); ch++) {
            int num = Integer.parseInt(str.substring(start, start + ch));
            if (num > 0 && num <= n && !isFound[num]) {
                isFound[num] = true;
                dfs(n, str, start + ch, isFound);
                isFound[num] = false;
            }
        }

        return;
    }
}