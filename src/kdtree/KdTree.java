import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p) {
            this.p = p;
        }
    }

    private Node root;
    private int N;

    // construct an empty set of points
    public KdTree() {
        N = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // number of points in the set
    public int size() {
        return N;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (contains(p)) return;
        if (root == null) {
            root = new Node(p);
            return;
        }
        Node newNode = new Node(p);
        Node cursor = root;
        Node prev = root;
        while (cursor != null) {
            prev = cursor;
            if (cursor.p.compareTo(p) > 0)
                cursor = cursor.lb;
            else
                cursor = cursor.rt;
        }

        if (prev.p.compareTo(p) > 0)
            prev.lb = newNode;
        else
            prev.rt = newNode;
        N++;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        Node traverse = root;
        while (traverse != null) {
            if (traverse.p.compareTo(p) == 0)
                return true;

            if (traverse.p.compareTo(p) > 0)
                traverse = traverse.lb;
            else
                traverse = traverse.rt;
        }

        return false;
    }

    // draw all points to standard draw
    public void draw() {
        draw(root);
    }

    // DFS preorder
    private void draw(Node node) {
        if (node == null) return;
        node.p.draw();
        draw(node.lb);
        draw(node.rt);
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        return p;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
