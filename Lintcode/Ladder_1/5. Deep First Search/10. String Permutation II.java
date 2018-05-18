public class Solution {
    /**
     * @param str: A string
     * @return: all permutations
     */
    public List<String> stringPermutation2(String str) {
        int[] counts = new int[26];
        for (int i = 0; i < str.length(); i++) {
            counts[str.charAt(i) - 'a'] += 1;
        }

        ArrayList<String> results = new ArrayList<>();
        dfs(counts, new StringBuilder(), results, str.length());

        return results;
    }

    private void dfs (int[] counts, StringBuilder sb, ArrayList<String> results, int targetLen) {
        if (sb.length() == targetLen) {
            results.add(sb.toString());
            return;
        }

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                counts[i]--;
                sb.append((char) ('a' + i));
                dfs(counts, sb, results, targetLen);
                sb.deleteCharAt(sb.length() - 1);
                counts[i]++;
            }
        }
    }
}