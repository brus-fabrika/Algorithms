import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class BoardTest {

    @Test
    public void testCreateBoard() {
        int[][] a = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };

        Board b = new Board(a);

        assertEquals("Board size is not correct", 3, b.dimension());
    }

    @Test
    public void testInitialHamming() {
        int[][] a = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };

        Board b = new Board(a);

        assertEquals("Initial hamming is not correct", 5, b.hamming());
    }

    @Test
    public void testInitialManhattan() {
        int[][] a = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };

        Board b = new Board(a);

        assertEquals("Initial manhattan is not correct", 10, b.manhattan());
    }

    @Test
    public void testInitialIsGoal() {
        // int[][] a = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        //
        // Board b = new Board(a);

        // assertTrue("Initial goal determination is not correct", !b.isGoal());
        fail("Not implemented");
    }

    @Test
    public void testNeighborsCenter() {
        int[][] a = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };

        int[][] north = { { 8, 0, 3 }, { 4, 1, 2 }, { 7, 6, 5 } };
        int[][] south = { { 8, 1, 3 }, { 4, 6, 2 }, { 7, 0, 5 } };
        int[][] west = { { 8, 1, 3 }, { 0, 4, 2 }, { 7, 6, 5 } };
        int[][] east = { { 8, 1, 3 }, { 4, 2, 0 }, { 7, 6, 5 } };

        Board[] expectedBoards =
            { new Board(north),
              new Board(south),
              new Board(west),
              new Board(east) };

        int[] results = new int[expectedBoards.length];

        Board b1 = new Board(a);
        for (Board currentN : b1.neighbors()) {
            for (int i = 0; i < expectedBoards.length; ++i) {
                if (currentN.equals(expectedBoards[i]))
                    ++results[i];
            }
        }

        int wrongResults = 0;
        for (int i = 0; i < results.length; ++i) {
            if (results[i] != 1) {
                StdOut.println(results[i]);
                StdOut.println(expectedBoards[i].toString());
                ++wrongResults;
            }
        }
        assertTrue("Lazha", wrongResults == 0);
    }

    @Test
    public void testEquals() {
        int[][] a = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };

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
        int[][] a = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };

        Board b = new Board(a);

        String expected = "3\n 8  1  3 \n 4  0  2 \n 7  6  5 \n";

        assertEquals("Board string representation is not correct", expected,
                b.toString());
    }

}
