import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


public class DequeTest {

    private Deque<Integer> d;

    @Before
    public void setUp() throws Exception {
        d = new Deque<>();
    }

    @Test
    public void isEmptyOnCreateTest() {
        assertTrue(d.isEmpty());
    }

    @Test
    public void isEmptyOneElementTest() {
        d.addFirst(1);
        assertFalse(d.isEmpty());
    }

    @Test
    public void isEmptyOneAddDeleteTest() {
        d.addFirst(1);
        d.removeFirst();
        assertTrue(d.isEmpty());
    }

    @Test
    public void isEmptyManyAddDeleteTest() {
        d.addFirst(1);
        d.removeFirst();
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        assertTrue(d.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void addFirstNullTest() {
        d.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void addLastNullTest() {
        d.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstEmptyNullTest() {
        d.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastEmptyNullTest() {
        d.removeLast();
    }
}
