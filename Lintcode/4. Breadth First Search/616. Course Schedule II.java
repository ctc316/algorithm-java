public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        ArrayList<Integer>[] edges = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];

        // init edge lists
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }

        // count in-degree and assign edges
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]] += 1 ;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        // get first layer nodes
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // find order
        int[] order = new int[numCourses];
        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[count] = course;
            count++;

            // handle the following courses
            ArrayList<Integer> e = edges[course];
            int size = e.size();
            for (int i = 0; i < size; i++) {
                int fc = e.get(i);
                inDegree[fc] -= 1;
                if (inDegree[fc] == 0) {
                    queue.offer(fc);
                }
            }
        }

        if (count == numCourses) {
            return order;
        }
        return new int[]{};
    }
}