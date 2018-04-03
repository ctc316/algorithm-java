public class Solution {

    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }

        Map<Character, String> pairs = new HashMap<>();
        Set<String> hash = new HashSet<>();
        return dfs(pattern, str, pairs, hash);
    }

    private boolean dfs(String pattern, String str, Map<Character, String> pairs, Set<String> hash) {
        if (pattern.length() == 0) {
            return str.length() == 0;
        }

        Character ch = pattern.charAt(0);
        String next = "";

        if (pairs.containsKey(ch)) {
            next = pairs.get(ch);
            if (!str.startsWith(pairs.get(ch))) {
                return false;

            }
            return  dfs(pattern.substring(1),
                        str.substring(next.length()),
                        pairs,
                        hash);
        }

        for (int i = 1; i <= str.length(); i++) {
            next = str.substring(0, i);
            if (hash.contains(next)) {
                continue;
            }

            pairs.put(ch, next);
            hash.add(next);
            if (dfs(pattern.substring(1),
                    str.substring(i),
                    pairs,
                    hash)) {
                return true;
            }
            pairs.remove(ch);
            hash.remove(next);
        }

        return false;
    }
}