public class Solution {
    /*
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int bottom = 0;
        int top = matrix.length - 1;
        int y = -1;
        while (bottom <= top) {
            int mid = bottom + (top - bottom) / 2;
            int[] xArr = matrix[mid];
            if (target < xArr[0]) {
                top = mid - 1;
            } else if (target > xArr[xArr.length - 1]) {
                bottom = mid + 1;
            } else {
                y = mid;
                break;
            }
        }

        if (y == -1) {
            return false;
        }

        int[] xArr = matrix[y];
        int left = 0;
        int right = xArr.length - 1;
        int x = -1;
        while (left <= right) {
            x = left + (right - left) / 2;
            if (target < xArr[x]) {
                right = x - 1;
            } else if (target > xArr[x]) {
                left = x + 1;
            } else {
                return true;
            }
        }

        return false;
    }
}