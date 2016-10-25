import student.TestCase;

/**
 * @author Adam Bishop
 * @author Kevin Zhang
 * @version 1.0
 */
public class QueueTest extends TestCase {
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
        assertNull(line.search(3));
        line.enqueue(one);
        line.enqueue(two);
        line.enqueue(three);
        line.enqueue(four);
        line.enqueue(five);
        line.search(3);
        line.search(1);
        line.search(1);
        assertEquals(line.frontValue().getPos(), 2);
        assertNull(line.search(9));
    }

    /**
     * Unit test for dequeue
     */
    public void testQueueDequeue() {
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
        assertNull(line.frontValue());
        line.enqueue(one);
        line.enqueue(two);
        line.enqueue(three);
        line.enqueue(four);
        line.enqueue(five);
        assertEquals(one, line.dequeue());
        assertEquals(two, line.dequeue());
        assertEquals(three, line.dequeue());
        assertEquals(four, line.dequeue());
        assertEquals(five, line.dequeue());
        assertNull(line.dequeue());
        assertEquals(0, line.length());
    }

    /**
     * Unit test for constructor
     */
    public void testQueueInitSize() {
        LQueue line = new LQueue(1);
        assertEquals(0, line.length());
    }
}
