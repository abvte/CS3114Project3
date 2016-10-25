import student.TestCase;

/**
 * @author Adam Bishop
 * @author Kevin Zhang
 * @version 1.0
 */
public class QuicksortTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization.
     */
    public void setUp() {
        // Nothing Here
    }

    /**
     * Get code coverage of the class declaration.
     */
    public void testQInit() {
        Quicksort tree = new Quicksort();
        assertNotNull(tree);
    }
}
