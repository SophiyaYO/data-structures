package ds;

public class BasicQueue<X> {
    private X[] data;
    private int front;
    private int end;

    public BasicQueue() {
        this(1000);
    }

    public BasicQueue(int size) {
        this.front = -1;
        this.end = -1;
        data = (X[]) new Object[size];
    }

    public int size() {
        //if the queue is empty return 0
        if (front == -1 && end == -1) {
            return 0;
        }

        //otherwise we add one to get the inclusive subtraction value rather than excluding
        else {
            return end - front + 1;
        }
    }

    public void enQueue(X item) {
        //first see if the queue is full
        //(end +1)-> mark the new data that will come in;
        if ((end + 1) % data.length == front) {
            throw new IllegalStateException("The Queue is full!");
        }
        //otherwise check to see if any items have been added to the queue yet
        else if (size() == 0) {
            front++;
            end++;
            data[end] = item;
        }
        //otherwise add the item to the end of the queue
        else {
            end++;
            data[end] = item;
        }
    }

    public X deQueue() {
        X item = null;

        //if the queue is empty we can't dequeue anything
        if (size() == 0) {
            throw new IllegalStateException("Can't dequeue because the queue is empty!");
        }

        //otherwise if this is the last item on the queue, the queue needs to get reset to empty
        else if (front == end) {
            item = data[front];
            data[front] = null; //to point to the garbage collector that this is for trash
            front = -1;
            end = -1;
        }
        //otherwise grab the front of the deque, return it and adjust the front pointer
        else {
            item = data[front];
            data[front] = null;
            front++;
        }

        return item;
    }

    public boolean contains(X item) {
        boolean containsItem = false;

        for (X datum : data) {
            if (datum.equals(item)) {
                containsItem = true;
                break;
            }
        }

        return containsItem;
    }
}
