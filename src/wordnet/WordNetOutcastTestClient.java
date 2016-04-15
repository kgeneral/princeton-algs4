import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class WordNetOutcastTestClient {
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);

//        for(String noun : wordnet.nouns())
//            StdOut.print(noun + ", ");
//        StdOut.println();

        StdOut.println(wordnet.distance("change", "thing"));

        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
