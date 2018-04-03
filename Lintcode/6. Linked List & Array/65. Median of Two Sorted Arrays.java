public class Solution {
    private int lenA;
    private int lenB;

    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // set and check length
        lenA = A.length;
        lenB = B.length;
        if (lenA == 0 && lenB == 0) {
            return Integer.MIN_VALUE;
        }

        // find k (and k-1 if even), recursion
        int len = lenA + lenB;
        int k = len / 2 + 1;
        if (len % 2 == 0) {
            return (findKthVal(A, B, 0, 0, k - 1) + findKthVal(A, B, 0, 0, k)) / 2;
        } else {
            return findKthVal(A, B, 0, 0, k);
        }
    }

    private double findKthVal(int[] A, int[] B, int startA, int startB, int k) {
        // check empty array
        if(startA >= lenA) {
            return B[startB + k - 1];
        }
        if(startB >= lenB) {
            return A[startA + k - 1];
        }

        // Exit
        if (k == 1) {
            return Math.min(A[startA], B[startB]);
        }

        int halfK = k/2;
        int indexA = startA + halfK - 1;
        int indexB = startB + halfK - 1;

        // check enough numbers, if not enough, remove from the other array
        int valA = indexA < lenA ? A[indexA] : Integer.MAX_VALUE;
        int valB = indexB < lenB ? B[indexB] : Integer.MAX_VALUE;

        // compare
        if (valA > valB) {
            return findKthVal(A, B, startA, indexB + 1, k - halfK);
        } else {
            return findKthVal(A, B, indexA + 1, startB, k - halfK);
        }
    }
}