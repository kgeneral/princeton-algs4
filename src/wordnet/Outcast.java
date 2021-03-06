public class Outcast {

    private WordNet wordnet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    // Given a list of wordnet nouns A1, A2, ..., An, which noun is the least related to the others? To identify an
    // outcast, compute the sum of the distances between each noun and every other one:
    // di = dist(Ai, A1) + dist(Ai, A2) + ... + dist(Ai, An)
    // and return a noun At for which dt is maximum.
    public String outcast(String[] nouns) {
        String outcastNoun = null;
        int maxDist = Integer.MIN_VALUE;
        for (String nounI : nouns) {
            int dist = 0;
            for (String noun : nouns)
                if (!noun.equals(nounI))
                    dist += wordnet.distance(nounI, noun);

            if (maxDist < dist) {
                maxDist = dist;
                outcastNoun = nounI;
            }
        }

        return outcastNoun;
    }

    // see test client below
    public static void main(String[] args) {
    }
}