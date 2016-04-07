import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Map;
import java.util.TreeMap;

public class WordNet {

    private class Synset implements Comparable<Synset> {
        String synsetNoun;
        int synsetId;
        String gloss;

        public Synset(String synsetNoun, int synsetId, String gloss) {
            this.synsetNoun = synsetNoun;
            this.synsetId = synsetId;
            this.gloss = gloss;
        }

        public Synset(String word) {
            this.synsetNoun = word;
        }

        @Override
        public int compareTo(Synset target) {
            if (synsetNoun.equals(target.synsetNoun))
                return 0;

            return synsetNoun.hashCode() - target.synsetNoun.hashCode();
        }
    }

    private Map<Integer, String> synIdSetMap;
    private Map<String, Integer> nounSynIdMap;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        this.synIdSetMap = new TreeMap<>();
        this.nounSynIdMap = new TreeMap<>();

        In synsetInput = new In(synsets);
        int synsetId = 0;
        while (synsetInput.hasNextLine()) {
            String line = synsetInput.readLine();
            String[] tokens = line.split(",");
            synsetId = Integer.parseInt(tokens[0]);
            String[] synsetNouns = tokens[1].split(" ");

            this.synIdSetMap.put(synsetId, tokens[1]);

            for (String noun : synsetNouns)
                this.nounSynIdMap.put(noun, synsetId);
        }

        Digraph digraph = new Digraph(synsetId + 1);
        In hypernymInput = new In(hypernyms);
        while (hypernymInput.hasNextLine()) {
            String line = hypernymInput.readLine();
            String[] tokens = line.split(",");

            int synsetSourceId = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++)
                digraph.addEdge(synsetSourceId, Integer.parseInt(tokens[i]));
        }

        sap = new SAP(digraph);

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        Bag<String> nouns = new Bag<>();
        nounSynIdMap.keySet().forEach(nouns::add);
        return nouns;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return nounSynIdMap.containsKey(word);
    }

    // distance between nounA and nounB
    // distance(A, B) = distance is the minimum length of any ancestral path between any synset v of A and any synset w of B.
    public int distance(String nounA, String nounB) {
        String[] nounsA = nounA.split(" ");
        String[] nounsB = nounB.split(" ");

        Bag<Integer> v = new Bag<>();
        Bag<Integer> w = new Bag<>();

        for (String noun : nounsA)
            v.add(nounSynIdMap.get(noun));
        for (String noun : nounsB)
            w.add(nounSynIdMap.get(noun));

        return sap.length(v, w);
    }

    // a synset (second field of nounSynIdMap.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        String[] nounsA = nounA.split(" ");
        String[] nounsB = nounB.split(" ");

        Bag<Integer> v = new Bag<>();
        Bag<Integer> w = new Bag<>();

        for (String noun : nounsA)
            v.add(nounSynIdMap.get(noun));
        for (String noun : nounsB)
            w.add(nounSynIdMap.get(noun));

        int shortestAncestor = sap.ancestor(v, w);
        String shortestAncestorSynset = null;
        if (shortestAncestor >= 0)
            shortestAncestorSynset = synIdSetMap.get(shortestAncestor);
        return shortestAncestorSynset;
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }

}