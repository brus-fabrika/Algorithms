import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


public class WordNetTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testIsNoun() {
        WordNet wordnet = new WordNet("synsets15.txt", "hypernyms15Path.txt");
        assertEquals(true, wordnet.isNoun("a"));
        assertEquals(true, wordnet.isNoun("o"));
        assertEquals(false, wordnet.isNoun("p"));
        assertEquals(false, wordnet.isNoun("x"));
    }

    @Test
    public void testNouns() {
        WordNet wordnet = new WordNet("synsets3.txt", "hypernyms3InvalidCycle.txt");
        Iterable<String> nouns = wordnet.nouns();
        Iterator<String> i = nouns.iterator();

        assertTrue(i.hasNext());        assertEquals("a", i.next());
        assertTrue(i.hasNext());        assertEquals("b", i.next());
        assertTrue(i.hasNext());        assertEquals("c", i.next());
        assertTrue(!i.hasNext());
    }

    @Test
    public void testFarNouns_1() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        assertEquals(23, wordnet.distance("white_marlin", "mileage"));
    }

    @Test
    public void testFarNouns_2() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        assertEquals(33, wordnet.distance("Black_Plague", "black_marlin"));
    }

    @Test
    public void testFarNouns_3() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        assertEquals(27, wordnet.distance("American_water_spaniel", "histology"));
    }

    @Test
    public void testFarNouns_4() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        assertEquals(29, wordnet.distance("Brown_Swiss", "barrel_roll"));
    }
    
    @Test
    public void testSapWormBird() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        assertEquals("animal animate_being beast brute creature fauna", wordnet.sap("worm", "bird"));
    }
  
    @Test
    public void testSapBirdWorm() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        assertEquals("animal animate_being beast brute creature fauna", wordnet.sap("bird", "worm"));
    }
}
