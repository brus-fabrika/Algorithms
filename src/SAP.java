public class SAP {

    private final Digraph DG;

    private static class Pair<KeyType, ValueType> {
        private final KeyType KEY;
        private final ValueType VALUE;

        public Pair(KeyType key, ValueType value) {
            this.KEY = key;
            this.VALUE = value;
        }

        public KeyType getKey() {
            return KEY;
        }

        public ValueType getValue() {
            return VALUE;
        }
    }

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new java.lang.NullPointerException();
        DG = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {

        if (v < 0 || v > DG.V() - 1 || w < 0 || w > DG.V() - 1)
            throw new java.lang.IndexOutOfBoundsException();

        BreadthFirstDirectedPaths bsf = new BreadthFirstDirectedPaths(DG, w);
        Pair<Integer, Integer> p = calculateAncestor(v, bsf);

        return p.getValue();
    }

    // a common ancestor of v and w that participates in a shortest ancestral
    // path; -1 if no such path
    public int ancestor(int v, int w) {

        if (v < 0 || v > DG.V() - 1 || w < 0 || w > DG.V() - 1)
            throw new java.lang.IndexOutOfBoundsException();

        BreadthFirstDirectedPaths bsf = new BreadthFirstDirectedPaths(DG, w);
        Pair<Integer, Integer> p = calculateAncestor(v, bsf);

        return p.getKey();
    }

    private Pair<Integer, Integer>
    calculateAncestor(int v, BreadthFirstDirectedPaths bfs) {

        if (v < 0 || v > DG.V() - 1)
            throw new java.lang.IndexOutOfBoundsException();

        Queue<Pair<Integer, Integer>> q = new Queue<>();

        boolean[] visited = new boolean[DG.V()];

        int sapLenght = Integer.MAX_VALUE;
        int ancestorVertex = -1;

        if (bfs.hasPathTo(v)) {
            sapLenght = bfs.distTo(v);
            ancestorVertex = v;
        }

        q.enqueue(new Pair<Integer, Integer>(v, 0));

        while (!q.isEmpty()) {

            Pair<Integer, Integer> p = q.dequeue();

            int x = p.getKey();
            int level = p.getValue() + 1;

//            visited[x] = true;
            
            for (int currentVertex: DG.adj(x)) {
                if (visited[currentVertex]) continue;
                if (bfs.hasPathTo(currentVertex)) {
                    int tmp = bfs.distTo(currentVertex) + level;
                    if (sapLenght > tmp) {
                        sapLenght = tmp;
                        ancestorVertex = currentVertex;
                    }
                }
                q.enqueue(new Pair<Integer, Integer>(currentVertex, level));
                visited[currentVertex] = true;
            }
        }

        return ancestorVertex == -1 ? new Pair<Integer, Integer>(-1, -1)
                                    : new Pair<Integer, Integer>(ancestorVertex, sapLenght);
    }

    // length of shortest ancestral path between any vertex in v and any vertex
    // in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        if (v == null || w == null) throw new java.lang.NullPointerException();

        BreadthFirstDirectedPaths bsf = new BreadthFirstDirectedPaths(DG, w);

        Pair<Integer, Integer> p = calculateAncestor(v, bsf);

        return p.getValue();
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no
    // such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {

        if (v == null || w == null) throw new java.lang.NullPointerException();

        BreadthFirstDirectedPaths bsf = new BreadthFirstDirectedPaths(DG, w);

        Pair<Integer, Integer> p = calculateAncestor(v, bsf);

        return p.getKey();
    }

    private Pair<Integer, Integer> calculateAncestor(Iterable<Integer> v, BreadthFirstDirectedPaths bfs) {

        Pair<Integer, Integer> p = new Pair<>(-1, Integer.MAX_VALUE);

        for (int vv: v) {
            Pair<Integer, Integer> t = calculateAncestor(vv, bfs);
            if ((t.getValue() != -1) && (p.getValue() > t.getValue())) p = t;
        }

        return p.getKey() == -1 ? new Pair<Integer, Integer>(-1, -1) : p;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In("digraph9.txt");
        Digraph G = new Digraph(in);

        StdOut.printf("G.V() =  %d\n", G.V());

        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            StdOut.println("ping");
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
