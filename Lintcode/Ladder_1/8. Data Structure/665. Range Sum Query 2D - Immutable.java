public class NumMatrix {

    private int[][] dp;
    private int n, m;

    /*
    * @param matrix: a 2D matrix
    */public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            n = 0;
            m = 0;
            return;
        }

        n = matrix.length;
        m = matrix[0].length;
        dp = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i + 1][j + 1] =  matrix[i][j]
                                    + dp[i + 1][j]
                                    + dp[i][j + 1]
                                    - dp[i][j];
            }
        }
    }

    /*
     * @param row1: An integer
     * @param col1: An integer
     * @param row2: An integer
     * @param col2: An integer
     * @return: An integer
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (dp == null || !isInBound(row1, col1) || !isInBound(row2, col2)) {
            return Integer.MIN_VALUE;
        }

        return dp[row2 + 1][col2 + 1]
                - dp[row1][col2 + 1]
                - dp[row2 + 1][col1]
                + dp[row1][col1];
    }

    private boolean isInBound(int row, int col) {
        return row > -1 && row < n && col > -1 && col < m;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */