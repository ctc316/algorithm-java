/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {

    private float maxAvg;
    private TreeNode theNode;

    /*
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        if (root == null) {
            return root;
        }

        maxAvg = Integer.MIN_VALUE;

        recursive(root);

        return theNode;
    }

    // @return value [sum, num]
    private int[] recursive(TreeNode node) {

        int sum = node.val;
        int num = 1;

        if (node.left != null) {
            int[] left = recursive(node.left);
            sum += left[0];
            num += left[1];
        }

        if (node.right != null) {
            int[] right = recursive(node.right);
            sum += right[0];
            num += right[1];
        }

        float avg = (float) sum / num;
        if (avg > this.maxAvg) {
            this.maxAvg = avg;
            this.theNode = node;
        }

        return new int[] {sum, num};
    }
}