public class Solution {
    /*
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        if(A == null || A.length == 0) {
            return -1;
        }

        int left = 0;
        int right = A.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right + 1) / 2;
            if (mid > 0 && A[mid] < A[mid - 1]) {
                right = mid - 1;
            }
            else if (mid + 1 < A.length - 1 && A[mid] < A[mid + 1]) {
                left = mid + 1;
            }
            else {
                return mid;
            }
       }

       return mid;
    }
}