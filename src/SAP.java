public class SAP {

    private Digraph dg;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new java.lang.NullPointerException();
        dg = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {

        BreadthFirstDirectedPaths bsf = new BreadthFirstDirectedPaths(dg, w);

        Queue<Integer> q = new Queue<>();
        Queue<Integer> l = new Queue<>();

        int result = Integer.MAX_VALUE;

        int level = 0;
        q.enqueue(v);
        l.enqueue(level);

        while (!q.isEmpty()) {

            int x = q.dequeue();
            level = l.dequeue() + 1;

            for(int i: dg.adj(x)) {
                q.enqueue(i);
                l.enqueue(level);
                if(bsf.hasPathTo(i)) {
                    int tmp = bsf.distTo(i) + level;
                    if (result > tmp) result = tmp;
                }
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    // a common ancestor of v and w that participates in a shortest ancestral
    // path; -1 if no such path
    public int ancestor(int v, int w) {

        BreadthFirstDirectedPaths bsf = new BreadthFirstDirectedPaths(dg, w);

        Queue<Integer> q = new Queue<>();
        Queue<Integer> l = new Queue<>();

        int result = Integer.MAX_VALUE;
        int r = -1;

        int level = 0;
        q.enqueue(v);
        l.enqueue(level);
        while (!q.isEmpty()) {
            int x = q.dequeue();
            level = l.dequeue() + 1;
            for(int i: dg.adj(x)) {
                q.enqueue(i);
                l.enqueue(level);
                if(bsf.hasPathTo(i)) {
                    int tmp = bsf.distTo(i) + level;
                    if (result > tmp) {
                        result = tmp;
                        r = i;
                    }
                }
            }
            level++;
        }

        return r;
    }

    // length of shortest ancestral path between any vertex in v and any vertex
    // in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        BreadthFirstDirectedPaths bsf = new BreadthFirstDirectedPaths(dg, w);

        Queue<Integer> q = new Queue<>();
        Queue<Integer> qq = new Queue<>();

        int result = Integer.MAX_VALUE;


        int r = -1;

        for(int vv: v) qq.enqueue(vv);

        while (!qq.isEmpty()) {
            q.enqueue(qq.dequeue());
            int level = 1;
            while (!q.isEmpty()) {
                int x = q.dequeue();
                for(int i: dg.adj(x)) {
                    q.enqueue(i);
                    if(bsf.hasPathTo(i)) {
                        int tmp = bsf.distTo(i) + level;
                        if (result > tmp) result = tmp;
                    }
                }
                level++;
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no
    // such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {

        BreadthFirstDirectedPaths bsf = new BreadthFirstDirectedPaths(dg, w);

        Queue<Integer> q = new Queue<>();
        Queue<Integer> qq = new Queue<>();

        int result = Integer.MAX_VALUE;

        int r = -1;

        for(int vv: v) qq.enqueue(vv);

        while (!qq.isEmpty()) {
            q.enqueue(qq.dequeue());
            int level = 1;
            while (!q.isEmpty()) {
                int x = q.dequeue();
                for(int i: dg.adj(x)) {
                    q.enqueue(i);
                    if(bsf.hasPathTo(i)) {
                        int tmp = bsf.distTo(i) + level;
                        if (result > tmp) {
                            result = tmp;
                            r = i;
                        }
                    }
                }
                level++;
            }
        }

        return r;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        StdOut.printf("G.V() =  %d\n", G.V());

        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
