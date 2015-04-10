public class WordNet {
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new java.lang.NullPointerException();
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return null;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new java.lang.NullPointerException();

        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new java.lang.NullPointerException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new java.lang.IllegalArgumentException();

        return -1;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of
    // nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new java.lang.NullPointerException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new java.lang.IllegalArgumentException();

        return null;
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
