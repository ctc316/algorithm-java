public class Solution {
    /*
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        double value = 1;
        int pow = 0;

        boolean isNeg = n < 0;
        if (isNeg) {
            x = 1 / x;
            n = -(n + 1); // +1 to avoid overflow when n == MIN_VALUE;
        }

        double ans = 1, tmp = x;

        while (n != 0) {
            if (n % 2 == 1) {
                ans *= tmp;
            }
            tmp *= tmp;
            n /= 2;

            System.out.println(n + " -> " + ans);
        }

        if (isNeg) {
            ans *= x;
        }

        return ans;
    }
}