import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import student.TestCase;

/**
 * Unit test cases for the sort class. Also tests the StatFileGenerator class.
 * 
 * @author Adam Bishop
 * @author Kevin Zhang
 * @version 1.0
 */
public class QuicksortTest extends TestCase {

    /**
     * Get code coverage of the class declaration.
     */
    public void testQInit() {
        Quicksort qs = new Quicksort();
        assertNotNull(qs);
    }

    /**
     * Test Errors
     */
    @SuppressWarnings("static-access")
    public void testQError() {
        Quicksort qs = new Quicksort();
        qs.main(new String[] { "a", "b", "c" });
        String output = systemOut().getHistory();
        assertTrue(output.contains("Invalid Arguments"));
    }

    /**
     * Unit test to test to see if it obtains a key properly from a buffer.
     * 
     * @throws IOException
     */
    public void testGetKey() throws IOException {
        FileProcessor fileProcess = new FileProcessor("test3.txt");
        BufferPool pool = new BufferPool(3, fileProcess);
        Sort sorter = new Sort(pool);
        // The value 7688 comes from the first key in the file
        // "test3.txt"
        assertEquals(7688, sorter.getKey(0));
        // The value 15693 comes from the second key in the file
        // "test3.txt"
        assertEquals(15693, sorter.getKey(1));
        // The value 13476 comes from the tenth key in the file
        // "test3.txt"
        assertEquals(13476, sorter.getKey(10));
    }

    /**
     * Test the sorting for ascii and binary
     * 
     * @throws Exception
     *             IOException
     */
    @SuppressWarnings("static-access")
    public void testQSort() throws Exception {
        FileGenerator fg = new FileGenerator();
        try {
            fg.generateFile(new String[] { "-b", "testQSortBinary.txt", "1" });
            fg.generateFile(new String[] { "-a", "testQSortAscii.txt", "1" });
            fg.generateFile(new String[] { "-z", "testQSortFail.txt", "1" });
            Quicksort qs = new Quicksort();
            String[] binaryArgs = new String[] { "testQSortBinary.txt", "1",
                    "testQSortStat.txt" };
            qs.main(binaryArgs);
            String[] asciiArgs = new String[] { "testQSortAscii.txt", "1",
                    "testQSortStat.txt" };
            qs.main(asciiArgs);
            CheckFile cf = new CheckFile();
            assertTrue(cf.checkFile("testQSortBinary.txt"));
            assertTrue(cf.checkFile("testQSortAscii.txt"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test the sorting for binary with 10 blocks and 4 buffers
     * 
     * @throws Exception
     *             IOException
     */
    @SuppressWarnings("static-access")
    public void testTenBlocksFourBuffers() throws Exception {
        FileGenerator fg = new FileGenerator();
        try {
            fg.generateFile(
                    new String[] { "-b", "test10Blocks4Buffers.txt", "10" });
            Quicksort qs = new Quicksort();
            String[] binaryArgs = new String[] { "test10Blocks4Buffers.txt",
                    "4", "test10Blocks4BuffersStats.txt" };
            qs.main(binaryArgs);
            CheckFile cf = new CheckFile();
            assertTrue(cf.checkFile("test10Blocks4Buffers.txt"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test the sorting for binary with 10 blocks and 1 buffer
     * 
     * @throws Exception
     *             IOException
     */
    @SuppressWarnings("static-access")
    public void testTenBlocksOneBuffers() throws Exception {
        FileGenerator fg = new FileGenerator();
        try {
            fg.generateFile(
                    new String[] { "-b", "test10Blocks1Buffer.txt", "10" });
            Quicksort qs = new Quicksort();
            String[] binaryArgs = new String[] { "test10Blocks1Buffer.txt", "1",
                    "test10Blocks1BufferStats.txt" };
            qs.main(binaryArgs);
            CheckFile cf = new CheckFile();
            assertTrue(cf.checkFile("test10Blocks1Buffer.txt"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test the sorting for binary with 100 blocks and 10 buffers
     * 
     * @throws Exception
     *             IOException
     */
    @SuppressWarnings("static-access")
    public void testHundBlocksTenBuffers() throws Exception {
        FileGenerator fg = new FileGenerator();
        try {
            fg.generateFile(
                    new String[] { "-b", "test100Blocks10Buffers.txt", "100" });
            Quicksort qs = new Quicksort();
            String[] binaryArgs = new String[] { "test100Blocks10Buffers.txt",
                    "10", "test100Blocks10BufferStats.txt" };
            qs.main(binaryArgs);
            CheckFile cf = new CheckFile();
            assertTrue(cf.checkFile("test100Blocks10Buffers.txt"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unit test to test the stat file generator class and makes sure that it
     * prints the proper information to the output files
     * 
     * @throws IOException
     */
    public void testStatFile() throws IOException {
        String fileName = "statTest.txt";
        StatFileGenerator stat = new StatFileGenerator(fileName);
        stat.writeStats(5, 10, 5, 50, "test1.txt");
        stat.writeStats(10, 20, 30, 40, "test2.txt");
        FileReader file = new FileReader("statTest.txt");
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = null;
        String[] statList = new String[10];
        int counter = 0;
        line = bufferedReader.readLine();
        while ((line != null && counter < 10)) {
            statList[counter] = line;
            counter++;
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        assertEquals("Sort on test1.txt", statList[0]);
        assertEquals("Cache Hits: 5", statList[1]);
        assertEquals("Disk Reads: 10", statList[2]);
        assertEquals("Disk Writes: 5", statList[3]);
        assertEquals("Time is 50", statList[4]);
        assertEquals("Sort on test2.txt", statList[5]);
        assertEquals("Cache Hits: 10", statList[6]);
        assertEquals("Disk Reads: 20", statList[7]);
        assertEquals("Disk Writes: 30", statList[8]);
        assertEquals("Time is 40", statList[9]);
    }

    /**
     * Tests findPivot function
     * 
     * @throws IOException
     */
    public void testSortPivot() throws IOException {
        Sort s = new Sort(null);
        assertEquals(s.findPivot(0, 10), 5);
    }

}
