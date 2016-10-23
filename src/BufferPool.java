import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Buffer pool class
 * 
 * @author Kevin Zhang
 * @author Adam Bishop
 * @version 1.0
 *
 */
public class BufferPool {
    private static final int BLOCK_SIZE = 4096;
    private static final int RECORD_SIZE = 4;
    private int bufferCount;
    private int fileBlocks;
    private int diskSize;
    private LQueue LRUList;
    /**
     * File processor
     */
    protected FileProcessor fileData;

    /**
     * Constructor for buffer pool
     * 
     * @param numberOfBuffers
     *            Number of buffers in the pool
     * @throws IOException
     */
    public BufferPool(int numberOfBuffers, FileProcessor file)
            throws IOException {
        bufferCount = numberOfBuffers;
        fileData = file;
        fileBlocks = fileData.calculateBlocks();
        diskSize = fileBlocks * (BLOCK_SIZE / RECORD_SIZE);
        LRUList = new LQueue();
    }

    /**
     * Finds the buffer in the queue
     * 
     * @param blockPosition
     *            Position of the block
     * @return Buffer with the block position
     * @throws IOException 
     */
    public Buffer getBuffer(int blockPosition) throws IOException {
        Buffer buffer = new Buffer();
        buffer = LRUList.search(blockPosition);
        if (buffer == null) {
            // Means that it wasn't found
            if (LRUList.length() < bufferCount) {
                buffer = new Buffer();
                byte[] data = new byte[4096];
                data = fileData.getBytes(data, blockPosition);
                buffer.setData(data);
                LRUList.enqueue(buffer);
                return buffer;
            }
            else {
                System.out.println("Dequeue from buffer and possibly write to the file");
                return buffer;
            }
        }
        else {
            return buffer;
        }
    }
    
    /**
     * Obtains the key from a buffer according to the position
     * @param pos
     *                Position of the element in the file 
     * @return key value
     * @throws IOException 
     */
    public short getKey(int pos) throws IOException {
        int blockNumber = (pos * RECORD_SIZE)/BLOCK_SIZE;
        int blockPos = (pos * RECORD_SIZE) % BLOCK_SIZE;
        // Obtains the buffer
        ByteBuffer temp = ByteBuffer.wrap(this.getBuffer(blockNumber).getData()); 
        short key = temp.getShort(blockPos); // Retrieves key from that position 
        return key;
    }
    
    /**
     * Flush method that clears the queue. Writes back 
     * to file if the dirty bit of the buffer is 1
     * @throws IOException 
     */
    public void flush() throws IOException {
        Buffer buffer = LRUList.dequeue();
        while (buffer != null) {
            if (buffer.getDirtyBit() == 1) {
                fileData.insertBytes(buffer.getData(), buffer.getPos());
            }
            buffer = LRUList.dequeue();
        }
    }

    /**
     * Getter for LRUList
     * 
     * @return LRUList
     */
    public LQueue getLRUList() {
        return LRUList;
    }

    /**
     * Getter for the amount of buffers
     * 
     * @return Number of buffers
     */
    public int getBufferCount() {
        return bufferCount;
    }

    /**
     * Obtains the number of file blocks
     * 
     * @return Number of file blocks
     */
    public int getFileBlock() {
        return fileBlocks;
    }

    /**
     * Getter for disk size
     * 
     * @return diskSize
     */
    public int getSize() {
        return diskSize;
    }

}
