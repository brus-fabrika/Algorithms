import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class BoardTest {

    @Test
    public void testCreateBoard() {
        int[][] a = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};

        Board b = new Board(a);

        assertEquals("Board size is not correct", 3, b.dimension());
    }

    @Test
    public void testInitialHamming() {
        int[][] a = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};

        Board b = new Board(a);

        assertEquals("Initial hamming is not correct", 5, b.hamming());
    }

    @Test
    public void testInitialManhattan() {
        int[][] a = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};

        Board b = new Board(a);

        assertEquals("Initial manhattan is not correct", 10, b.manhattan());
    }

    @Test
    public void testInitialIsGoal() {
        int[][] a = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};

        Board b = new Board(a);

        assertTrue("Initial goal determination is not correct", !b.isGoal());
    }

    @Test
    public void testEquals() {
        int[][] a = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};

        Board b1 = new Board(a);
        Board b2 = new Board(a);
        Board nullBoard = null;

        assertTrue("Equals on itself is not correct", b1.equals(b1));
        assertTrue("Equals on null is not correct", !b1.equals(nullBoard));
        assertTrue("Equals on other class is not correct", !b1.equals("test string"));

        assertTrue("Equals on equal board is not correct", b1.equals(b2));
    }

    @Test
    public void testToString() {
        int[][] a = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};

        Board b = new Board(a);

        String expected = "3\n 8  1  3 \n 4  0  2 \n 7  6  5 \n";

        assertEquals("Board string representation is not correct",
                expected, b.toString());
    }

}
