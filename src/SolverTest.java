import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SolverTest {

    private Board getBoard(final String filename) {
        StdOut.println(filename + ".txt");

        // read in the board specified in the filename
        In in = new In(filename + ".txt");
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        Board initial = new Board(tiles);
        return initial;
    }

    @Test
    public void testPuzzlePuzzle33impossible() {
        Board board = getBoard("puzzle3x3-unsolvable");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", !solver.isSolvable());
        assertEquals("Wrong moves count", -1, solver.moves());
    }

    @Test
    public void testPuzzle00() {
        Board board = getBoard("puzzle00");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 0, solver.moves());
    }

    @Test
    public void testPuzzle01() {
        Board board = getBoard("puzzle01");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 1, solver.moves());
    }

    @Test
    public void testPuzzle02() {
        Board board = getBoard("puzzle02");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 2, solver.moves());
    }

    @Test
    public void testPuzzle03() {
        Board board = getBoard("puzzle03");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 3, solver.moves());
    }

    @Test
    public void testPuzzle04() {
        Board board = getBoard("puzzle04");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 4, solver.moves());
    }

    @Test
    public void testPuzzle05() {
        Board board = getBoard("puzzle05");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 5, solver.moves());
    }

    @Test
    public void testPuzzle06() {
        Board board = getBoard("puzzle06");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 6, solver.moves());
    }

    @Test
    public void testPuzzle07() {
        Board board = getBoard("puzzle07");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 7, solver.moves());
    }

    @Test
    public void testPuzzle08() {
        Board board = getBoard("puzzle08");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 8, solver.moves());
    }

    @Test
    public void testPuzzle09() {
        Board board = getBoard("puzzle09");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 9, solver.moves());
    }

    @Test
    public void testPuzzle10() {
        Board board = getBoard("puzzle10");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 10, solver.moves());
    }

    @Test
    public void testPuzzle45() {
        Board board = getBoard("puzzle45");
        Solver solver = new Solver(board);
        assertTrue("Should be solvable", solver.isSolvable());
        assertEquals("Wrong moves count", 45, solver.moves());
    }

}
