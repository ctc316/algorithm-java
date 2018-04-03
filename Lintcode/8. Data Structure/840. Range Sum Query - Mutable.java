public class NumArray {

    private int[] BITree;   // 0 ~ n
    private int[] nums;     // 0 ~ n - 1
    private int n;

    /**
     * @return: nothing
     */
    public NumArray(int[] nums) {
        this.n = nums.length;
        this.nums = new int[n];
        this.BITree = new int[n + 1];

        for (int i = 0; i < n; i++) {
            update(i, nums[i]);
        }
    }

    public void update(int i, int val) {
        if (i < 0 || i >= n) {
            return;
        }

        // Update nums
        int diff = val - nums[i];
        nums[i] = val;

        // Update BITree
        i++;
        while(i <= n) {
           BITree[i] += diff;
           i += i & (-i);
        }
    }

    public int sumRange(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            return Integer.MIN_VALUE;
        }
        return getSum(j) - getSum(i - 1);
    }

    private int getSum(int i) {
        int sum = 0;
        i++;
        while (i > 0) {
            sum += BITree[i];
            i -= i & (-i);
        }
        return sum;
    }
}