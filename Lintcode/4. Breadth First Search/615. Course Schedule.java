public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 2 || prerequisites == null) {
            return true;
        }

        // Graph Init
        int[] inDegree = new int[numCourses];
        List<ArrayList<Integer>> neighbors = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int[] req = prerequisites[i];
            if (req.length != 2 ||
                req[0] < 0 || req[0] >= numCourses ||
                req[1] < 0 || req[1] >= numCourses ) {
                continue;
            }

            inDegree[prerequisites[i][0]]++;
            neighbors.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        // check by BFS
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
           if (inDegree[i] == 0) {
                queue.offer(i);
                count++;
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            for (int next : neighbors.get(course)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                    count++;
                }
            }
        }

        return count == numCourses;
    }
}