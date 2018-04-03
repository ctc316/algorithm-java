/**
 * public class SVNRepo {
 *     public static boolean isBadVersion(int k);
 * }
 * you can use SVNRepo.isBadVersion(k) to judge whether
 * the kth code version is bad or not.
*/

public class Solution {
    /*
     * @param n: An integer
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        if(n < 2) {
            return n;
        }

        int left = 0;
        int right = n;
        int mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (SVNRepo.isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if(SVNRepo.isBadVersion(left)) {
            return left;
        }

        if(SVNRepo.isBadVersion(right)) {
            return right;
        }

        return 0;
    }
}