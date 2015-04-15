import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class SAPTest2 {

    private SAP sap;

    @Before
    public void setUp() throws Exception {
        final String fileName = "digraph1_m.txt";
        In in = new In(fileName);
        Digraph G = new Digraph(in);
        sap = new SAP(G);
    }

    @Test
    public void testDigraph_1() {
        assertEquals(4, sap.length(3, 11));
        assertEquals(1, sap.ancestor(3, 11));

        assertEquals(4, sap.length(11, 3));
        assertEquals(1, sap.ancestor(11, 3));
    }

    @Test
    public void testDigraph_2() {
        assertEquals(3, sap.length(9, 12));
        assertEquals(5, sap.ancestor(9, 12));

        assertEquals(3, sap.length(12, 9));
        assertEquals(5, sap.ancestor(12, 9));
    }

    @Test
    public void testDigraph_3() {
        assertEquals(3, sap.length(7, 2));
        assertEquals(0, sap.ancestor(7, 2));

        assertEquals(3, sap.length(2, 7));
        assertEquals(0, sap.ancestor(2, 7));
    }

    @Test
    public void testDigraph_4() {
        assertEquals(-1, sap.length(1, 6));
        assertEquals(-1, sap.ancestor(1, 6));

        assertEquals(-1, sap.length(6, 1));
        assertEquals(-1, sap.ancestor(6, 1));
    }

    @Test
    public void testDigraph_5() {
        assertEquals(2, sap.length(7, 8));
        assertEquals(3, sap.ancestor(7, 8));

        assertEquals(2, sap.length(8, 7));
        assertEquals(3, sap.ancestor(8, 7));
    }

    @Test
    public void testDigraph_6() {
        assertEquals(2, sap.length(7, 1));
        assertEquals(1, sap.ancestor(7, 1));

        assertEquals(2, sap.length(1, 7));
        assertEquals(1, sap.ancestor(1, 7));
    }

    @Test
    public void testDigraph_multi() {
        Bag<Integer> vs = new Bag<Integer>();
        vs.add(7); vs.add(9);
        Bag<Integer> ws = new Bag<Integer>();
        ws.add(11); ws.add(12);
        final int l = 3; // who knows
        final int a = 5;  // who knows
        final int length = sap.length(vs, ws);
        final int ancestor = sap.ancestor(vs, ws);
        assertTrue("length/ancestor " + length + "/" + ancestor + " but should be " + l + "/" + a, length == l && ancestor == a);
        assertEquals(length, sap.length(ws, vs));
        assertEquals(ancestor, sap.ancestor(ws, vs));
    }

    @Test
    public void testDigraph_multi_2() {
        Bag<Integer> vs = new Bag<Integer>();
        vs.add(7); vs.add(4);
        Bag<Integer> ws = new Bag<Integer>();
        ws.add(0); ws.add(1);
        final int l = 1; // who knows
        final int a = 1;  // who knows
        final int length = sap.length(vs, ws);
        final int ancestor = sap.ancestor(vs, ws);
        assertTrue("length/ancestor " + length + "/" + ancestor + " but should be " + l + "/" + a, length == l && ancestor == a);
        assertEquals(length, sap.length(ws, vs));
        assertEquals(ancestor, sap.ancestor(ws, vs));
    }
}
