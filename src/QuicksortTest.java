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
}
