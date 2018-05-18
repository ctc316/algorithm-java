public class Stack {

    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    private void moveItems() {
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
    }

    private void swapQueue() {
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    /*
     * @param x: An integer
     * @return: nothing
     */
    public void push(int x) {
        queue1.offer(x);
    }

    /*
     * @return: nothing
     */
    public void pop() {
        moveItems();
        queue1.poll();
        swapQueue();
    }

    /*
     * @return: An integer
     */
    public int top() {
        moveItems();
        int val = queue1.poll();
        swapQueue();
        queue1.offer(val);
        return val;
    }

    /*
     * @return: True if the stack is empty
     */
    public boolean isEmpty() {
        return queue1.isEmpty();
    }
}