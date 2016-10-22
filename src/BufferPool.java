import java.io.IOException;

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
     * @param numberOfBuffers
     *                 Number of buffers in the pool
     * @throws IOException 
     */
    public BufferPool(int numberOfBuffers, FileProcessor file) throws IOException {
        bufferCount = numberOfBuffers;
        fileData = file;
        fileBlocks = fileData.calculateBlocks();
        diskSize = fileBlocks * (BLOCK_SIZE/RECORD_SIZE);
        LRUList = new LQueue();
    }
        
    /**
     * Getter for LRUList
     * @return LRUList
     */
    public LQueue getLRUList() {
        return LRUList;
    }
    
    /**
     * Getter for the amount of buffers
     * @return Number of buffers
     */
    public int getBufferCount() {
        return bufferCount;
    }
    
    /**
     * Obtains the number of file blocks
     * @return Number of file blocks
     */
    public int getFileBlock() {
        return fileBlocks;
    }
    
    /**
     * Getter for disk size 
     * @return diskSize
     */
    public int getSize() {
        return diskSize;
    }
    
    
}
