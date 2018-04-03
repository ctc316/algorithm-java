public class NumMatrix {

    int[][] matrix;
    int height, width;

    /**
     * @return: nothing
     */
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;

        if (matrix != null) {
            height = matrix.length;
        } else {
            height = 0;
        }

        if (height != 0 && matrix[0] != null) {
            width = matrix[0].length;
        } else {
            width = 0;
        }

    }

    public void update(int row, int col, int val){
        if (isInBound(row, col)) {
            this.matrix[row][col] = val;
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2){
        if (!isInBound(row1, col1) || !isInBound(row2, col2)) {
            return -1;
        }

        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                sum += matrix[i][j];
            }
        }

        return sum;
    }

    private boolean isInBound(int row, int col) {
        return row > - 1 && row < height && col > -1 && col < width;
    }
}