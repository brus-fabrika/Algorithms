import junit.framework.TestCase;

public class OutcastTest extends TestCase {
    
    public void testFromTask_outcast5() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
        {
            String fileName = "outcast5.txt";
            String expected = "table";
            
            In in = new In(fileName);
            String[] nouns = in.readAllStrings();
            String actual = outcast.outcast(nouns);
            assertEquals(expected, actual);
        }
    }
    
    public void testFromTask_outcast8() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
        {
            String fileName = "outcast8.txt";
            String expected = "bed";
            
            In in = new In(fileName);
            String[] nouns = in.readAllStrings();
            String actual = outcast.outcast(nouns);
            assertEquals(expected, actual);
        }
    }
    
    public void testFromTask_outcast11() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
        {
            String fileName = "outcast11.txt";
            String expected = "potato";
            
            In in = new In(fileName);
            String[] nouns = in.readAllStrings();
            String actual = outcast.outcast(nouns);
            assertEquals(expected, actual);
        }
    }
    
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}