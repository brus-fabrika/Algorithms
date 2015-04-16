import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SAPCourseraTest {

    private Digraph load(String f) {
        return new Digraph(new In(f));
    }

    @Test
    public void testdigraph3_coursera_length() {
        Digraph G = load("digraph3.txt");
        SAP sap = new SAP(G);
        final int v = 8;
        final int w = 13;
        assertEquals(5, sap.length(v, w));
        assertEquals(5, sap.length(w, v));
    }

    @Test
    public void testdigraph4_coursera_length() {
        Digraph G = load("digraph4.txt");
        SAP sap = new SAP(G);
        final int v = 4;
        final int w = 1;
        assertEquals(3, sap.length(v, w));
        assertEquals(3, sap.length(w, v));
    }

    @Test
    public void testdigraph5_coursera_length() {
        Digraph G = load("digraph5.txt");
        SAP sap = new SAP(G);
        final int v = 14;
        final int w = 21;
        assertEquals(8, sap.length(v, w));
        assertEquals(8, sap.length(w, v));
    }

    @Test
    public void testdigraph9_coursera_length() {
        Digraph G = load("digraph9.txt");
        SAP sap = new SAP(G);
        final int v = 4;
        final int w = 0;
        assertEquals(3, sap.length(v, w));
        assertEquals(3, sap.length(w, v));
    }

    @Test
    public void testdigraph_wordnet_coursera_length() {
        Digraph G = load("digraph-wordnet.txt");
        SAP sap = new SAP(G);
        final int v = 54943;
        final int w = 13778;
        assertEquals(13, sap.length(v, w));
        assertEquals(13, sap.length(w, v));
    }

    @Test
    public void testdigraph_wordnet_coursera_length_multi_1() {
        SAP sap = new SAP(load("digraph-wordnet.txt"));
        Bag<Integer> vs = new Bag<Integer>();
        vs.add(72875);
        Bag<Integer> ws = new Bag<Integer>();
        ws.add(61154);
        assertEquals(11, sap.length(vs, ws));
        assertEquals(11, sap.length(ws, vs));
    }

    @Test
    public void testdigraph_wordnet_coursera_length_multi_2() {
        SAP sap = new SAP(load("digraph-wordnet.txt"));
        Bag<Integer> vs = new Bag<Integer>();
        vs.add(51261);
        Bag<Integer> ws = new Bag<Integer>();
        ws.add(482);
        ws.add(22490);
        assertEquals(13, sap.length(vs, ws));
        assertEquals(13, sap.length(ws, vs));
    }

    @Test
    public void testdigraph_wordnet_coursera_length_multi_3() {
        SAP sap = new SAP(load("digraph-wordnet.txt"));
        Bag<Integer> vs = new Bag<Integer>();
        vs.add(14691);
        vs.add(54370);
        Bag<Integer> ws = new Bag<Integer>();
        ws.add(32411);
        assertEquals(10, sap.length(vs, ws));
        assertEquals(10, sap.length(ws, vs));
    }

    @Test
    public void testdigraph_wordnet_coursera_length_multi_3_1() {
        SAP sap = new SAP(load("digraph-wordnet.txt"));
        Bag<Integer> vs = new Bag<Integer>();
        vs.add(54370);
        vs.add(14691);
        Bag<Integer> ws = new Bag<Integer>();
        ws.add(32411);
        assertEquals(10, sap.length(vs, ws));
        assertEquals(10, sap.length(ws, vs));
    }

    @Test
    public void testdigraph_wordnet_coursera_length_multi_4_1() {
        SAP sap = new SAP(load("digraph-wordnet.txt"));
        Bag<Integer> vs = new Bag<Integer>();
        vs.add(15294);
        vs.add(4131);
        Bag<Integer> ws = new Bag<Integer>();
        ws.add(50552);
        ws.add(361);
        assertEquals(5, sap.length(vs, ws));
        assertEquals(5, sap.length(ws, vs));
    }

}
