public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;
        int[][] nPaths = new int[n][m];

        // initialize
        nPaths[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            nPaths[i][0] = nPaths[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            nPaths[0][i] = nPaths[0][i - 1] + grid[0][i];
        }

        // state
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // function
                nPaths[i][j] = grid[i][j] + Math.min(nPaths[i - 1][j], nPaths[i][j - 1]);
            }
        }

        // answer
        return nPaths[n - 1][m - 1];
    }
}