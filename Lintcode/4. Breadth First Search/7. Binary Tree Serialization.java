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

    private static final String SEPERATOR = ",";
    private static final String EMPTY_NODE = "#";

    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        // BFS
        StringBuilder result = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node == null) {
                result.append(EMPTY_NODE + SEPERATOR);
            } else {
                result.append(node.val + SEPERATOR);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        // remove redundant "#" and "," at the end
        for (int i = result.length() - 1; i >= 0; i -= 1) {
            String s = result.substring(i, i + 1);
            if (s.equals(SEPERATOR) || s.equals(EMPTY_NODE)) {
                result.deleteCharAt(i);
            } else {
                break;
            }
        }

        System.out.println(result.toString());
        return result.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }

        String[] strs = data.split(SEPERATOR);
        if (strs.length == 0) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList();
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        TreeNode parent = root;
        boolean assignLeft = true;

        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].equals(EMPTY_NODE)) {
                TreeNode node = new TreeNode(Integer.parseInt(strs[i]));
                queue.offer(node);
                if(assignLeft) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }
            if (!assignLeft) {
                parent = queue.poll();
            }
            assignLeft = !assignLeft;
        }

        return root;
    }
}