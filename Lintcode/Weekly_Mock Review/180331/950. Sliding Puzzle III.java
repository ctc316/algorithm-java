public class Solution {

    static final int[][] moves = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    /**
     * @param matrix: The 3*3 matrix
     * @return: The answer
     */
    public String  jigsawPuzzle(int[][] matrix) {
        String source = matrixToString(matrix);
        String target = matrixToString(new int[][]{{1,2,3},{4,5,6},{7,8,0}});

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(target)) {
                return "YES";
            }

            for (String next : getNext(curr)) {
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }

        return "NO";
    }

    public String matrixToString(int[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(state[i][j]);
            }
        }
        return sb.toString();
    }

    public List<String> getNext(String state) {
        // Move Zero
        List<String> states = new ArrayList<>();
        int zeroIndex = state.indexOf('0');
        int x = zeroIndex / 3;
        int y = zeroIndex % 3;

        for (int i = 0; i < 4; i++) {
            int x_ = x + moves[i][0];
            int y_ = y + moves[i][1];
            if (x_ < 0 || x_ >= 3 || y_ < 0 || y_ >= 3) {
                continue;
            }

            char[] chars = state.toCharArray();
            chars[x * 3 + y] = chars[x_ * 3 + y_];
            chars[x_ * 3 + y_] = '0';
            states.add(new String(chars));
        }

        return states;
    }
}