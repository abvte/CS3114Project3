import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

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
    private LQueue lruList;
    private int cacheHit;
    private int diskWrite;
    private int diskRead;
    
    private Buffer tempBuffer = new Buffer();
    
    private byte[] data;
    /**
     * File processor
     */
    protected FileProcessor fileData;

    /**
     * Constructor for buffer pool
     * 
     * @param numberOfBuffers
     *            Number of buffers in the pool
     * @param file
     *            File processor object
     * @throws IOException
     */
    public BufferPool(int numberOfBuffers, FileProcessor file)
            throws IOException {
        bufferCount = numberOfBuffers;
        fileData = file;
        fileBlocks = fileData.calculateBlocks();
        diskSize = fileBlocks * (BLOCK_SIZE / RECORD_SIZE);
        lruList = new LQueue();
        cacheHit = 0;
        diskWrite = 0;
        diskRead = 0;
        data = new byte[4096];
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
        //data = new byte[4096];
        buffer = lruList.search(blockPosition);
        if (buffer == null) {
            buffer = new Buffer();
            // Means that it wasn't found
            if (lruList.length() < bufferCount) {
                buffer.setData(fileData.getBytes(data, blockPosition));
                //System.out.println(Arrays.toString(data));
                diskRead++;
                //buffer.setData(data);
                buffer.setPos(blockPosition);
                lruList.enqueue(buffer);
                return buffer;
            }
            else {
                tempBuffer = lruList.dequeue();
                if (tempBuffer.getDirtyBit() == 1) {
                    // Writes the remove buffer to the disk if the dirty bit is
                    // 1
                    fileData.insertBytes(tempBuffer.getData(),
                            tempBuffer.getPos());
                    diskWrite++;
                }
                buffer.setData(fileData.getBytes(data, blockPosition));
                //System.out.println(Arrays.toString(data));
                diskRead++;
                //buffer.setData(data);
                buffer.setPos(blockPosition);
                lruList.enqueue(buffer);
                return buffer;
            }
        }
        else {
            cacheHit++;
            return buffer;
        }
    }

    /**
     * Obtains the key from a buffer according to the position
     * 
     * @param pos
     *            Position of the element in the file
     * @return key value
     * @throws IOException
     */
    public short getKey(int pos) throws IOException {
        int blockNumber = (pos * RECORD_SIZE) / BLOCK_SIZE;
        int blockPos = (pos * RECORD_SIZE) % BLOCK_SIZE;
        // Obtains the buffer
        ByteBuffer temp = ByteBuffer
                .wrap(this.getBuffer(blockNumber).getData());
        short key = temp.getShort(blockPos); // Retrieves key from that position
        return key;
    }

    /**
     * Flush method that clears the queue. Writes back to file if the dirty bit
     * of the buffer is 1
     * 
     * @throws IOException
     */
    public void flush() throws IOException {
        Buffer buffer = lruList.dequeue();
        while (buffer != null) {
            if (buffer.getDirtyBit() == 1) {
                fileData.insertBytes(buffer.getData(), buffer.getPos());
                diskWrite++;
            }
            buffer = lruList.dequeue();
        }
        data = null;

    }

    /**
     * Getter method that returns an array of statistics to be written to the
     * stat file. stat[0] = cache hits, stat[1] = disk reads, stat[2] = disk
     * writes
     * 
     * @return stat array
     */
    public int[] getStatInfo() {
        int[] stat = new int[3];
        stat[0] = cacheHit;
        stat[1] = diskRead;
        stat[2] = diskWrite;
        return stat;
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
