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

    private int maxDepth;

    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        maxDepth = 0;

        traverse(root, 0);

        return maxDepth;
    }

    private void traverse(TreeNode node, int depth) {
        if(node == null) {
            return;
        }

        depth += 1;
        if(depth > maxDepth) {
            maxDepth = depth;
        }

        traverse(node.left, depth);
        traverse(node.right, depth);
    }
}