public class Solution {

    private static final int EMPTY = 0;
    private static final int OBSTACLE = 1;

    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] grid = new int[m][n];

        // initialize
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == EMPTY) {
                grid[i][0] = 1;
            } else {
                // can't pass
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == EMPTY) {
                grid[0][i] = 1;
            } else {
                break;
            }
        }

        // state
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // function
                if(obstacleGrid[i][j] == EMPTY) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
                } else {
                    grid[i][j] = 0;
                }
            }
        }

        // answer
        return grid[m - 1][n - 1];
    }
}