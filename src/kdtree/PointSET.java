import edu.princeton.cs.algs4.*;

public class PointSET {

    private SET<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new SET<>();

    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : points)
            point.draw();
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> ranged = new Queue<>();
        for (Point2D point : points)
            if (rect.xmin() <= point.x())
                if (rect.xmax() >= point.x())
                    if (rect.ymin() <= point.y())
                        if (rect.ymax() >= point.y())
                            ranged.enqueue(point);
        return ranged;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        MinPQ<Point2D> nearest = new MinPQ<>((p1, p2) -> {
            if(p.distanceTo(p1) == p.distanceTo(p2))
                return 0;

            return (p.distanceTo(p1) < p.distanceTo(p2))? -1 : 1;
        });
        for (Point2D point : points)
            nearest.insert(point);
        return nearest.delMin();
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
