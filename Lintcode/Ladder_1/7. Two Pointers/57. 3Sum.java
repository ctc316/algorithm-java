public class Solution {
    /*
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] numbers) {
        List<List<Integer>> results = new ArrayList<>();

        if (numbers == null || numbers.length < 3) {
           return results; 
        }

        Arrays.sort(numbers);

        int len = numbers.length;
        for (int i = 0; i < len; i++) {
            // skip duplicate numbers
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }

            twoSum(numbers, i + 1, len - 1, -numbers[i], results);
        }

        return results;
    }

    private void twoSum(int[] numbers,
                        int left,
                        int right,
                        int target,
                        List<List<Integer>> results) {

        boolean moveLeft = false, moveRight = false;

        while (left < right) {
            int remain = target - numbers[left] - numbers[right];
            if (remain > 0) {
                moveLeft = true;
            } else if (remain < 0) {
                moveRight = true;
            } else {
                List<Integer> ret = new ArrayList<>();
                ret.add(-target);
                ret.add(numbers[left]);
                ret.add(numbers[right]);
                results.add(ret);
                moveLeft = moveRight = true;
            }

            // skip duplicate numbers
            if (moveLeft) {
                moveLeft = false;
                left++;
                while (left < right && numbers[left] == numbers[left - 1]) {
                    left++;
                }
            }
            if (moveRight) {
                moveRight = false;
                right--;
                while (left < right && numbers[right] == numbers[right + 1]) {
                    right--;
                }
            }
        }
    }
}