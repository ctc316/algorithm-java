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

    private List<String> ansList;

    /*
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        ansList = new ArrayList<String>();

        if (root == null) {
            return ansList;
        }

        traverse(root, "");

        return ansList;
    }

    public void traverse(TreeNode node, String str) {
        if(node == null) {
            return;
        }

        if(str.equals("")) {
            str += node.val;
        } else {
            str += "->" + node.val;
        }

        if(node.left == null && node.right == null) {
            ansList.add(str);
            return;
        }

        traverse(node.left, str);
        traverse(node.right, str);
    }
}