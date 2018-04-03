// DFS + Divide & Conquer + Memorize, Time: O(2^n), Space: O(n^2)
public class Solution {

    Map<String, List<String>> done;
    Set<String> dict;

    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return new ArrayList<String>();
        }

        this.dict = wordDict;
        this.done = new HashMap<>();

        // handle "", the meaning of [""] is not the same as [], which means no valid result
        done.put("", new ArrayList<>());
        done.get("").add("");

        List<String> results = new ArrayList<String>();
        return dfs(s);
    }

    private List<String> dfs(String s) {
        if (done.containsKey(s)) {
            return done.get(s);
        }

        List<String> results = new ArrayList<>();

        for (int end = 1; end <= s.length(); end++) {
            String s1 = s.substring(0, end);
            if (dict.contains(s1)) {
                String s2 = s.substring(end, s.length());
                List<String> s2Results = dfs(s2);
                for (String item : s2Results) {
                    if (item == "") {
                        results.add(s1);
                    } else {
                        results.add(s1 + " " + item);
                    }
                }
            }
        }

        done.put(s, results);
        return results;
    }
}