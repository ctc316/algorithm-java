public class Solution {
    /*
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int left = 0;
        int right = A.length - 1;
        int mid = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (target == A[mid]) {
                return mid;
            } else if (A[mid] > A[right]) {
                //mid at pivoted part
                if (A[mid] > target  && target > A[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                //mid at sequential part
                if (A[mid] < target && target <= A[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}