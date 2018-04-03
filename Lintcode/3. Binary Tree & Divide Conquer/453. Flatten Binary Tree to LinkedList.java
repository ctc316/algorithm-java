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


// Version 1: Traverse
public class Solution {
    private TreeNode lastNode;

    /*
     * @param root: a TreeNode, the root of the binary tree
     * @return:
     */
    public void flatten(TreeNode root) {
         if(root == null) {
             return;
         }

         if(lastNode != null) {
             lastNode.left = null;
             lastNode.right = root;
         }

         lastNode = root;
         TreeNode right = root.right;
         flatten(root.left);
         flatten(right);
    }
}


// Version 2: Divide & Conquer
public class Solution {
    /*
     * @param root: a TreeNode, the root of the binary tree
     * @return:
     */
    public void flatten(TreeNode root) {
         subtree(root);
    }

    private TreeNode subtree(TreeNode node) {
        if(node == null) {
            return node;
        }

        TreeNode leftLast = subtree(node.left);
        TreeNode rightLast = subtree(node.right);

        if(leftLast != null) {
           leftLast.right = node.right;
           node.right = node.left;
           node.left = null;
        }

        if(rightLast != null) {
            return rightLast;
        }

        if(leftLast != null) {
            return leftLast;
        }

        return node;
    }
}