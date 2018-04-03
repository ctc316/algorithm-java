// Version 1: Queue
public class MovingAverage {

    Queue<Integer> queue;
    int size;
    long sum;

    /*
    * @param size: An integer
    */public MovingAverage(int size) {
        this.queue = new LinkedList<>();
        this.size = size;
        this.sum = 0;
    }

    /*
     * @param val: An integer
     * @return:
     */
    public double next(int val) {
        sum += val;
        queue.offer(val);
        if (queue.size() > size) {
            sum -= queue.poll();
        }
        return (double) sum / queue.size();
    }
}


// Version 2: ArrayList
public class MovingAverage {

    List<Integer> window;
    int size;
    long sum;

    /*
    * @param size: An integer
    */public MovingAverage(int size) {
        this.window = new ArrayList<>(size);
        this.size = size;
        this.sum = 0;
    }

    /*
     * @param val: An integer
     * @return:
     */
    public double next(int val) {
        sum += val;
        window.add(val);
        if (window.size() > size) {
            sum -= window.get(0);
            window.remove(0);
        }
        return (double) sum / window.size();
    }
}


// Version 3: Array
public class MovingAverage {

    int[] window;
    int count;
    long sum;

    /*
    * @param size: An integer
    */public MovingAverage(int size) {
        this.window = new int[size];
        this.count = 0;
        this.sum = 0;
    }

    /*
     * @param val: An integer
     * @return:
     */
    public double next(int val) {
        sum += val;
        count++;
        int idx = count - 1;
        int size = count;
        if (count > window.length) {
            idx %= window.length;
            sum -= window[idx];
            size = window.length;
        }
        window[idx] = val;
        return (double) sum / size;
    }
}