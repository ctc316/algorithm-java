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


// Version 1: Inorder Traverse (Recursive), Time: O(k), Space: O(1)
public class Solution {
    int remain, kth;

    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        remain = k;
        inorderTraverse(root);
        return remain == 0 ? kth : Integer.MIN_VALUE;
    }

    private void inorderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        inorderTraverse(root.left);
        if (remain == 0) {
            return;
        }

        kth = root.val;
        remain--;
        if (remain == 0) {
            return;
        }

        inorderTraverse(root.right);
    }
}


// Version 2: Inorder Traverse (Iterate), Time: O(k), Space: O(1)
public class Solution {
    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        // push left nodes
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        // traverse nodes and check right tree.
        while (!stack.isEmpty()) {
            curr = stack.pop();
            k--;
            if (k == 0) {
                return curr.val;
            }

            if (curr.right != null) {
                curr = curr.right;
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
            }
        }

        return Integer.MIN_VALUE;
    }
}