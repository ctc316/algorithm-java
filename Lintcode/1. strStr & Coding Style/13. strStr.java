public class Solution {
    /*
     * @param source: source string to be scanned.
     * @param target: target string containing the sequence of characters to match
     * @return: a index to the first occurrence of target in source, or -1  if target is not part of source.
     */
    public int strStr(String source, String target) {
        if(source == null || target == null) {
            return -1;
        }

        for(int i = 0; i < source.length() - target.length() + 1; i++) {
            boolean found = true;
            for(int j = 0; j < target.length(); j++) {
                if(source.charAt(i+j) != target.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if(found) {
                return i;
            }
        }

        return -1;
    }
}