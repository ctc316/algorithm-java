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


class ResultType {
    public boolean balanced;
    public int height;
    public ResultType(boolean bal, int h) {
        this.balanced = bal;
        this.height = h;
    }
}

public class Solution {
    /*
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        ResultType result = subtree(root);

        return result.balanced;
    }

    private ResultType subtree(TreeNode node) {
        if(node == null) {
            return new ResultType(true, 0);
        }

        ResultType left = subtree(node.left);
        ResultType right = subtree(node.right);

        if(!left.balanced || !right.balanced
                || Math.abs(left.height - right.height) > 1) {
            return new ResultType(false, 0);
        }

        int height = Math.max(left.height, right.height) + 1;

        return new ResultType(true, height);
    }
}