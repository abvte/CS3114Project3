import java.io.IOException;

/**
 * Quicksort algorithm
 * 
 * @author Kevin Zhang
 * @author Adam Bishop
 * @version 1.0
 *
 */
public class Sort {
    private BufferPool pool;
    private static final int BLOCK_SIZE = 4096;
    private static final int RECORD_SIZE = 4;

    public Sort(BufferPool buffPool) throws IOException {
        pool = buffPool;
    }

    /**
     * Main method that calls other methods to sort data
     * 
     * @throws IOException
     */
    public void sortData() throws IOException {
        // Gets the right most position of the blocks of files
        this.quicksort(0, pool.getSize() - 1);
    }

    /**
     * Main quicksort algorithm
     * 
     * @param i
     *            Left position
     * @param j
     *            Right position
     * @throws IOException
     */
    public void quicksort(int i, int j) throws IOException {
        int pivotIndex = this.findPivot(i, j);
        this.swap(pivotIndex, j);
        short pivot = this.pool.getKey(j);
        // k will be the first position in the right subarray
        int k = this.partition(i, j - 1, pivot);
        this.swap(k, j);
        if ((k - i) > 1) {
            this.quicksort(i, k - 1); // Sorts left partition
        }
        if ((j - k) > 1) {
            this.quicksort(k + 1, j); // Sort right partition 
        }
        
    }
    
    /**
     * Partition method for quicksort 
     * 
     * @param left
     *            Left position 
     * @param right
     *            Right position 
     * @param pivot
     *            Pivot value
     * @return first position in right partition 
     * @throws IOException 
     */
    private int partition(int left, int right, short pivot) throws IOException {
        while (left <= right) { // Move bounds inward until they meet
            while (pool.getKey(left) < pivot) {
                left++;
            }
            while ((right >= left) && (pool.getKey(right) >= pivot)) {
                right--;
            }
            if (right > left) {
                this.swap(left, right); // Swap out-of-place values
            }
        }
        return left; //Return first position in right partition 
    }

    /**
     * @param i
     *            Left position
     * @param j
     *            Right position
     * @return pivot values
     */
    private int findPivot(int i, int j) {
        return (i + j) / 2;
    }

    /**
     * Swap method for quick sort
     * 
     * @param i
     *            Left position
     * @param j
     *            Right position
     * @throws IOException
     */
    public void swap(int i, int j) throws IOException {
        if (pool.getBufferCount() == 1) {
            this.oneBuffSwap(i, j);
            return;
        }
    }

    /**
     * Swap method when there is only one buffer
     * 
     * @param i
     *            Left position
     * @param j
     *            Right position
     * @throws IOException
     */
    public void oneBuffSwap(int i, int j) throws IOException {
        int leftBlock = (i * RECORD_SIZE) / BLOCK_SIZE;
        int leftPosition = (i * RECORD_SIZE) % BLOCK_SIZE;
        int rightBlock = (j * RECORD_SIZE) / BLOCK_SIZE;
        int rightPosition = (j * RECORD_SIZE) % BLOCK_SIZE;

        byte[] leftRecord = new byte[RECORD_SIZE];
        byte[] rightRecord = new byte[RECORD_SIZE];
        byte[] tempBuffer;

        Buffer leftBuffer = pool.getBuffer(leftBlock);
        tempBuffer = leftBuffer.getData();
        // Reads the record from that buffer and stores it into a variable
        System.arraycopy(tempBuffer, leftPosition, leftRecord, 0, RECORD_SIZE);

        Buffer rightBuffer = pool.getBuffer(rightBlock);
        tempBuffer = rightBuffer.getData();
        // Reads the record from that buffer and stores it into a variable
        System.arraycopy(tempBuffer, rightPosition, rightRecord, 0,
                RECORD_SIZE);
        // Reads the left record and places it in the temp buffer in the right
        // position
        System.arraycopy(leftRecord, 0, tempBuffer, rightPosition, RECORD_SIZE);
        rightBuffer.setData(tempBuffer);
        leftBuffer = pool.getBuffer(leftBlock);
        tempBuffer = leftBuffer.getData();
        // Reads the right record and places it in the temp buffer in the left
        // position
        System.arraycopy(rightRecord, 0, tempBuffer, leftPosition, RECORD_SIZE);
        leftBuffer.setData(tempBuffer);
    }
    
    /**
     * Flushes the buffer pool
     * 
     * @throws IOException
     */
    public void flushPool() throws IOException {
        pool.flush();
    }
}