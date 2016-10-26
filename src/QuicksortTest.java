import java.io.IOException;

import student.TestCase;

/**
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
            String[] binaryArgs = new String[] { 
                "testQSortBinary.txt", 
                "1",
                "testQSortStat.txt" };
            qs.main(binaryArgs);
            String[] asciiArgs = new String[] { 
                "testQSortAscii.txt", 
                "1",
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
            String[] binaryArgs = new String[] { 
                "test10Blocks4Buffers.txt",
                "4", 
                "test10Blocks4BuffersStats.txt" };
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
            String[] binaryArgs = new String[] { 
                "test10Blocks1Buffer.txt", 
                "1",
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
            String[] binaryArgs = new String[] { 
                "test100Blocks10Buffers.txt", 
                "10",
                "test100Blocks10BufferStats.txt" };
            qs.main(binaryArgs);
            CheckFile cf = new CheckFile();
            assertTrue(cf.checkFile("test100Blocks10Buffers.txt"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    /**
//     * Test the sorting for binary with 1000 blocks and 10 buffers
//     * 
//     * @throws Exception
//     *             IOException
//     */
//    @SuppressWarnings("static-access")
//    public void testThouBlocksTenBuffers() throws Exception {
//        FileGenerator fg = new FileGenerator();
//        try {
//            fg.generateFile(
//                    new String[] { "-b", "test1000Blocks10Buffers.txt", "1000" });
//            Quicksort qs = new Quicksort();
//            String[] binaryArgs = new String[] { 
//                "test1000Blocks10Buffers.txt", 
//                "10",
//                "test1000Blocks10BufferStats.txt" };
//            qs.main(binaryArgs);
//            CheckFile cf = new CheckFile();
//            assertTrue(cf.checkFile("test1000Blocks10Buffers.txt"));
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    /**
//     * Test the sorting for ascii with 1000 blocks and 10 buffers
//     * 
//     * @throws Exception
//     *             IOException
//     */
//    @SuppressWarnings("static-access")
//    public void testThouBlocksTenBuffersAscii() throws Exception {
//        FileGenerator fg = new FileGenerator();
//        try {
//            fg.generateFile(
//                    new String[] { "-b", "test1000Blocks10BuffersAsc.txt", "1000" });
//            Quicksort qs = new Quicksort();
//            String[] binaryArgs = new String[] { 
//                "test1000Blocks10BuffersAsc.txt", 
//                "10",
//                "test1000Blocks10BufferStatsAsc.txt" };
//            qs.main(binaryArgs);
//            CheckFile cf = new CheckFile();
//            assertTrue(cf.checkFile("test1000Blocks10BuffersAsc.txt"));
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
