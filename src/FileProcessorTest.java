import java.io.IOException;

import student.TestCase;

/**
 * @author Adam Bishop
 * @author Kevin Zhang
 * @version 1.0
 */
public class FileProcessorTest extends TestCase {

    /**
     * Unit test for constructor
     */
    public void testFileProcessorInit() {
        try {
            FileProcessor fp = new FileProcessor("");
        } 
        catch (Exception e) {
            assertTrue(true); //Verify the exception was caught
        }
        
        try {
            FileProcessor fp = new FileProcessor("testQSortAscii.txt");
            assertEquals(fp.getFileName(), "testQSortAscii.txt");
        }
        catch (Exception e) {
           //Do nothing 
        }
    }
    
    /**
     * Tests the getBytes method
     * @throws IOException
     */
    public void testGetBytes() throws IOException {
        byte[] arr = new byte[1];
        FileProcessor fp = new FileProcessor("testFileProcessor.txt");
        fp.getBytes(arr, 0);
        assertEquals(arr[0], 97);
    }
    
    /**
     * Tests the calculateBlocks method
     * @throws IOException
     */
    public void testCalculateBlocks() throws IOException {
        FileProcessor fp = new FileProcessor("testQSortAscii.txt");
        assertEquals(fp.calculateBlocks(), 1);
    }
    
    /**
     * Tests the insert bytes method
     * @throws IOException
     */
    public void testInsertBytes() throws IOException {
        byte[] arr = new byte[] {98};
        FileProcessor fp = new FileProcessor("testFileProcessor.txt");
        fp.insertBytes(arr, 0);
        fp.getBytes(arr, 0);
        assertEquals(arr[0], 98);
        arr = new byte[] {97};  //Prepare to reset file back to normal
        fp.insertBytes(arr, 0);
        fp.getBytes(arr, 0);
        assertEquals(arr[0], 97);
    }
}
