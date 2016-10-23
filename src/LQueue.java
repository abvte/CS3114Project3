/**
 * Linked queue implementation
 * 
 * @author Kevin Zhang
 * @author Adam Bishop
 * @version 1.0
 *
 */
class LQueue {
    private Link front; // Pointer to front queue node
    private Link rear; // Pointer to rear queue node
    private int size; // Number of elements in queue

    /**
     * Constructor
     */
    LQueue() {
        init();
    }

    /**
     * Constructor
     * 
     * @param size
     *            Size of the queue
     */
    LQueue(int size) {
        init();
    } // Ignore size

    /**
     * Initialization of queue
     */
    void init() {
        rear = new Link(null);
        front = rear;
        size = 0;
    }

    /**
     * Search method for queue
     * 
     * @param blockNumber
     *            Position number of the buffer
     * @return Buffer element if found. Null otherwise
     * 
     */
    public Buffer search(int blockNumber) {
        if (size == 0) { // Empty list
            return null;
        }

        Link temp = front; // Grabs the front of the queue
        while (temp != rear) {
            if (temp.next().element().getPos() == blockNumber) {
                Buffer tempBuffer = temp.next().element();
                if (temp.next() != rear) {
                    // Removes the element in the queue and puts it at the end
                    temp.setNext(temp.next().next()); 
                    size--;
                    this.enqueue(tempBuffer);
                    return tempBuffer;
                }
                else { // element is found at the end 
                    return tempBuffer; // Already at the end 
                }
            }
            temp = temp.next();
        }
        return null;
    }

    /**
     * Puts element in the rear of the queue
     * 
     * @param it
     *            Buffer to enqueue
     * @return Always returns true
     */
    public boolean enqueue(Buffer it) {
        rear.setNext(new Link(it, null));
        rear = rear.next();
        size++;
        return true;
    }

    /**
     * Removes and return element from front
     * 
     * @return Last element of the queue
     */
    public Buffer dequeue() {
        if (size == 0) {
            return null;
        }
        Buffer it = front.next().element(); // Store the value
        front.setNext(front.next().next()); // Advance front
        if (front.next() == null) {
            rear = front; // Last element
        }
        size--;
        return it; // Return element
    }

    /**
     * Returns front element
     * 
     * @return Front element of the queue
     */
    public Buffer frontValue() {
        if (size == 0) {
            return null;
        }
        return front.next().element();
    }

    /**
     * Returns the queue size
     * 
     * @return Size of the queue
     */
    public int length() {
        return size;
    }

}