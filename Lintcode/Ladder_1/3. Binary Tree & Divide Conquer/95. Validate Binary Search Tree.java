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


// Version 1: Inorder traverse (Recursive), Time: O(n), Space: O(1)
public class Solution {

    int currVal;
    boolean isFirstNode;

    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        isFirstNode = true;
        return inorderTraverse(root);
    }

    private boolean inorderTraverse(TreeNode root) {
        if (root == null) {
            return true;
        }

        // left
        if(!inorderTraverse(root.left)) {
            return false;
        }

        // node
        if (isFirstNode) {
            isFirstNode = false;
        } else if (currVal >= root.val) {
            return false;
        }
        currVal = root.val;

        // right
        if (!inorderTraverse(root.right)) {
            return false;
        }

        return true;
    }
}


// Version 2: Divide and Conquer, Time: O(n), Space: O(1)
class ResultType {
    public int min, max;
    public boolean isValid;
    ResultType(int min, int max) {
        this.min = min;
        this.max = max;
        isValid = true;
    }
}

public class Solution {
    /*
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }

        ResultType result = subtree(root);
        return result.isValid;
    }

    public ResultType subtree(TreeNode node) {
        int min = node.val,
            max = node.val;

        if (node.left != null) {
            ResultType left = subtree(node.left);
            if (left.max >= node.val) {
                left.isValid = false;
            }
            if(!left.isValid) {
                return left;
            }
            min = left.min;
        }

        if (node.right != null) {
            ResultType right = subtree(node.right);
            if(right.min <= node.val) {
                right.isValid = false;
            }
            if(!right.isValid) {
                return right;
            }
            max = right.max;
        }

        return new ResultType(min, max);
    }
}