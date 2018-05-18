class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {

    private static final int PEOPLE = 0;
    private static final int ZOMBIE = 1;
    private static final int WALL = 2;

    private static final int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    /*
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    public int zombie(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // copy the origin grid to avoid messing it up
        final int n = grid.length;
        final int m = grid[0].length;
        int[][] copyedGrid = new int[n][m];

        for (int i = 0; i < n; i++) {
            System.arraycopy(grid[i], 0, copyedGrid[i], 0, m);
        }

        // BFS - by day
        Queue<Point> queue = new LinkedList<>();
        int people_left = 0;
        int days = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                switch(copyedGrid[i][j]) {
                    case PEOPLE:
                        people_left++;
                        break;
                    case ZOMBIE:
                        queue.offer(new Point(i, j));
                        break;
                }
            }
        }

        if (people_left == 0) {
            return 0;
        }

        while (!queue.isEmpty()) {
            days++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                for (int j = 0; j < direction.length; j++) {
                    Point bite = new Point(
                        p.x + direction[j][0],
                        p.y + direction[j][1]
                    );

                    //check if out of boundary
                    if (bite.x < 0 || bite.x >= n || bite.y < 0 || bite.y >= m) {
                        continue;
                    }

                    //check if biteable
                    if (copyedGrid[bite.x][bite.y] != PEOPLE) {
                        continue;
                    }

                    people_left--;
                    copyedGrid[bite.x][bite.y] = ZOMBIE;
                    queue.offer(bite);
                }
            }

            if (people_left == 0) {
                return days;
            }
        }

        return -1;
    }
}