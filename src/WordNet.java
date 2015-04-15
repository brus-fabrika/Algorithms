import java.util.HashMap;
import java.util.Map;

public class WordNet {

    private static class SynsetData {
        private final String WORDS;
        private final String DESCRIPTION;

        SynsetData(String w, String d) {
            WORDS = w;
            DESCRIPTION = d;
        }

        public String getWords() {
            return WORDS;
        }

        public String getDescription() {
            return DESCRIPTION;
        }
    }

    private Map<Integer, SynsetData> mSynsets = new HashMap<>();

    private Map<String, Bag<Integer>> mNouns = new HashMap<>();

    private Digraph mGraph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new java.lang.NullPointerException();

        In synIn = new In(synsets);
        String line = synIn.readLine();

        while (line != null) {
            String[] ss = line.split(",");
            int id = Integer.parseInt(ss[0]);
            mSynsets.put(id, new SynsetData(ss[1], ss[2]));

            for (String s: ss[1].split(" ")) {
                if (mNouns.containsKey(s)) mNouns.get(s).add(id);
                else {
                    Bag<Integer> sBag = new Bag<>();
                    sBag.add(id);
                    mNouns.put(s, sBag);
                }
            }
            line = synIn.readLine();
        }

        mGraph = new Digraph(mSynsets.size());

        In hypIn = new In(hypernyms);

        line = hypIn.readLine();
        while (line != null) {
            String[] ss = line.split(",");
            int id = Integer.parseInt(ss[0]);

            for (int i = 1; i < ss.length; ++i) {
                int idHyp = Integer.parseInt(ss[i]);
                mGraph.addEdge(id, idHyp);
            }

            line = hypIn.readLine();
        }

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return mNouns.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new java.lang.NullPointerException();

        return mNouns.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new java.lang.NullPointerException();

        if (!isNoun(nounA) || !isNoun(nounB))
            throw new java.lang.IllegalArgumentException();

        Iterable<Integer> as = mNouns.get(nounA);
        if (as != null) {
            Iterable<Integer> bs = mNouns.get(nounB);
            if (bs != null) {
                SAP sap = new SAP(mGraph);
                return sap.length(as, bs);
            }
        }
        return -1;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of
    // nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new java.lang.NullPointerException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new java.lang.IllegalArgumentException();

        int ancestorId = -1;

        Iterable<Integer> as = mNouns.get(nounA);
        if (as != null) {
            Iterable<Integer> bs = mNouns.get(nounB);
            if (bs != null) {
                SAP sap = new SAP(mGraph);
                ancestorId = sap.ancestor(as, bs);
            }
        }

        if (ancestorId != -1) {
            return mSynsets.get(ancestorId).getWords();
        }

        return null;
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
