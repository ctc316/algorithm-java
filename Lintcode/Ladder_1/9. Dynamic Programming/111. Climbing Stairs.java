// Version 1: DP
public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }

        // 0 1 2  3   4  5 6 7 8 9 steps
        // 0 1 2 2+1 3+2
        //

        // initialize
        int curr = 0;
        int last1ways = 1;
        int last2ways = 0;

        // status
        for (int i = 1; i <= n; i++) {
            curr = last1ways + last2ways;
            last2ways = last1ways;
            last1ways = curr;
        }

        return curr;
    }
}


// Version 2: 搜索 + 記憶
public class Solution {

    int[] dp;

    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }

        dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        findWays(n);

        return dp[n];
    }

    private void findWays(int n) {
        if (n == 0 || n == 1 || dp[n] != 0) {
            return;
        }

        findWays(n - 1);
        findWays(n - 2);
        dp[n] = dp[n - 1] + dp[n - 2];
    }
}
