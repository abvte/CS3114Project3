/**
 * Link class for queue
 * 
 * @author Kevin Zhang
 * @author Adam Bishop
 * @version 1.0
 *
 */
class Link {
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
    public Link(Buffer block, Link in) {
        data = block;
        n = in;
    }

    /**
     * Constructor
     * 
     * @param inn
     *            Next node
     */
    public Link(Link inn) {
        data = null;
        n = inn;
    }

    /**
     * Getter for data element
     * 
     * @return data
     */
    public Buffer element() {
        return data;
    }

    /**
     * Setter for data element
     * 
     * @param it
     *            Data value
     * @return Set data value
     */
    public Buffer setElement(Buffer it) {
        data = it;
        return data;
    }

    /**
     * Getter for next node
     * 
     * @return next node
     */
    public Link next() {
        return n;
    }

    /**
     * Setter for next node
     * 
     * @param inn
     *            Next node to be set
     * @return next node
     */
    public Link setNext(Link inn) {
        n = inn;
        return n;
    }
}