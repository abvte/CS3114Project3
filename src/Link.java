/**
 * Link class for queue
 * 
 * @author Kevin Zhang
 * @author Adam Bishop
 * @version 1.0
 *
 */
/**
 * @author Kevin Zhang
 *
 */
class Link { // Singly linked list node class
    private Buffer data; // Value for this node
    private Link n; // Point to next node in list

    /**
     * Constructor
     * 
     * @param block
     *            Data element
     * @param in
     *            Next node
     */
    Link(Buffer block, Link in) {
        data = block;
        n = in;
    }

    /**
     * Constructor
     * 
     * @param inn
     *            Next node
     */
    Link(Link inn) {
        data = null;
        n = inn;
    }

    /**
     * Getter for data element
     * 
     * @return data
     */
    Buffer element() {
        return data;
    }

    /**
     * Setter for data element
     * 
     * @param it
     *            Data value
     * @return Set data value
     */
    Buffer setElement(Buffer it) {
        return data = it;
    }

    /**
     * Getter for next node 
     * @return next node 
     */
    Link next() {
        return n;
    } 

    /**
     * Setter for next node 
     * @param inn
     * @return
     */
    Link setNext(Link inn) {
        return n = inn;
    } 
}