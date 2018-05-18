public class Solution {
    /**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        if (A.length == 0 || A[0].length == 0) {
            return new int[][]{};
        }

        int A_rows = A.length;
        int A_cols = A[0].length;
        int B_cols = B[0].length;
        int[][] results = new int[A_rows][B_cols];

        for (int A_row = 0; A_row < A_rows; A_row++) {
            for (int B_col = 0; B_col < B_cols; B_col++) {
                int sum = 0;
                for (int A_col = 0; A_col < A_cols; A_col++) {
                    // B_row == A_col
                    sum += A[A_row][A_col] * B[A_col][B_col];
                }
                results[A_row][B_col] = sum;
            }
        }

        return results;
    }
}