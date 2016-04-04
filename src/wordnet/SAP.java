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
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        int commonAncestor = ancestor(v, w);
        if (commonAncestor < 0) return -1;
        return shortenPathes[v].distTo(commonAncestor) + shortenPathes[w].distTo(commonAncestor);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        // find all ancestors of x and y
        Iterable<Integer> ancestorsV = ancestors(v);
        Iterable<Integer> ancestorsW = ancestors(w);
        // then return the maximum element of the intersection of the two sets
        int result = -1;
        SET<Integer> commonAncestors = new SET<>();
        for (int ancestorV : ancestorsV)
            for (int ancestorW : ancestorsW)
                if (ancestorV == ancestorW)
                    commonAncestors.add(ancestorV);

        int dist = Integer.MAX_VALUE;
        for (int candidate : commonAncestors)
            if (dist > shortenPathes[v].distTo(candidate) + shortenPathes[w].distTo(candidate)) {
                dist = shortenPathes[v].distTo(candidate) + shortenPathes[w].distTo(candidate);
                result = candidate;
            }

        return result;
    }

    private SET<Integer> ancestors(int v) {
        SET<Integer> ancestors = new SET<>();
        ancestors(ancestors, v);
        return ancestors;
    }

    private void ancestors(SET<Integer> ancestors, int w) {
        if(ancestors.contains(w)) return;
        Iterable<Integer> adjs = source.adj(w);
        for (int v : adjs) {
            ancestors.add(v);
            ancestors(ancestors, v);
        }
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int result = -1;
        int dist = Integer.MAX_VALUE;
        for (int v1 : v)
            for (int w1 : w) {
                int candidate = ancestor(v1, w1);
                if (dist < shortenPathes[v1].distTo(candidate) + shortenPathes[w1].distTo(candidate)) {
                    dist = shortenPathes[v1].distTo(candidate) + shortenPathes[w1].distTo(candidate);
                    result = dist;
                }
            }

        return result;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int result = -1;
        int dist = Integer.MAX_VALUE;
        for (int v1 : v)
            for (int w1 : w) {
                int candidate = ancestor(v1, w1);
                if (dist < shortenPathes[v1].distTo(candidate) + shortenPathes[w1].distTo(candidate)) {
                    dist = shortenPathes[v1].distTo(candidate) + shortenPathes[w1].distTo(candidate);
                    result = candidate;
                }
            }

        return result;
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
