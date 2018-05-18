//Version 1: DFS + Memorization : Time: O(n^2), Space: O(n^2)
public class Solution {

    Map<Integer, List<Integer>>[] records;

    /*
     * @param nums: a set of distinct positive integers
     * @return: the largest subset
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<Integer>();
        }

        records = new HashMap[nums.length];

        Arrays.sort(nums);

        List<Integer> longest = findLongest (nums, 0, 1);
        longest.remove(0);

        return longest;

    }

    private List<Integer> findLongest (int[] nums, int startIdx, int divisor) {
        if(startIdx == nums.length) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(divisor);
            return list;
        }

        Map<Integer, List<Integer>> map = records[startIdx];
        if (map == null) {
            map = new HashMap<>();
            records[startIdx] = map;
        } else if (map.containsKey(divisor)) {
            return map.get(divisor);
        }

        List<Integer> longest = new ArrayList<>();
        for (int i = startIdx; i < nums.length ; i++) {
            int n = nums[i];
            if(n % divisor == 0) {
                List<Integer> list = findLongest(nums, i + 1, n);
                if (list.size() > longest.size()) {
                    longest = list;
                }
            }
        }

        List<Integer> newList = new ArrayList<>();
        newList.add(divisor);
        newList.addAll(longest);
        map.put(divisor, newList);

        return newList;
    }
}



// Version 2 : DP
public class Solution {
    /*
     * @param nums: a set of distinct positive integers
     * @return: the largest subset
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<Integer>();
        }

        Arrays.sort(nums);

        // Initialize, count divisors and record last divisor
        //    [1, 2, 3, 4, 6, 8]
        // -> [1, 1 ,1, 1, 1, 1], [0, 1, 2, 3, 4, 5]
        // -> [1, 2, 2, 3, 3, 4], [0, 0, 0, 1, 1, 3]

        int len = nums.length;
        int[] records = new int[len];
        int[] lastDivs = new int[len];
        for (int i = 0; i < len; i++) {
            records[i] = 1;
            lastDivs[i] = i;

            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && records[i] < records[j] + 1) {
                    records[i] = records[j] + 1;
                    lastDivs[i] = j;
                }
            }
        }

        // get the ans list
        List<Integer> ans = new ArrayList<Integer>();

        // find max
        int max = 0;
        int max_idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (records[i] > max) {
                max = records[i];
                max_idx = i;
            }
        }

        // trace back
        ans.add(nums[max_idx]);
        while (max_idx != lastDivs[max_idx]) {
            max_idx = lastDivs[max_idx];
            ans.add(nums[max_idx]);
        }

        Collections.reverse(ans);
        return ans;
    }
}
