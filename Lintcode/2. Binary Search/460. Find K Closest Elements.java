public class Solution {
    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        if (k <= 0 || A.length == 0) {
            return new int[0];
        }

        // find target position
        int left = 0, right = A.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (target < A[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }

        // get k numbers
        int[] results = new int[k];
        int count = 0;
        while (left >= 0 && right < A.length && count < k) {
            if (Math.abs(target - A[left]) <= Math.abs(target - A[right])) {
                results[count++] = A[left--];
            } else {
                results[count++] = A[right++];
            }
        }

        while (count < k && left >= 0) {
            results[count++] = A[left--];
        }
        while (count < k && right < A.length) {
            results[count++] = A[right++];
        }

        return results;
    }
}