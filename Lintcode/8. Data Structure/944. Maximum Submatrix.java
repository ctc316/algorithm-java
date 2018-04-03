public class Solution {
    /**
     * @param matrix: the given matrix
     * @return: the largest possible sum
     */
    public int maxSubmatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        // i,j
        // [1,  3, -1],
        // [2,  3, -2],
        // [-1,-2, -3]

        // rowSum
        // [1,  4, 3],
        // [2,  5, 5],
        // [-1,-3, -6]
        // when i = 0, j = 1 ->  findMaximumSum -> 0 ~ 1 = 9

        int n = matrix.length;
        int[] rowSum = new int[n];
        int max = Integer.MIN_VALUE;

        // calculate sums of row of col i ~ j and compare
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // update row sum
                for (int row = 0; row < n; row++) {
                    if (j == i) {
                        rowSum[row] = matrix[j][row];
                    } else {
                        rowSum[row] += matrix[j][row];
                    }
                }

                // compare
                int local_max = findMaximumSum(rowSum);
                if (local_max > max) {
                    max = local_max;
                }
            }
        }

        return max;
    }


    private int findMaximumSum(int[] nums) {
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }
}