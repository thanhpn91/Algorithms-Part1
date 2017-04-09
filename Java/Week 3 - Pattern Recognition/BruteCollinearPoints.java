import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private LineSegment[] linesegments;
    private int size;
    
    public BruteCollinearPoints(Point[] points) {
        Point[] copyPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(copyPoints);
        
        ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        
        int n = copyPoints.length;
        for (int p = 0; p < n - 3; p++) {
            for (int q = p + 1; q < n - 2; q++) {
                double slopePQ = copyPoints[p].slopeTo(copyPoints[q]);
                for (int r = q + 1; r < n - 1; r++) {
                    double slopePR = copyPoints[p].slopeTo(copyPoints[r]);
                    for (int s = r + 1; s < n; s++) {
                        double slopePS = copyPoints[p].slopeTo(copyPoints[s]);
                        if (slopePQ == slopePR  && slopePR == slopePS) {
                            LineSegment segment = new LineSegment(copyPoints[p], copyPoints[s]);
                            segments.add(segment);
                        }
                    }
                }
            }
        }
        linesegments = segments.toArray(new LineSegment[segments.size()]);
    }
    
    public int numberOfSegments() {
        return linesegments.length;
    }
    
    public LineSegment[] segments() {
        return linesegments;
    }
}