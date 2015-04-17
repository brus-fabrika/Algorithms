import java.util.HashMap;
import java.util.Map;

public class WordNet {

    private Map<Integer, String> mSynsets = new HashMap<>();

    private Map<String, Bag<Integer>> mNouns = new HashMap<>();

    private SAP mSap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new java.lang.NullPointerException();

        In synIn = new In(synsets);
        String line = synIn.readLine();

        while (line != null) {
            String[] ss = line.split(",");
            int id = Integer.parseInt(ss[0]);
            mSynsets.put(id, ss[1]);

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

        Digraph graph = new Digraph(mSynsets.size());

        In hypIn = new In(hypernyms);

        line = hypIn.readLine();
        while (line != null) {
            String[] ss = line.split(",");
            int id = Integer.parseInt(ss[0]);

            for (int i = 1; i < ss.length; ++i) {
                int idHyp = Integer.parseInt(ss[i]);
                graph.addEdge(id, idHyp);
            }

            line = hypIn.readLine();
        }

        DirectedCycle dc = new DirectedCycle(graph);
        if (dc.hasCycle())
            throw new java.lang.IllegalArgumentException("Cycle: not a rooted DAG");

        int rootCount = 0;
        for (int i = 0; i < graph.V(); ++i) {
          if (graph.outdegree(i) == 0) rootCount++;
        }

        if (rootCount != 1)
            throw new java.lang.IllegalArgumentException("More then 1 root: not a rooted DAG");

        mSap = new SAP(graph);

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
                return mSap.length(as, bs);
            }
        }
        return -1;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of
    // nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new java.lang.NullPointerException();

        if (!isNoun(nounA) || !isNoun(nounB))
            throw new java.lang.IllegalArgumentException();

        int ancestorId = -1;

        Iterable<Integer> as = mNouns.get(nounA);
        if (as != null) {
            Iterable<Integer> bs = mNouns.get(nounB);
            if (bs != null) {
                ancestorId = mSap.ancestor(as, bs);
            }
        }

        if (ancestorId != -1) {
            return mSynsets.get(ancestorId);
        }

        return null;
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
