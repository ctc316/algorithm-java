class Node {
    String word;
    List<Node> neighbors;
    int distToEnd;
    Node(String word) {
        this.word = word;
        neighbors = new ArrayList<>();
        distToEnd = Integer.MAX_VALUE;
    }
}

public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> results = new ArrayList<>();

        if(start.length() != end.length() || dict.size() == 0) {
            return results;
        }

        // construct nodes
        Map<String, Node> nodeMap = new HashMap<>();
        for (String word : dict) {
            nodeMap.put(word, new Node(word));
        }
        nodeMap.put(start, new Node(start));
        nodeMap.put(end, new Node(end));

        // bfs - measure distances from end
        bfs(end, nodeMap);

        /** print all nodes and their neighbors **/
        // for (String key : nodeMap.keySet()) {
        //     Node node = nodeMap.get(key);
        //     System.out.print(node.word + "-> ");
        //     for (Node neighbor : node.neighbors) {
        //         System.out.print(neighbor.word + "(" + neighbor.distToEnd + "), ");
        //     }
        //     System.out.print("\b\n");
        // }

        // check reachable
        Node startNode = nodeMap.get(start);
        if (startNode.distToEnd == Integer.MAX_VALUE) {
            return results;
        }

        // dfs - get all shorest paths from start
        List<String> path = new ArrayList<>();
        path.add(start);
        dfs(startNode, nodeMap, path, results);

        return results;
    }

    private void bfs(String end, Map<String, Node> nodeMap) {
        Queue<Node> queue = new LinkedList<>();
        Set<String> hash = new HashSet<>();

        queue.offer(nodeMap.get(end));
        hash.add(end);

        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i< size; i++) {
                Node node = queue.poll();
                node.distToEnd = distance;
                exploreNeighbors(node, nodeMap);
                for (Node neighbor : node.neighbors) {
                    if (!hash.contains(neighbor.word)) {
                        queue.offer(neighbor);
                        hash.add(neighbor.word);
                    }
                }
            }
            distance++;
        }
    }

    private void exploreNeighbors(Node node, Map<String, Node> nodeMap) {
        String word = node.word;

        // for every word, use change 1 char within a ~ z
        for (int i = 0; i < word.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == word.charAt(i)) {
                    continue;
                }

                // change 1 char to build new word
                String newWord = word.substring(0, i) + ch + word.substring(i + 1);

                // compare to dict
                if (nodeMap.containsKey(newWord)) {
                    Node neighbor = nodeMap.get(newWord);
                    node.neighbors.add(neighbor);
                }
            }
        }
    }

    private void dfs(Node node,
                     Map<String, Node> nodeMap,
                     List<String> path,
                     List<List<String>> results) {

        if (node.distToEnd == 0) {
            results.add(new ArrayList<>(path));
            return;
        }

        int min = Integer.MAX_VALUE;
        List<Node> next = new ArrayList<>();
        for (Node neighbor : node.neighbors) {
            int dist = neighbor.distToEnd;
            if (dist == Integer.MAX_VALUE) {
                continue;
            }
            if (dist < min) {
                min = dist;
                next.clear();
                next.add(neighbor);
            } else if (dist == min) {
                next.add(neighbor);
            }
        }

        for (Node n : next) {
            path.add(n.word);
            dfs(n, nodeMap, path, results);
            path.remove(path.size() - 1);
        }
    }
}