import java.util.Arrays;

public class FastCollinearPoints {
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            int index = 0;
            Point[] candidatePoints = new Point[points.length - 1];
            for (int j = 0; j < points.length; j++) {

                if (i != j && points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();

                if (i == j) continue;

                candidatePoints[index++] = points[j];
            }

            Arrays.sort(candidatePoints, points[0].slopeOrder());

            // find 3 or more continuous equal slope list
            double lastSlope = Double.NEGATIVE_INFINITY;
            for (int j = 0; j < candidatePoints.length - 1; j++) {
                int start = 0;
                int end = 0;

                double recentSlope = candidatePoints[j].slopeTo(candidatePoints[j + 1]);
                if (lastSlope == recentSlope) {

                } else {
                    if (start == end) {

                    } else {

                    }
                }
                lastSlope = recentSlope;

            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return 0;
    }

    // the line segments
    public LineSegment[] segments() {
        return new LineSegment[0];
    }
}