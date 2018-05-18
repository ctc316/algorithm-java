public class Solution {
    /**
     * @param nums: the given k sorted arrays
     * @return: the median of the given k sorted arrays
     */
    public double findMedian(int[][] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // get n & available list of nums
        ArrayList<Integer> avlArrayIdx = new ArrayList<Integer>();
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].length > 0) {
                avlArrayIdx.add(i);
                n += nums[i].length;
            }
        }

        // zero elements
        if (n == 0) {
            return 0;
        }

        if (n % 2 == 0) {
            return (
                     findKth(nums, new int[nums.length], new ArrayList<Integer>(avlArrayIdx), n / 2) +
                     findKth(nums, new int[nums.length], new ArrayList<Integer>(avlArrayIdx), n / 2 + 1)
                   ) / 2.0;
        }
        return findKth(nums, new int[nums.length], avlArrayIdx, n / 2 + 1);
    }

    private int findKth(int[][] nums, int[] startIdx, ArrayList<Integer> avlArrayIdx, int k) {

        // find the element
        if (k == 1) {
            int min = Integer.MAX_VALUE;
            for(int i : avlArrayIdx) {
                if (nums[i][startIdx[i]] < min) {
                    min = nums[i][startIdx[i]];
                }
            }
            return min;
        }

        // cut off (k / num of arrays) elements
        // if k = 30 and the number of array = 3,
        // then we can cut off at most 10 elements from a array,
        // and at least 1 element at a time
        int cutOffPos = k / avlArrayIdx.size() - 1;
        if (cutOffPos < 0) {
            cutOffPos = 0;
        }

        // do the comparation for each array
        // find the array and the pos we should cut off
        int min = Integer.MAX_VALUE;
        int minListIdx = 0;
        int minListNextIdx = 0;
        int passNum = 0;
        for (int listIdx : avlArrayIdx) {
            int pos = startIdx[listIdx] + cutOffPos;
            if (pos >= nums[listIdx].length) {
                pos = nums[listIdx].length - 1;
            }

            int num = nums[listIdx][pos];
            if (num < min) {
                min = num;
                minListIdx = listIdx;
                minListNextIdx = pos + 1;
                passNum = minListNextIdx - startIdx[listIdx];
            }
        }

        // found the kth element
        if (passNum == k) {
            return min;
        }

        // do the cutting
        startIdx[minListIdx] = minListNextIdx;
        if (minListNextIdx == nums[minListIdx].length) {
            for (int i = 0; i < avlArrayIdx.size(); i++) {
                if (avlArrayIdx.get(i) == minListIdx) {
                     avlArrayIdx.remove(i);
                }
            }
        }

        return findKth(nums, startIdx, avlArrayIdx, k - passNum);
    }
}