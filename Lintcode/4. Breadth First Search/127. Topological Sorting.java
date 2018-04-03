/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

public class Solution {
    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if (graph == null || graph.size() == 0) {
            return new ArrayList<DirectedGraphNode>();
        }

        // check for inDegrees
        Map<DirectedGraphNode, Integer> inDegree = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            for(DirectedGraphNode neighbor : node.neighbors) {
                if (inDegree.containsKey(neighbor)) {
                    inDegree.put(neighbor, inDegree.get(neighbor) + 1);
                } else {
                    inDegree.put(neighbor, 1);
                }
            }
        }

        // BFS
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        ArrayList<DirectedGraphNode> results = new ArrayList<>();

        for (DirectedGraphNode node : graph) {
            if (!inDegree.containsKey(node)) {
                queue.offer(node);
                results.add(node);
            }
        }

        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            for (DirectedGraphNode neighbor : node.neighbors) {
                int deg = inDegree.get(neighbor) - 1;
                inDegree.put(neighbor, deg);
                if (deg == 0) {
                    queue.offer(neighbor);
                    results.add(neighbor);
                }
            }
        }

        return results;
    }
}