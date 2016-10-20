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
        freeBuffers = new LQueue<Buffer>();
        LRUList = new LQueue<Buffer>();
        for (int i = 0; i < bufferCount; i++) {
            Buffer dataBuffer = new Buffer();
            freeBuffers.enqueue(dataBuffer);
        }
    }
    
    /**
     * Obtains size of the free buffer list
     * @return Size of the free buffer list
     */
    public int getFreeBufferListSize() {
        return freeBuffers.length();
    }
    
    /**
     * Obtains a free buffer from the free buffer list
     * @return Free buffer from list
     */
    public Buffer getFreeBuffer() {
        return freeBuffers.dequeue();
    }
}
