
public class Solver {

    private static final class SearchNode implements Comparable<SearchNode> {
        private Board mBoard;
        private SearchNode mPrevNode;
        private int mMoves;

        public SearchNode(Board board) {
            mBoard = board;
            mPrevNode = null;
            mMoves = 0;
        }

        public SearchNode(Board board, SearchNode prevNode, int move) {
            mBoard = board;
            mPrevNode = prevNode;
            mMoves = move;
        }

        @Override
        public int compareTo(SearchNode o) {
            return (mBoard.manhattan() + mMoves) - (o.mBoard.manhattan() + o.mMoves);
        }

        public Iterable<SearchNode> getNeigbors() {
            Stack<SearchNode> neighbors = new Stack<>();
            Iterable<Board> boards = mBoard.neighbors();
            for (Board b: boards) {
                if (mPrevNode != null ) {
                    if( !b.equals(mPrevNode.mBoard)) {
                        neighbors.push(new SearchNode(b, this, mMoves + 1));
                    }
                } else {
                    neighbors.push(new SearchNode(b, this, mMoves + 1));
                }
            }

            return neighbors;
        }

    }

    private MinPQ<SearchNode> mGameTree;

    private Stack<Board> mSolution;

    private boolean isSolvable;


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }

        mGameTree = new MinPQ<>();
        mGameTree.insert(new SearchNode(initial));
        mGameTree.insert(new SearchNode(initial.twin()));

        SearchNode node = mGameTree.delMin();

        while (!node.mBoard.isGoal()) {
            // get all neighbor nodes
            Iterable<SearchNode> neighborNodes = node.getNeigbors();
            for (SearchNode nn: neighborNodes) {
                //StdOut.println(nn.mBoard);
                mGameTree.insert(nn);
            }

            node = mGameTree.delMin();
        }

        mSolution = new Stack<>();
        mSolution.push(node.mBoard);

        while (node.mPrevNode != null) {
            node = node.mPrevNode;
            mSolution.push(node.mBoard);
        }

        isSolvable = mSolution.peek().equals(initial);

    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable) return mSolution.size() - 1;

        return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return mSolution;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In("puzzle22.txt");
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        // solve the puzzle
        long startTime = System.currentTimeMillis();
        Solver solver = new Solver(initial);
        long elapsedTime = System.currentTimeMillis() - startTime;
        StdOut.printf("Solution found in %.3f seconds\n", (double)(elapsedTime)/1000);
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}