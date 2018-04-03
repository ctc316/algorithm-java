public class Solution {
    /*
     * @param A: an integer array sorted in ascending order
     * @param target: An integer
     * @return: an integer
     */
    public int closestNumber(int[] A, int target) {
        int left = 0;
        int right = A.length - 1;
        int mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (target == A[mid]) {
                return mid;
            } else if (target > A[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if(Math.abs(A[left] - target) < Math.abs(A[right] - target)) {
            return left;
        }
        return right;
    }
}