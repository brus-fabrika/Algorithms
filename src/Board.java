import java.util.Arrays;

public class Board {

    private int[] blocks;

    private final int N;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        N = blocks.length;
        this.blocks = new int[N*N];

        for (int i = 0; i < N; i++) {
            final int rowIndex = N*i;
            for (int j = 0; j < N; j++) {
                final int index = j + rowIndex;
                this.blocks[index] = blocks[i][j];
            }
        }
    }

    // board dimension N
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        int result = 0;

        for (int i = 0; i < blocks.length; ++i) {
            if ((blocks[i] != 0) && blocks[i] != i+1) ++result;
        }

        return result;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int result = 0;

        for (int i = 0; i < blocks.length; ++i) {
            if (blocks[i] != 0) {
                result += getRowDif(blocks[i], i) + getColDif(blocks[i], i);
            }
        }

        return result;
    }

    private int getRowDif(int number, int pos) {
        int result = number/N - pos/N;
        return result > 0 ? result : -result;
    }

    private int getColDif(int number, int pos) {
        int result = (number - 1) % N - (pos % N);
        return result > 0 ? result : -result;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return 0 == hamming();
    }

    // a board that is obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        return null;
    }

    // does this board equal y?
    @Override
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board yBoard = (Board) y;

        return Arrays.equals(blocks, yBoard.blocks);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + N;
        result = prime * result + Arrays.hashCode(blocks);
        return result;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> n = new Stack<>();

        final int emptyIndex = getEmptyIndex();

        final int emptyRow = emptyIndex/5;
        final int emptyCol = emptyIndex % 5;

        int newIndex = -1;
/*
        if (emptyRow > 0) {
            int[] newBlocks = new int[N*N];
            System.arraycopy(blocks, 0, newBlocks, 0, blocks.length);
            newIndex = emptyCol + (emptyRow - 1)*N;
            newBlocks[emptyIndex] = blocks[newIndex];
            newBlocks[newIndex] = 0;

            n.push(new Board());
        }

        if (emptyRow < N - 1) {  }

        if (emptyCol > 0) {  }

        if (emptyCol < N - 1) {  }
*/
        return n;
    }

    private int getEmptyIndex() {
        for (int i = 0; i < blocks.length; ++i) {
            if (blocks[i] == 0) return i;
        }
        return -1;
    }

    // string representation of this board (in the output format specified below)
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", blocks[j + N*i]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }

}