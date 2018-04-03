public class Solution {
    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }

        int[][] grid = new int[m][n];

        // initialize
        for (int i = 0; i < m; i++) {
            grid[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            grid[0][j] = 1;
        }

        // state
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // function
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }

        // answer
        return grid[m - 1][n - 1];
    }
}