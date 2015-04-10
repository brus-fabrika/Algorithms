import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class SAPTest {

    @Test
    public void testDigraph1_1() {
        final String fileName = "digraph1.txt";
        In in = new In(fileName);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        assertEquals(4, sap.length(3, 11));
        assertEquals(1, sap.ancestor(3, 11));
    }

    @Test
    public void testDigraph1_2() {
        final String fileName = "digraph1.txt";
        In in = new In(fileName);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        assertEquals(3, sap.length(9, 12));
        assertEquals(5, sap.ancestor(9, 12));
    }

    @Test
    public void testDigraph1_3() {
        final String fileName = "digraph1.txt";
        In in = new In(fileName);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        assertEquals(4, sap.length(7, 2));
        assertEquals(0, sap.ancestor(7, 2));
    }

    @Test
    public void testDigraph1_4() {
        final String fileName = "digraph1.txt";
        In in = new In(fileName);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        assertEquals(-1, sap.length(1, 6));
        assertEquals(-1, sap.ancestor(1, 6));
    }

}
