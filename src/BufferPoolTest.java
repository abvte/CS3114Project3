import student.TestCase;
import java.io.IOException;
import java.util.Arrays;

/**
 * Unit tests for buffer pool. Also tests the functionality of the file
 * processor which writes/reads data from a file.
 * 
 * @author Adam Bishop
 * @author Kevin Zhang
 * @version 1.0
 */

public class BufferPoolTest extends TestCase {
    /**
     * Unit test to check to see if it sets dirty bits and position of a buffer
     * properly. Also checks to see if it sets data properly.
     */
    public void testSetters() {
        Buffer test = new Buffer();
        test.setDirtyBit();
        assertEquals(1, test.getDirtyBit());
        test.setPos(5);
        assertEquals(5, test.getPos());
        byte[] testData = new byte[4096];
        Arrays.fill(testData, (byte) 1); // Initializes byte array
        test.setData(testData);
        assertEquals((byte) 1, test.getData()[0]);
    }

    /**
     * Unit test to check to see if it obtains buffers properly
     * 
     * @throws IOException
     */
    public void testGetBuffer() throws IOException {
        FileProcessor fileProcess = new FileProcessor("test3.txt");
        BufferPool pool = new BufferPool(2, fileProcess);
        pool.getBuffer(1);
        assertEquals(1, pool.getListSize());
        pool.getBuffer(2);
        assertEquals(2, pool.getListSize());
        pool.getBuffer(1);
        assertEquals(2, pool.getListSize());
        pool.getBuffer(2);
        assertEquals(2, pool.getListSize());
        pool.getBuffer(3);
        assertEquals(2, pool.getListSize());
    }

    /**
     * Unit test to test if it flushes contents of the queue properly
     * 
     * @throws IOException
     */
    public void testFlush() throws IOException {
        FileProcessor fileProcess = new FileProcessor("test3.txt");
        BufferPool pool = new BufferPool(3, fileProcess);
        pool.getBuffer(0);
        pool.getBuffer(1);
        pool.getBuffer(2);
        pool.flush();
        assertEquals(0, pool.getListSize());
    }

    /**
     * Unit test to test to see if it obtains a key properly from a buffer.
     * 
     * @throws IOException
     */
    public void testGetKey() throws IOException {
        FileProcessor fileProcess = new FileProcessor("test3.txt");
        BufferPool pool = new BufferPool(3, fileProcess);
        pool.getBuffer(0);
        pool.getBuffer(1);
        pool.getBuffer(2);
        // The value 7688 comes from the first key in the file
        // "test3.txt"
        assertEquals(7688, pool.getKey(0));
    }

    /**
     * Unit test to check to see if it stores the statistics correctly for disk
     * reads, disk writes, and cache hits.
     * 
     * @throws IOException
     */
    public void testStat() throws IOException {
        FileProcessor fileProcess = new FileProcessor("test3.txt");
        BufferPool pool = new BufferPool(3, fileProcess);
        pool.getBuffer(0); // Disk read
        pool.getBuffer(1); // Disk read
        pool.getBuffer(2); // Disk read
        pool.getBuffer(0); // Cache hit
        pool.getBuffer(1); // Cache hit
        pool.getBuffer(2); // Cache hit
        int[] stats = new int[3];
        stats = pool.getStatInfo();
        // Checks for amount of cache hits
        assertEquals(3, stats[0]);
        // Checks for disk reads
        assertEquals(3, stats[1]);
        // Checks for disk writes
        assertEquals(0, stats[2]);
    }

    /**
     * This unit test checks to see if it obtains information from the file
     * processor properly
     * 
     * @throws IOException
     */
    public void testGetBytes() throws IOException {
        FileProcessor fileProcess = new FileProcessor("test5.txt");
        BufferPool pool = new BufferPool(5, fileProcess);
        Buffer test = pool.getBuffer(0);
        // Checks to see if the first byte of the file properly.
        // The number, 81, was determined by looking at the file
        // itself.
        assertEquals(81, test.getData()[0]);
    }

    /**
     * This unit test checks to see if the file processor sets the correct
     * information in the file itself.
     * 
     * @throws IOException
     */
    public void testSetBytes() throws IOException {
        FileProcessor fileProcess = new FileProcessor("test4.txt");
        BufferPool pool = new BufferPool(4, fileProcess);
        byte[] testData = new byte[4096];
        Arrays.fill(testData, (byte) 5); // Initializes byte array with the
                                         // number 5
        Buffer test = pool.getBuffer(0);
        test.setData(testData);
        test.setDirtyBit();
        pool.flush();
        fileProcess.getBytes(testData, 0);
        assertEquals(5, testData[0]); // Checks first 2 bytes
        assertEquals(5, testData[1]);

    }
}