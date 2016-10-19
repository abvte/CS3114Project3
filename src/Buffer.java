/**
 * Buffer class to hold contents from the file.
 * 
 * @author Kevin Zhang
 * @author Adam Bishop
 * @version 1.0
 *
 */
public class Buffer {
    private static final int BUFFER_SIZE = 4096;
    private byte[] data;
    private int dirtyBit;
    
    /**
     * Constructor 
     */
    public Buffer() {
        data = new byte[BUFFER_SIZE];
        dirtyBit = 0;
    }
    
    /**
     * Getter for data in the buffer
     * @return Data in the buffer
     */
    public byte[] getData() {
        return data;
    }
}
