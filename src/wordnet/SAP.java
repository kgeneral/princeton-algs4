import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.SET;

public class SAP {

    private Digraph source;
    private BreadthFirstDirectedPaths[] shortenPathes;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        source = G;
        shortenPathes = new BreadthFirstDirectedPaths[source.V()];
        for (int v = 0; v < source.V(); v++) {
            shortenPathes[v] = new BreadthFirstDirectedPaths(source, v);


        }
        //
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return 0;
    }

    private int root(int v) {

        return 0;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        // find all ancestors of x and y
        Iterable<Integer> ancestorsV = ancestors(v);
        Iterable<Integer> ancestorsW = ancestors(w);
        // then return the maximum element of the intersection of the two sets
        int result = -1;
        for (int ancestorV : ancestorsV)
            for (int ancestorW : ancestorsW)
                if (ancestorV == ancestorW)
                    if (result < ancestorV)
                        result = ancestorV;

        return result;
    }

    private Iterable<Integer> ancestors(int w) {
        SET<Integer> ancestors = new SET<>();
        for (int v : source.adj(w))
            ancestors.add(v);

        return ancestors;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
