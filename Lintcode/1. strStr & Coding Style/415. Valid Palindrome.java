public class Solution {
    /**
     * @param s: A string
     * @return: Whether the string is a valid palindrome
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !isValid(s.charAt(left))) {
                left++;
            }
            while (left < right && !isValid(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    private boolean isValid (char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }
}