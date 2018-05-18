
// Version 1: DFS
public class Solution {

    private int least;

    /**
     * @param n: a positive integer
     * @return: An integer
     */
    public int numSquares(int n) {
        if (n <= 1) {
            return n;
        }

        int len = (int) Math.sqrt(n);
        int[] nums = new int[len];
        //reverse order
        for (int i = 0; i < len; i++) {
            nums[len - 1 - i] = (i + 1) * (i + 1);
        }

        least = Integer.MAX_VALUE;
        dfs(nums, 0, n, 0);

        return least;
    }

    private void dfs(int[] nums, int startIdx, int target, int count) {
        if (count >= least) {
            return;
        }

        for (int i = startIdx; i < nums.length; i++) {
            int remain = target - nums[i];
            if (remain > 0) {
                dfs(nums, startIdx, remain, count + 1);
            } else if (remain < 0) {
                continue;
            } else {
                count++;
                if(count < least) {
                    least = count;
                }
            }
        }
    }
}


// Version 2: DP
public class Solution {

    private int least;

    /**
     * @param n: a positive integer
     * @return: An integer
     */
    public int numSquares(int n) {
        if (n <= 1) {
            return n;
        }

        // [0, 1, 2, 3, 4, 5, 6]
        // [M, 1, 1, M, M, M, M]
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 0; i * i <= n; i++) {
            dp[i * i] = 1;
        }

        // [M, 1, 1, 2, 2, 2, 2]
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}