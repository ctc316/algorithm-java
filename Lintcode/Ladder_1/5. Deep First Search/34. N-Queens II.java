// Version 1: DFS (Recursive)
public class Solution {

    private int count;

    /**
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    public int totalNQueens(int n) {
        // row: col
        int[] queen = new int[n];

        count = 0;

        // DFS
        dfs(n, 0, queen);

        return count;
    }

    private void dfs(int n, int row, int[] queen) {
        if (row == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(n, queen, row, i)) {
                queen[row] = i;
                dfs(n, row + 1, queen);
            }
        }
    }

    private boolean isValid(int n, int[] queen, int row, int col) {
        // check previous Queens
        // X            X
        //    X   X   X
        //       cur

        for (int i = 0; i < row; i++) {
            if (queen[i] == col) {
                return false;
            }
            if ((row - i) == Math.abs(col-queen[i])) {
                return false;
            }
        }
        return true;
    }
}


// Version 2: DFS (Non-Recursive)
public class Solution {
    /**
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    public int totalNQueens(int n) {
        // row: col
        int[] queen = new int[n];

        int count = 0;

        // DFS
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        int row = 0, col;

        while (!stack.isEmpty()) {
            col = stack.poll();
            // find a valid position
            while (col < n) {
                if (isValid(n, queen, row, col)) {
                    break;
                }
                col++;
            }

            if (col < n) {
                // found a valid pos, save state, and go next row
                stack.push(col + 1);
                queen[row] = col;
                row++;

                if (row == n) {
                    // found a combination
                    count++;
                    row--;
                } else {
                    //next row, start from 0
                    stack.push(0);
                }
            } else {
                row--;
            }
        }

        return count;
    }

    private boolean isValid(int n, int[] queen, int row, int col) {
        // check previous Queens
        // X            X
        //    X   X   X
        //       cur

        for (int i = 0; i < row; i++) {
            if (queen[i] == col) {
                return false;
            }
            if ((row - i) == Math.abs(col-queen[i])) {
                return false;
            }
        }
        return true;
    }
}
