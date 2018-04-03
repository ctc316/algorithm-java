public class Solution {
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return results;
        }

        dfs(digits, 0, new StringBuilder(digits.length()), results);
        return results;
    }

    private void dfs(String digits, int index, StringBuilder str, List<String> results) {
        if (index == digits.length()) {
            results.add(str.toString());
            return;
        }

        for (char ch : getAlphabets(digits.charAt(index))) {
            str.append(ch);
            dfs(digits, index + 1, str, results);
            str.deleteCharAt(str.length() - 1);
        }
    }

    private char[] getAlphabets(char num) {
        switch(num) {
            case '2':
                return new char[]{'a', 'b', 'c'};
            case '3':
                return new char[]{'d', 'e', 'f'};
            case '4':
                return new char[]{'g', 'h', 'i'};
            case '5':
                return new char[]{'j', 'k', 'l'};
            case '6':
                return new char[]{'m', 'n', 'o'};
            case '7':
                return new char[]{'p', 'q', 'r', 's'};
            case '8':
                return new char[]{'t', 'u', 'v'};
            case '9':
                return new char[]{'w', 'x', 'y', 'z'};
        }

        return new char[0];
    }
}