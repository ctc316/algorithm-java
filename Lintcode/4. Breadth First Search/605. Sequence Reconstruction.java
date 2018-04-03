public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // Graph Init
        int n = org.length;
        Map<Integer, Integer> inDegrees = new HashMap<>(n);
        Map<Integer, Set<Integer>> edges = new HashMap<>(n);

        for (int num : org) {
            inDegrees.put(num, 0);
            edges.put(num, new HashSet<Integer>());
        }

        boolean foundStart = false;
        for (int[] seq : seqs) {
            if (seq.length > 0 && (seq[0] <= 0 || seq[0] > n)) {
                    return false;
            }

            if(org.length == 0 || (seq.length > 0 && seq[0] == org[0])) {
                foundStart = true;
            }

            for (int i = 1; i < seq.length; i++) {
                if (seq[i] <= 0 || seq[i] > n) {
                    return false;
                }
                if (edges.get(seq[i - 1]).add(seq[i])) {
                    inDegrees.put(seq[i], inDegrees.get(seq[i]) + 1);
                }
            }
        }

        // special cases: can't construct start number
        // [1],  [[],[]]  false
        // [],   [[],[]]  true
        if (!foundStart) {
            return false;
        }


        // BFS
        Queue<Integer> queue = new LinkedList<>();
        for (int key : inDegrees.keySet()) {
            if (inDegrees.get(key) == 0) {
                queue.offer(key);
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            // check more than 1 path
            if (queue.size() > 1) {
                return false;
            }

            // compare to org
            int num = queue.poll();
            if (num != org[index]) {
                return false;
            }
            index++;

            // get next
            for (int next : edges.get(num)) {
                inDegrees.put(next, inDegrees.get(next) - 1);
                if (inDegrees.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }

        return index == org.length;
    }
}