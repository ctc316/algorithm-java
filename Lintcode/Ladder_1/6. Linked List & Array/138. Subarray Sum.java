public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        List<Integer> result = new ArrayList<>();

        if (nums.length == 0) {
            return result;
        }

        //   [1,2,3,4]
        // [0,1,3,6,10]

        // find a pair of same prefix sum
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, -1);

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (prefixSumMap.containsKey(sum)) {
                result.add(prefixSumMap.get(sum) + 1);
                result.add(i);
                break;
            }
            prefixSumMap.put(sum, i);
        }

        return result;
    }
}