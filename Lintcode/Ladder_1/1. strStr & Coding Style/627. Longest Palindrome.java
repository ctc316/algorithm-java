// Version 1
public class Solution {
    /**
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // count alphbets
        int[] counts = new int[52];
        for (int i = 0; i < s.length(); i++){
            int pos = (int)s.charAt(i);
            if(pos >= 'a') {
                pos = pos - 'a' + 26;
            } else {
                pos -= 'A';
            }
            counts[pos] += 1;
        }

        // count odd and even
        boolean oddCenter = false;
        int evens = 0;
        for (int c : counts) {
            while (c > 1) {
                evens += 2;
                c -= 2;
            }
            if (c == 1) {
                oddCenter = true;
            }
        }

        return evens + (oddCenter ? 1 : 0);
    }
}


// Version 2