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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * }
 */


//Version 1: Simple Inorder Traverse, Time: O(1), Space: O(n)
public class BSTIterator {

    ArrayList<TreeNode> list = new ArrayList<>();
    int curr = 0;

    /*
    * @param root: The root of binary tree.
    */public BSTIterator(TreeNode root) {
        inorderTraverse(root);
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return curr < list.size();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        return list.get(curr++);
    }

    private void inorderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        inorderTraverse(root.left);
        list.add(root);
        inorderTraverse(root.right);
    }
}



// Version 2: Use Stack to do Inorder Traverse, Time: O(1), Space: O(h)
public class BSTIterator {

    Deque<TreeNode> stack = new ArrayDeque<>();

    /*
    * @param root: The root of binary tree.
    */public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        TreeNode node = stack.pop();
        TreeNode right = node.right;
        while (right != null) {
            stack.push(right);
            right = right.left;
        }
        return node;
    }
}


// Version 3: Morris Inorder Tree Traverse, Time: O(1), Space: O(1)
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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * }
 */


public class BSTIterator {

    TreeNode curr;

    /*
    * @param root: The root of binary tree.
    */public BSTIterator(TreeNode root) {
        curr = root;
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return curr != null;
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        TreeNode ret = null;
        while (curr != null) {
            if (curr.left == null) {
                // curr has reached the most left node
                ret = curr;
                curr = curr.right;
                break;
            } else {
                // find the predecessor of curr node and link them
                TreeNode pre = curr.left;
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    // link the predecessor to curr, and move curr to its left
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    // predecessor already linked, that means the curr.left node is visited. go right
                    pre.right = null;
                    ret = curr;
                    curr = curr.right;
                    break;
                }
            }
        }

        return ret;
    }
}