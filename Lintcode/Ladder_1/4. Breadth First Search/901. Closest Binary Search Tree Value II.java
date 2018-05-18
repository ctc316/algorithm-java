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


// Version 1: Inorder traverse (Recursive) + Priority queue, Time: O(nlogk), Space: O(k)
public class Solution {

    class DistComp implements Comparator<Integer>{
        double target;
        public DistComp(double target){
            this.target = target;
        }
        public int compare(Integer a, Integer b){
            return Math.abs(a - target) < Math.abs(b - target) ? 1 : -1;
        }
    }

    /**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if (root == null || k < 1) {
            return new ArrayList<Integer>();
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new DistComp(target));
        inorderTraverse(root, pq, k);
        return new ArrayList<Integer>(pq);
    }

    private void inorderTraverse(TreeNode root, PriorityQueue<Integer> pq, int k) {
        if (root == null) {
            return;
        }

        inorderTraverse(root.left, pq, k);
        pq.offer(root.val);
        if (pq.size() > k) {
            pq.poll();
        }
        inorderTraverse(root.right, pq, k);
    }
}


// Version 2: Inorder traverse (Non-Recursive) + Priority queue, Time: O(nlogk), Space: O(k)
public class Solution {

    class DistComp implements Comparator<Integer>{
        double target;
        public DistComp(double target){
            this.target = target;
        }
        public int compare(Integer a, Integer b){
            return Math.abs(a - target) < Math.abs(b - target) ? 1 : -1;
        }
    }

    /**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if (root == null || k < 1) {
            return new ArrayList<Integer>();
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new DistComp(target));

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            // dive into left childs
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // do things on node
            curr = stack.pop();
            pq.offer(curr.val);
            if (pq.size() > k) {
                pq.poll();
            }

            // deal with right child
            curr = curr.right;
        }

        return new ArrayList<Integer>(pq);
    }
}


// Version 3: Inorder Traverse + Two Pointer, Time: O(n), Space: O(n)
// 先用 inorder traversal 求出中序遍历
// 找到第一个 >= target 的位置 index
// 从 index-1 和 index 出发，设置两根指针一左一右，获得最近的 k 个整数
public class Solution {

    /**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if (root == null || k < 1) {
            return new ArrayList<Integer>();
        }

        ArrayList<Integer> list = new ArrayList<>();
        inorderTraverse(root, list);

        int targetIdx = 0;
        for (int v : list) {
            if (v >= target) {
                break;
            }
            targetIdx++;
        }

        int left = targetIdx - 1, right = targetIdx;
        List<Integer> results = new ArrayList<>();
        while (results.size() < k) {
            double ldist = left < 0 ? Integer.MAX_VALUE : Math.abs(list.get(left) - target);
            double rdist = right >= list.size() ? Integer.MAX_VALUE : Math.abs(list.get(right) - target);

            if (ldist < rdist) {
                results.add(list.get(left));
                left--;
            } else {
                results.add(list.get(right));
                right++;
            }
        }

        return results;
    }

    private void inorderTraverse(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inorderTraverse(root.left, list);
        list.add(root.val);
        inorderTraverse(root.right, list);
    }
}


// Version 4 : BST + Two stack, Time: O(k + logn)，Space: O(logn)
public class Solution {

    /**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if (root == null || k < 1) {
            return new ArrayList<Integer>();
        }

        Deque<TreeNode> prevStack = new ArrayDeque<>();
        Deque<TreeNode> nextStack = new ArrayDeque<>();

        // get the closest nodes to target
        TreeNode node = root;
        while (node != null) {
            if (node.val < target) {
                prevStack.push(node);
                node = node.right;
            } else {
                nextStack.push(node);
                node = node.left;
            }
        }

        List<Integer> results = new ArrayList<Integer>();

        while (results.size() < k)  {
            double distp = prevStack.isEmpty() ? Integer.MAX_VALUE : Math.abs(prevStack.peek().val - target);
            double distn = nextStack.isEmpty() ? Integer.MAX_VALUE : Math.abs(nextStack.peek().val - target);

            if (distp < distn) {
                results.add(prevStack.peek().val);
                goPrev(prevStack);
            } else {
                results.add(nextStack.peek().val);
                goNext(nextStack);
            }
        }

        return results;
    }

    private void goNext(Deque<TreeNode> stack) {
        // push all left nodes on the right child tree
        TreeNode node = stack.pop().right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    private void goPrev(Deque<TreeNode> stack) {
        // push all right nodes on the left child tree
        TreeNode node = stack.pop().left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
    }
}