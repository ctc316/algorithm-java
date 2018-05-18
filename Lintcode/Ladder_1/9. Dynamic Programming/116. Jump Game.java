// Version 1: DP
public class Solution {
    /*
     * @param A: A list of integers
     * @return: A boolean
     */
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }

        int len = A.length;
        boolean[] path = new boolean[len];

        // initialize
        path[0] = true;

        // state
        for (int i = 0; i < len - 1; i++) {
            if (path[i]) {
                // function
                for (int j = 1; j <= A[i] && i + j < len; j++) {
                    path[i + j] = true;
                }
            }
        }

        // answer
        return path[len - 1];
    }
}


// Version 2: Greedy
public class Solution {
    /*
     * @param A: A list of integers
     * @return: A boolean
     */
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }

        int maxJump = A[0];
        for (int i = 0; i < A.length - 1; i++) {
            if (i <= maxJump && i + A[i] > maxJump) {
                maxJump = i + A[i];
            }
        }

        return maxJump >= A.length - 1;
    }
}