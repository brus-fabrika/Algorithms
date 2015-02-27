import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void testManhattanGoal10x10() {
        int[][] a = { {  1,  2,  3,  4,  5,  6,  7,  8,  9, 10 },
                      { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 },
                      { 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 },
                      { 31, 32, 33, 34, 35, 36, 37, 38, 39, 40 },
                      { 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 },
                      { 51, 52, 53, 54, 55, 56, 57, 58, 59, 60 },
                      { 61, 62, 63, 64, 65, 66, 67, 68, 69, 70 },
                      { 71, 72, 73, 74, 75, 76, 77, 78, 79, 80 },
                      { 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 },
                      { 91, 92, 93, 94, 95, 96, 97, 98, 99,  0 } };

        Board b = new Board(a);

        assertEquals("10x10 manhattan is not correct", 0, b.manhattan());
    }

    @Test
    public void testManhattan3x3_1() {
        int[][] a = { { 1, 2, 3 },
                      { 0, 7, 6 },
                      { 5, 4, 8 } };

        Board b = new Board(a);

        assertEquals("3x3_1 manhattan is not correct", 7, b.manhattan());
    }

    @Test
    public void testManhattan3x3_2() {
        int[][] a = { { 5, 1, 8 },
                      { 2, 7, 3 },
                      { 4, 0, 6 } };

        Board b = new Board(a);

        assertEquals("3x3_2 manhattan is not correct", 13, b.manhattan());
    }

    @Test
    public void testManhattan2x2_unsolvable() {
        int[][] a = { { 1, 0 },
                      { 2, 3 } };

        Board b = new Board(a);

        assertEquals("2x2_unsolvable manhattan is not correct", 3, b.manhattan());
    }

    @Test
    public void testInitialManhattan() {
        int[][] a = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };

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

    @Test
    public void testTwin22() {
        int[][] a  = { { 1, 0 }, { 2, 3 } };
        int[][] a_twin = { { 1, 0 }, { 3, 2 } };

        Board b = new Board(a);
        Board b_twin = new Board(a_twin);

        assertTrue("twin() doesn't return a twin", b.twin().equals(b_twin));
    }

    @Test
    public void testTwin33() {
        int[][] a  = { { 7, 0, 2 }, { 4, 6, 1 }, { 5, 3, 8 } };
        int[][] a_twin = { { 7, 0, 2 }, { 6, 4, 1 }, { 5, 3, 8 } };

        Board b = new Board(a);
        Board b_twin = new Board(a_twin);

        assertTrue("twin() doesn't return a twin", b.twin().equals(b_twin));
    }

}
