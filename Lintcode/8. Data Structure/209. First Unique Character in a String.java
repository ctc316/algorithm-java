public class Solution {
    /**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        int[] counts = new int[26];

        for (int i = 0; i <  str.length(); i++) {
            counts[str.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < str.length(); i++) {
            if (counts[str.charAt(i) - 'a'] == 1) {
                return str.charAt(i);
            }
        }

        return 0;
    }
}