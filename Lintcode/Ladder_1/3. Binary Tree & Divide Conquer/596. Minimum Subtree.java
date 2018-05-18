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

    private int minSum;
    private TreeNode theNode;

    /*
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
        if (root == null) {
            return root;
        }

        minSum = Integer.MAX_VALUE;

        recursive(root);

        return theNode;
    }

    private int recursive(TreeNode node) {
        int sum = node.val;

        if (node.left != null) {
            sum += recursive(node.left);
        }

        if (node.right != null) {
            sum += recursive(node.right);
        }

        if (sum < this.minSum) {
            this.minSum = sum;
            this.theNode = node;
        }

        return sum;
    }
}