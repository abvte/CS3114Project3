import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
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
        Quicksort.main(null);
    }

    /**
     * Unit test to check to see if it finds elements in the queue properly.
     */
    public void testQueueSearch() {
        Buffer one = new Buffer();
        one.setPos(1);
        Buffer two = new Buffer();
        two.setPos(2);
        Buffer three = new Buffer();
        three.setPos(3);
        Buffer four = new Buffer();
        four.setPos(4);
        Buffer five = new Buffer();
        five.setPos(5);
        LQueue line = new LQueue();
        line.enqueue(one);
        line.enqueue(two);
        line.enqueue(three);
        line.enqueue(four);
        line.enqueue(five);
        line.search(3);
        line.search(1);
        line.search(1);
        assertEquals(line.frontValue().getPos(), 2);
    }

}
