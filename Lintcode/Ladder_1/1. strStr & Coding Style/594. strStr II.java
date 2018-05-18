public class Solution {
    public static final int BASE = 1000000;
    public static final int PRIME = 31;
    /*
     * @param source: A source string
     * @param target: A target string
     * @return: An integer as index
     */
    public int strStr2(String source, String target) {
        if(source == null || target == null) {
            return -1;
        }

        int t = target.length();
        if (t == 0) {
            return 0;
        }

        //target hash code
        int tcode = 0;
        for (int i = 0; i < t; i++) {
            tcode = (tcode * PRIME + target.charAt(i)) % BASE;
        }

        // prime ^ t
        int power = 1;
        for (int i = 0; i < t; i++) {
            power = (power * PRIME) % BASE;
        }

        //source hash code
        int scode = 0;
        for (int i = 0; i < source.length(); i++) {
            //abc + d
            scode = (scode * PRIME + source.charAt(i)) % BASE;
            if (i < t - 1) {
                continue;
            }

            //abcd - a
            if (i >= t) {
                scode = scode - (source.charAt(i - t) * power) % BASE;
                // check negative
                if (scode < 0) {
                    scode += BASE;
                }
            }

            //compare and double check
            if (scode == tcode) {
                if (source.substring(i - t + 1, i + 1).equals(target)) {
                    return i - t + 1;
                }
            }
        }

        return -1;
    }
}