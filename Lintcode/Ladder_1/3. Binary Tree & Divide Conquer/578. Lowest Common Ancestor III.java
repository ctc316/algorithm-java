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

    private TreeNode lca;

    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        lca = null;
        traverse(root, A, B);
        return lca;
    }

    private TreeNode traverse(TreeNode node, TreeNode A, TreeNode B){
        if(node == null) {
            return node;
        }

        TreeNode left = traverse(node.left, A, B);
        TreeNode right = traverse(node.right, A, B);

        if((A == node || A == left || A == right) &&
            (B == node || B == left || B == right)) {
                lca = node;
                return null;
        }

        if(node == A || node == B) {
            return node;
        }

        if(left != null) {
            return left;
        }

        if(right != null) {
            return right;
        }

        return null;
    }
}