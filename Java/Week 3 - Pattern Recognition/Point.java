import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {
    
    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void draw() {
        StdDraw.point(x, y);
    }
    
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    public int compareTo(Point that) {
        if ((this.y < that.y) || ((this.y == that.y) && (this.x < that.x))) {
            return -1;
        }
        return 1;
    }
    
    public double slopeTo(Point that) {
        if (that.y == this.y) {
            return 0.0;
        } else if (that.x == this.x) {
            return Double.POSITIVE_INFINITY;
        }
        double slope = (that.y - this.y)/(that.y - this.y);
        if (slope < 0) {
            return Double.NEGATIVE_INFINITY;
        }
        return slope;
    }
    
    public Comparator<Point> slopeOrder() {
        return new BySlopeOrder();
    }
    
    private class BySlopeOrder implements Comparator<Point> {
        public int compare(Point a, Point b) {
            Point currentPoint = new Point(x, y);
            if (currentPoint.slopeTo(a) < currentPoint.slopeTo(b)) {
                return -1;
            }
            return 1;
        }
    }
    
    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        for (int i = 0; i < n; i++) {
            StdOut.println(points[i].x);
            StdOut.println(points[i].y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    }
}