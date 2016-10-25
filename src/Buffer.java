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
    private int blockPosition;

    /**
     * Constructor
     */
    public Buffer() {
        data = new byte[BUFFER_SIZE];
        dirtyBit = 0;
        blockPosition = 0;
    }

    /**
     * Getter for data in the buffer
     * 
     * @return Data in the buffer
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Setter for buffer
     * 
     * @param space
     *            Bytes to be set to data
     */
    public void setData(byte[] space) {
        data = space;
    }

    /**
     * Dirty bit setter
     * 
     */
    public void setDirtyBit() {
        dirtyBit = 1;
    }

    /**
     * Dirty bit getter
     * 
     * @return dirtyBit
     */
    public int getDirtyBit() {
        return dirtyBit;
    }

    /**
     * Position setter
     * 
     * @param position
     *            Position to be set
     */
    public void setPos(int position) {
        blockPosition = position;
    }

    /**
     * Position getter
     * 
     * @return blockPosition
     */
    public int getPos() {
        return blockPosition;
    }
}
