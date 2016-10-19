/**
 * Buffer pool class
 * 
 * @author Kevin Zhang
 * @author Adam Bishop
 * @version 1.0
 *
 */
public class BufferPool {
    private int bufferCount;
    private LQueue<Buffer> freeBuffers;
    private LQueue<Buffer> LRUList;
    
    /**
     * Constructor for buffer pool
     * @param numberOfBuffers
     *                 Number of buffers in the pool
     */
    public BufferPool(int numberOfBuffers) {
        bufferCount = numberOfBuffers;
        for (int i = 0; i < bufferCount; i++) {
            Buffer dataBuffer = new Buffer();
            freeBuffers.enqueue(dataBuffer);
        }
    }
    
}
