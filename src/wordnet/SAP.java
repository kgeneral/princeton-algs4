import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.SET;

import java.util.Map;
import java.util.TreeMap;

public class SAP {

    private Digraph source;

    private Map<String, Integer> ancestralPathLengthes;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        source = new Digraph(G);
        ancestralPathLengthes = new TreeMap<>();
    }

    private int getAncestralPathLength(int v, int w, int a) {
        String vwKey = generateKey(v, w, a);
        String wvKey = generateKey(w, v, a);
        if (ancestralPathLengthes.containsKey(vwKey))
            return ancestralPathLengthes.get(vwKey);
        if (ancestralPathLengthes.containsKey(wvKey))
            return ancestralPathLengthes.get(wvKey);

        BreadthFirstDirectedPaths shortenPathV = new BreadthFirstDirectedPaths(source, v);
        BreadthFirstDirectedPaths shortenPathW = new BreadthFirstDirectedPaths(source, w);

        int result = shortenPathV.distTo(a) + shortenPathW.distTo(a);
        ancestralPathLengthes.put(vwKey, result);

        return result;
    }

    private String generateKey(int v, int w, int a) {
        return v + "_" + w + "_" + a;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        int commonAncestor = ancestor(v, w);
        if (commonAncestor < 0) return -1;
        return getAncestralPathLength(v, w, commonAncestor);
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
        for (int candidate : commonAncestors) {
            int ancestralPathLength = getAncestralPathLength(v, w, candidate);
            if (dist > ancestralPathLength) {
                dist = ancestralPathLength;
                result = candidate;
            }
        }

        return result;
    }

    private SET<Integer> ancestors(int v) {
        SET<Integer> ancestors = new SET<>();
        ancestors(ancestors, v);
        return ancestors;
    }

    private void ancestors(SET<Integer> ancestors, int w) {
        if (ancestors.contains(w)) return;
        ancestors.add(w);
        Iterable<Integer> adjs = source.adj(w);
        for (int v : adjs) {
            ancestors(ancestors, v);
        }
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new NullPointerException();
        int result = -1;
        int dist = Integer.MAX_VALUE;
        for (int v1 : v)
            for (int w1 : w) {
                int candidate = ancestor(v1, w1);
                int ancestralPathLength = getAncestralPathLength(v1, w1, candidate);
                if (dist > ancestralPathLength) {
                    dist = ancestralPathLength;
                    result = dist;
                }
            }

        return result;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new NullPointerException();
        int result = -1;
        int dist = Integer.MAX_VALUE;
        for (int v1 : v)
            for (int w1 : w) {
                int candidate = ancestor(v1, w1);
                int ancestralPathLength = getAncestralPathLength(v1, w1, candidate);
                if (dist > ancestralPathLength) {
                    dist = ancestralPathLength;
                    result = candidate;
                }
            }

        return result;
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
