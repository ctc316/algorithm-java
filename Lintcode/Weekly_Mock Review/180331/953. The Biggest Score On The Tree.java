public class Solution {

    int max;

    /**
     * @param x: The vertex of edge
     * @param y: The another vertex of edge
     * @param cost: The cost of edge
     * @param profit: The profit of vertex
     * @return: Return the max score
     */
    public int getMaxScore(int[] x, int[] y, int[] cost, int[] profit) {
        if (x == null || x.length == 0 || y == null || y.length != x.length || cost == null || cost.length != x.length || profit == null || profit.length != x.length + 1) {
            return 0;
        }

        Map<Integer, ArrayList<Integer>> nodeEdges = new HashMap<>();
        for (int i = 0; i < x.length; i++) {
            if (!nodeEdges.containsKey(x[i])) {
                ArrayList<Integer> posList = new ArrayList<>();
                posList.add(i);
                nodeEdges.put(x[i], posList);
            } else {
                nodeEdges.get(x[i]).add(i);
            }
        }

        max = Integer.MIN_VALUE;
        dfs (x, y, cost, profit, nodeEdges, 0, 0);

        return max;
    }

    private void dfs(int[] x, int[] y, int[] cost, int[] profit,
                Map<Integer, ArrayList<Integer>> nodeEdges,
                int currNode, int total) {

        total += profit[currNode];

        boolean isLeaf = true;
        if (nodeEdges.containsKey(currNode)) {
            ArrayList<Integer> edges = nodeEdges.get(currNode);
            for (int i : edges) {
                if (x[i] == currNode) {
                    isLeaf = false;
                    dfs (x, y, cost, profit, nodeEdges, y[i], total - cost[i]);
                }
            }
        }

        if (isLeaf & total > max) {
             max = total;
        }
    }
}