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
     */
    public void sortData() {
        // Gets the right most position of the blocks of files
        this.quicksort(0, pool.getSize() - 1);
    }

    /**
     * Main quicksort algorithm
     * 
     * @param i
     *        Left position 
     * @param j
     *        Right position 
     */
    public void quicksort(int i, int j) {
        int pivotIndex = this.findPivot(i, j);
        this.swap(pivotIndex, j);
    }
    
    /**
     * @param i
     *        Left position 
     * @param j
     *        Right position 
     * @return pivot values
     */
    private int findPivot(int i, int j) {
        return (i + j) / 2;
    }
    
    /**
     * Swap method for quick sort  
     * 
     * @param i
     *         Left position 
     * @param j
     *         Right position 
     */
    public void swap(int i, int j) {
        if (pool.getBufferCount() == 1) {
            this.oneBuffSwap(i, j);
            return;
        }
    }
    
    /**
     * Swap method when there is only one buffer
     * 
     * @param i
     *        Left position
     * @param j
     *        Right position
     */
    public void oneBuffSwap(int i, int j) {
        int leftBlock = (i * RECORD_SIZE) / BLOCK_SIZE;
        int leftPosition = (i * RECORD_SIZE) % BLOCK_SIZE;
        int rightBlock = (j * RECORD_SIZE) / BLOCK_SIZE;
        int rightPosition = (j * RECORD_SIZE) % BLOCK_SIZE;
        
        byte[] leftRecord = new byte[RECORD_SIZE];
        byte[] rightRecord = new byte[RECORD_SIZE];
        byte[] tempBuffer;
        
        
    }
}