public class LRUCache {

    private class Node {
        Node next;
        Node prev;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = this.prev = null;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> map = new HashMap<Integer, Node>();

    //dummy nodes
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    /*
    * @param capacity: An integer
    */public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        //remove node
        node.prev.next = node.next;
        node.next.prev = node.prev;

        // move the recent node to head.next
        moveToHead(node);

        return node.value; 
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // already have this pair; otherwise, get(key) will move the node to head
        if (get(key) != -1) {
            map.get(key).value = value;
            return;
        }

        if (map.size() == capacity) {
            map.remove(tail.prev.key);
            tail.prev = tail.prev.prev;
            tail.prev.next = tail;
        }

        Node newNode = new Node(key, value);
        map.put(key, newNode);
        moveToHead(newNode);
    }

    private void moveToHead(Node node) {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }
}