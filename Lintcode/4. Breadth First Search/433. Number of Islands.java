class Coordinate {
    int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {

    private static final int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};

    /*
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // copy the origin grid to avoid messing it up
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] copyedGrid = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            System.arraycopy(grid[i], 0, copyedGrid[i], 0, m);
        }

        // find islands
        int islands = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(copyedGrid[i][j] == true) {
                    System.out.println("("+ i + "," + j + ")");
                    islands += 1;
                    markByBFS(copyedGrid, new Coordinate(i, j));
                }
           }
        }

        return islands;
    }


    private void markByBFS(boolean[][] grid, Coordinate coord) {
        Queue<Coordinate> queue = new LinkedList<>();

        queue.offer(coord);

        while(!queue.isEmpty()) {
            Coordinate c = queue.poll();
            grid[c.x][c.y] = false;

            for (int i = 0; i < direction.length; i++) {
                Coordinate moved = new Coordinate(
                    c.x + direction[i][0],
                    c.y + direction[i][1]
                );

                if (inBound(grid, moved) && grid[moved.x][moved.y] == true) {
                    queue.offer(moved);
                }
            }
        }
    }

    private boolean inBound(boolean[][] grid, Coordinate coord) {
        int n = grid.length;
        int m = grid[0].length;

        return coord.x > -1 && coord.x < n && coord.y > -1 && coord.y < m;
    }
}