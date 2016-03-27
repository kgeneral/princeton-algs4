import java.util.Arrays;

public class FastCollinearPoints {

    private int length = 0;
    private LineSegment[] allLineSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        Point[] inputPoints = Arrays.copyOf(points, points.length);

        int allLineSegmentsCount = inputPoints.length * inputPoints.length;
        allLineSegments = new LineSegment[allLineSegmentsCount];

        Arrays.sort(inputPoints, (p1, p2) -> p2.compareTo(p1));

        Point[] lineStarts = new Point[allLineSegmentsCount - 1];
        Point[] lineEnds = new Point[allLineSegmentsCount - 1];

        for (int i = 0; i < inputPoints.length; i++) {
            int index = 0;
            Point[] candidatePoints = new Point[inputPoints.length - 1];
            for (int j = 0; j < inputPoints.length; j++) {

                if (i != j && inputPoints[i].compareTo(inputPoints[j]) == 0)
                    throw new IllegalArgumentException();

                if (i == j) continue;

                candidatePoints[index++] = inputPoints[j];
            }

            final int originIndex = i;
            Arrays.sort(candidatePoints, (p1, p2) -> {
                double originToP1 = inputPoints[originIndex].slopeTo(p1);
                double originToP2 = inputPoints[originIndex].slopeTo(p2);

                if (originToP1 == originToP2)
                    return 0;

                return (originToP1 > originToP2) ? -1 : 1;
            });

            if(candidatePoints.length < 1) continue;

            // find 3 or more continuous same slope list
            double lastSlope = inputPoints[i].slopeTo(candidatePoints[0]);
            int start = 0;
            int end = 0;
            for (int j = 0; j <= candidatePoints.length; j++) {
                double recentSlope;
                if (j != candidatePoints.length)
                    recentSlope = inputPoints[i].slopeTo(candidatePoints[j]);
                else
                    recentSlope = Double.NEGATIVE_INFINITY;
                if (lastSlope != recentSlope) {
                    if (end - start >= 2) {
                        Point[] candidates = new Point[end - start + 2];
                        for (int k = start; k <= end; k++) {
                            candidates[k - start] = candidatePoints[k];
                        }
                        candidates[end - start + 1] = inputPoints[i];
                        Arrays.sort(candidates, (p1, p2) -> p2.compareTo(p1));

                        boolean duplicated = false;
                        for (int k = 0; k < length; k++) {
                            if (lineStarts[k].compareTo(candidates[0]) == 0
                                    && lineEnds[k].compareTo(candidates[end - start + 1]) == 0) {
                                duplicated = true;
                                break;
                            }
                        }

                        if (!duplicated) {
                            allLineSegments[length] = new LineSegment(candidates[0], candidates[end - start + 1]);
                            lineStarts[length] = candidates[0];
                            lineEnds[length++] = candidates[end - start + 1];
                        }


                    }
                    start = j;

                    lastSlope = recentSlope;
                }
                end = j;
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