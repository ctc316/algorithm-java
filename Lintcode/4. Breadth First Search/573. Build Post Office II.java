class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {

    private static final int EMPTY = 0;
    private static final int HOUSE = 1;
    private static final int WALL = 2;

    private static final int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    private int n, m;
    private int[][] grid;

    /*
     * @param grid: a 2D grid
     * @return: An integer
     */
    public int shortestDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // set grid
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;

        // BFS from houses to mark all viable houses on empty
        List<Point> houses = getPointList(HOUSE);
        int[][] distanceSum = new int[n][m];
        int[][] visitedTimes = new int[n][m];
        for (Point house : houses) {
            bfsFromHouse(house, distanceSum, visitedTimes);
        }

        // find shortest
        int shortest = Integer.MAX_VALUE;
        List<Point> emptys = getPointList(EMPTY);
        for (Point e : emptys) {
            if (visitedTimes[e.x][e.y] == houses.size()) {
                shortest = Math.min(shortest, distanceSum[e.x][e.y]);
            }
        }

        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }

    private void bfsFromHouse(Point house, int[][] distanceSum, int[][] visitedTimes){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] hash = new boolean[n][m];

        queue.offer(house);
        hash[house.x][house.y] = true;

        int distance = 0;
        while (!queue.isEmpty()) {
            distance++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                for (int j = 0; j < direction.length; j++) {
                    Point moved = new Point(
                        p.x + direction[j][0],
                        p.y + direction[j][1]
                    );

                    //check boundary
                    if(moved.x < 0 || moved.x >= n || moved.y < 0 || moved.y >= m) {
                        continue;
                    }

                    // check if accessible
                    if(grid[moved.x][moved.y] != EMPTY || hash[moved.x][moved.y] == true) {
                        continue;
                    }

                    distanceSum[moved.x][moved.y] += distance;
                    visitedTimes[moved.x][moved.y]++;
                    hash[moved.x][moved.y] = true;
                    queue.offer(moved);
                }
            }
        }
    }

    private List<Point> getPointList(int type) {
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == type) {
                    list.add(new Point(i,j));
                }
            }
        }
        return list;
    }
}