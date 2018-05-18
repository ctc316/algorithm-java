// Version 1: Memorizing Search
public class Solution {

    int[][] minSum;

    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        if (triangle == null || triangle.length == 0 || triangle[0].length == 0) {
            return 0;
        }

        minSum = new int[triangle.length][triangle.length];

        return findPath(triangle, 0, 0);
    }

    private int findPath(int[][] triangle, int level, int index) {
        if (minSum[level][index] != 0) {
            return minSum[level][index];
        }

        if (level == triangle.length - 1) {
            return triangle[level][index];
        }

        int left = findPath(triangle, level + 1, index);
        int right = findPath(triangle, level + 1, index + 1);

        minSum[level][index] = triangle[level][index] + Math.min(left, right);

        return minSum[level][index];
    }
}


// Version : Top Down
public class Solution {

    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        if (triangle == null || triangle.length == 0 || triangle[0].length == 0) {
            return -1;
        }

        int n = triangle.length;
        int[][] minSum = new int[n][n];

        // initialize
        minSum[0][0] = triangle[0][0];
        for (int i = 1; i < n; i++) {
            minSum[i][0] = minSum[i - 1][0] + triangle[i][0];
            minSum[i][i] = minSum[i - 1][i - 1] + triangle[i][i];
        }

        // state
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                int parent1 = minSum[i - 1][j];
                int parent2 = minSum[i - 1][j - 1];
                minSum[i][j] = triangle[i][j] + Math.min(parent1, parent2);
            }
        }

        // answer
        int bottom = n - 1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, minSum[bottom][i]);
        }

        return min;
    }
}


// Version 3 : Bottom Up
public class Solution {

    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        if (triangle == null || triangle.length == 0 || triangle[0].length == 0) {
            return -1;
        }

        int n = triangle.length;
        int[][] minSum = new int[n][n];

        // initialize
        for (int i = 0; i < n; i++) {
            minSum[n - 1][i] = triangle[n - 1][i];
        }

        // state
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int child1 = minSum[i + 1][j];
                int child2 = minSum[i + 1][j + 1];
                minSum[i][j] = triangle[i][j] + Math.min(child1, child2);
            }
        }

        // answer
        return minSum[0][0];
    }
}