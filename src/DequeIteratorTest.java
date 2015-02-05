import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class DequeIteratorTest {

    private Deque<String> d;

    @Before
    public void setUp() throws Exception {
        d = new Deque<>();
    }

    @Test
    public void testBasicSequenceAddFirst() {
        String[] testDataRecieved = new String[10];

        d.addFirst("AA");
        d.addFirst("BB");
        d.addFirst("CC");

        Iterator<String> it = d.iterator();

        int i = 0;
        while (it.hasNext()) {
            testDataRecieved[i++] = it.next();
        }

        assertTrue("Not 3 elements were read", i == 3);
        assertTrue("Incorrect element was read", "CC".equals(testDataRecieved[0]));
        assertTrue("Incorrect element was read", "BB".equals(testDataRecieved[1]));
        assertTrue("Incorrect element was read", "AA".equals(testDataRecieved[2]));
    }

    @Test
    public void testBasicSequenceAddLast() {
        String[] testDataRecieved = new String[10];

        d.addLast("AA");
        d.addLast("BB");
        d.addLast("CC");

        Iterator<String> it = d.iterator();

        int i = 0;
        while (it.hasNext()) {
            testDataRecieved[i++] = it.next();
        }

        assertTrue("Not 3 elements were read", i == 3);
        assertTrue("Incorrect element was read", "AA".equals(testDataRecieved[0]));
        assertTrue("Incorrect element was read", "BB".equals(testDataRecieved[1]));
        assertTrue("Incorrect element was read", "CC".equals(testDataRecieved[2]));
    }

    @Test
    public void testBasicSequenceRemoveFirst() {
        String[] testDataRecieved = new String[10];

        d.addLast("AA");
        d.addLast("BB");
        d.addLast("CC");
        d.addLast("DD");
        d.addLast("EE");

        d.removeFirst();
        d.removeFirst();

        Iterator<String> it = d.iterator();

        int i = 0;
        while (it.hasNext()) {
            testDataRecieved[i++] = it.next();
        }

        assertTrue("Not 3 elements were read", i == 3);
        assertTrue("Incorrect element was read", "CC".equals(testDataRecieved[0]));
        assertTrue("Incorrect element was read", "DD".equals(testDataRecieved[1]));
        assertTrue("Incorrect element was read", "EE".equals(testDataRecieved[2]));
    }

    @Test
    public void testBasicSequenceRemoveLast() {
        String[] testDataRecieved = new String[10];

        d.addLast("AA");
        d.addLast("BB");
        d.addLast("CC");
        d.addLast("DD");
        d.addLast("EE");

        d.removeLast();
        d.removeLast();

        Iterator<String> it = d.iterator();

        int i = 0;
        while (it.hasNext()) {
            testDataRecieved[i++] = it.next();
        }

        assertTrue("Not 3 elements were read", i == 3);
        assertTrue("Incorrect element was read", "AA".equals(testDataRecieved[0]));
        assertTrue("Incorrect element was read", "BB".equals(testDataRecieved[1]));
        assertTrue("Incorrect element was read", "CC".equals(testDataRecieved[2]));
    }

    @Test
    public void testHasNextEmpty() {
        Iterator<String> it = d.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void testHasNextOneAdd() {
        d.addFirst("AA");
        Iterator<String> it = d.iterator();
        assertTrue(it.hasNext());
        assertTrue("AA".equals(it.next()));
        assertFalse(it.hasNext());
    }

    @Test
    public void testHasNextTwoAdd() {
        d.addFirst("AA");
        d.addFirst("BB");
        Iterator<String> it = d.iterator();

        assertTrue(it.hasNext());
        assertTrue("BB".equals(it.next()));
        assertTrue(it.hasNext());
        assertTrue("AA".equals(it.next()));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void nextEmptyTest() {
        Iterator<String> it = d.iterator();
        it.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        Iterator<String> it = d.iterator();
        it.remove();
    }

}
