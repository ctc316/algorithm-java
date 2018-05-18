public class MyQueue {

    //push:   s1:        s2:12345
    //top:    s1:54321   s2:
    //pop:    s1:5432    s2:
    //push    s1:5432    s2:6

    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        stack2.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        if (stack1.isEmpty()) {
            if (stack2.isEmpty()) {
                return Integer.MIN_VALUE;
            }
            stack2ToStack1();
        }
        return stack1.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        if (stack1.isEmpty()) {
            if (stack2.isEmpty()) {
                return Integer.MIN_VALUE;
            }
            stack2ToStack1();
        }
        return stack1.peek();
    }

    private void stack2ToStack1() {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }
}