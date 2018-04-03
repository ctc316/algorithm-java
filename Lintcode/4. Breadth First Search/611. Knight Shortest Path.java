/**
 * Definition for a point.
 * public class Point {
 *     publoc int x, y;
 *     public Point() { x = 0; y = 0; }
 *     public Point(int a, int b) { x = a; y = b; }
 * }
 */


public class Solution {

    private static final int[][] direction = {{1,2}, {1,-2}, {-1,2}, {-1,-2},
                                        {2,1}, {2,-1}, {-2,1}, {-2,-1}};

    /*
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // copy the origin grid to avoid messing it up
        final int n = grid.length;
        final int m = grid[0].length;
        boolean[][] copyedGrid = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            System.arraycopy(grid[i], 0, copyedGrid[i], 0, m);
        }

        // BFS
        Queue<Point> queue = new LinkedList<>();
        queue.offer(source);

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();

                // check for destination
                if(p.x == destination.x && p.y == destination.y) {
                    return steps;
                }

                // add next possible steps
                for (int j = 0; j < direction.length; j++) {
                    Point next = new Point(
                        p.x + direction[j][0],
                        p.y + direction[j][1]
                    );

                    // check if out of boundary
                    if (next.x < 0 || next.x >= n ||
                        next.y < 0 || next.y >= m) {
                        continue;
                    }

                    // check for barrier
                    if (copyedGrid[next.x][next.y] == true) {
                        continue;
                    }

                    // mark not accessible to avoid infinite loop
                    copyedGrid[next.x][next.y] = true;

                    queue.offer(next);
                }
            }
            steps++;
        }

        return -1;
    }
}