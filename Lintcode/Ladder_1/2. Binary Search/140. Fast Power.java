// Version 1: Iteration, find maximum exp for substraction in every round
public class Solution {
    /**
     * @param a: A 32bit integer
     * @param b: A 32bit integer
     * @param n: A 32bit integer
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        if (n == 0) {
            return 1 % b;
        }

        long ans = 1;
        while (n > 0) {
            int round_exp = 1;
            long round_a = a % b;
            while (round_exp <= n / 2) {
                round_exp *= 2;
                round_a *= round_a;
                round_a %= b;
            }
            n -= round_exp;
            ans *= round_a;
            ans %= b;
        }

        return (int) ans;
    }
}


// Version 2: Recursive
public class Solution {
    /**
     * @param a: A 32bit integer
     * @param b: A 32bit integer
     * @param n: A 32bit integer
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        if (n == 1) {
            return a % b;
        }
        if (n == 0) {
            return 1 % b;
        }

        long prod = fastPower(a, b, n / 2);
        prod = (prod * prod) % b;
        if (n % 2 == 1) {
            prod = (prod * a) % b;
        }
        return (int) prod;
    }
}