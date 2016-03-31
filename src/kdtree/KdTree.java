import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private static final RectHV initialBoundary = new RectHV(0, 0, 1, 1);

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect = initialBoundary;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        private boolean isHorizonalSplit = true;
        private boolean isComparedYCoordFirst = false;

        public Node(Point2D p) {
            this(p, null);
        }

        public Node(Point2D p, Node parent) {
            this.p = p;
            if (parent == null) return;

            isHorizonalSplit = !parent.isHorizonalSplit;
            isComparedYCoordFirst = !parent.isComparedYCoordFirst;
            RectHV parentBoundary = parent.rect;

            if (parent.compareTo(p) > 0)
                this.rect = (isHorizonalSplit) ?
                        new RectHV(parentBoundary.xmin(), parentBoundary.ymin(), parentBoundary.xmax(), parent.p.y()) :
                        new RectHV(parentBoundary.xmin(), parentBoundary.ymin(), parent.p.x(), parentBoundary.ymax());
            else
                this.rect = (isHorizonalSplit) ?
                        new RectHV(parentBoundary.xmin(), parent.p.y(), parentBoundary.xmax(), parentBoundary.ymax()) :
                        new RectHV(parent.p.x(), parentBoundary.ymin(), parentBoundary.xmax(), parentBoundary.ymax());

        }

        public int compareTo(Point2D that) {
            if (isComparedYCoordFirst)
                return compareYCoordFirst(that);

            return compareXCoordFirst(that);
        }

        private int compareYCoordFirst(Point2D that) {
            if (this.p.y() < that.y()) return -1;
            if (this.p.y() > that.y()) return +1;
            if (this.p.x() < that.x()) return -1;
            if (this.p.x() > that.x()) return +1;
            return 0;
        }

        private int compareXCoordFirst(Point2D that) {
            if (this.p.x() < that.x()) return -1;
            if (this.p.x() > that.x()) return +1;
            if (this.p.y() < that.y()) return -1;
            if (this.p.y() > that.y()) return +1;
            return 0;
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
        Node cursor = root;
        Node prev = root;
        while (cursor != null) {
            prev = cursor;
            if (cursor.compareTo(p) > 0)
                cursor = cursor.lb;
            else
                cursor = cursor.rt;
        }

        if (prev.compareTo(p) > 0)
            prev.lb = new Node(p, prev);
        else
            prev.rt = new Node(p, prev);
        N++;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        Node traverse = root;
        while (traverse != null) {
            if (traverse.compareTo(p) == 0)
                return true;

            if (traverse.compareTo(p) > 0)
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
        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        node.p.draw();
        StdDraw.setPenRadius(.001);
        StdDraw.setPenColor(StdDraw.BLUE);
        if (node.isHorizonalSplit)
            StdDraw.setPenColor(StdDraw.RED);
        node.rect.draw();

        draw(node.lb);
        draw(node.rt);
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> queue = new Queue<>();
        range(queue, rect, root);
        return queue;
    }

    private void range(Queue<Point2D> queue, RectHV rect, Node node) {
        if (node == null) return;
        if (rect.intersects(node.rect)) {
            if (rect.contains(node.p))
                queue.enqueue(node.p);
            range(queue, rect, node.lb);
            range(queue, rect, node.rt);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        Point2D closest = new Point2D(Double.MAX_VALUE, Double.MAX_VALUE);
        closest = nearest(p, closest, root);
        return closest;
    }

    // if the closest point discovered so far is closer than the distance between the query point and the rectangle corresponding to a node,
    // there is no need to explore that node (or its subtrees).
    private Point2D nearest(Point2D query, Point2D closest, Node node) {
        if (node == null) return closest;
        if(node.rect.distanceTo(query) < closest.distanceTo(query)) {
            Point2D currentClosest = closest;
            if(currentClosest.distanceTo(query) > node.p.distanceTo(query))
                currentClosest = node.p;

            Point2D nearestLeft = nearest(query, closest, node.lb);
            if(currentClosest.distanceTo(query) > nearestLeft.distanceTo(query))
                currentClosest = nearestLeft;

            Point2D nearestRight = nearest(query, closest, node.rt);
            if(currentClosest.distanceTo(query) > nearestRight.distanceTo(query))
                currentClosest = node.p;

            return currentClosest;
        }

        return closest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
