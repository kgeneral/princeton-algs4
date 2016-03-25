public class BruteCollinearPoints {

    private int length = 0;
    private LineSegment[] allLineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        allLineSegments = new LineSegment[points.length * points.length];

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i != j && points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();

                if (points[i].compareTo(points[j]) <= 0) continue;
                double firstSlope = points[i].slopeTo(points[j]);
                for (int k = 0; k < points.length; k++) {
                    if (points[j].compareTo(points[k]) <= 0) continue;
                    double secondSlope = points[j].slopeTo(points[k]);
                    for (int m = 0; m < points.length; m++) {
                        if (points[k].compareTo(points[m]) <= 0) continue;
                        double thirdSlope = points[k].slopeTo(points[m]);

                        if (firstSlope == secondSlope && firstSlope == thirdSlope) {
                            allLineSegments[length++] = new LineSegment(points[i], points[m]);
                        }

                    }
                }
            }
        }

    }

    // the number of line segments
    public int numberOfSegments() {
        return length;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[length];
        for (int i = 0; i < length; i++) {
            lineSegments[i] = allLineSegments[i];
        }
        return lineSegments;
    }
}