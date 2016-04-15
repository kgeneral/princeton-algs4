import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class WordNetSAPTestClient {
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
//        while (!StdIn.isEmpty()) {
//            int v = StdIn.readInt();
//            int w = StdIn.readInt();
//            int length = sap.length(v, w);
//            int ancestor = sap.ancestor(v, w);
//            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//        }
        int n = 0;
        while (n++ < 50000000) {

            int v = StdRandom.uniform(G.V());
            int w = StdRandom.uniform(G.V());
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("v = %d, w = %d\n", v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
