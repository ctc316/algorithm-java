class Pair {
    int sum;
    int index;
    Pair(int s, int i) {
        sum = s;
        index = i;
    }
}

public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
           return result;
        }

        if (nums.length == 1) {
            return new int[]{0, 0};
        }

        //   [-3, 1, 1,-3,5]
        // [0,-3,-2,-1,-4,1]
        Pair[] prefixSums = new Pair[nums.length + 1];
        prefixSums[0] = new Pair(0, 0);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSums[i + 1] = new Pair(sum, i + 1);
        }

        // Sort to find the closest
        Arrays.sort(prefixSums, new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
               return a.sum - b.sum;
            }
        });

        //   [6,-4,-8, 3, 1,7]
        // [0,6, 2,-6,-3,-2,5] -> [-6,-3,-2,0,2,5,6]
        int closest = Integer.MAX_VALUE;
        for (int i = 1; i < prefixSums.length; i++) {
            int subSum = prefixSums[i].sum - prefixSums[i - 1].sum;
            if (subSum < closest) {
                closest = subSum;

                int i1 = prefixSums[i - 1].index;
                int i2 = prefixSums[i].index;
                if (i1 > i2) {
                    result[0] = i2;
                    result[1] = i1 - 1;
                } else {
                    result[0] = i1;
                    result[1] = i2 - 1;
                }
            }
        }

        return result;
    }
}