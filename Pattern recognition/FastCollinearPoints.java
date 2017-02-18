import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Objects;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;
    private ArrayList<Point> p;
    private ArrayList<Point> maxPoints;
    
    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
        if (points == null) throw new NullPointerException("Null point array");
        boolean alreadyExists = false;
        lineSegments = new ArrayList<LineSegment>();
        int n = points.length;
        maxPoints = new ArrayList<Point>();
        Point[] originalOrder = Arrays.copyOf(points, n);
        //System.arraycopy(points, 0, originalOrder, 0, n);
        
        // check the slope of each point to each other point, and sort array according to the slopes starting from the current point
        for(int i = 0; i < n; i++) {
            Point current = originalOrder[i];
            
            Arrays.sort(points, 0, n, current.slopeOrder());
            //for(int j = 0; j < n; j++){
                //System.out.println(current.slopeTo(points[j]));
            //}
            //System.out.println("new sort");
            
            int numberOfCollinearPoints = 0;
            int startIndex = -1;
            for(int j = 1; j < n - 1; j++){
                //System.out.println("NUM COLLINEAR = " + numberOfCollinearPoints);
                if(numberOfCollinearPoints >= 2){
                    //find highest y (or x if line is horizontal)
                    Point endpoint1 = current;
                    for(int z = 0; z < 3; z++){
                        if(endpoint1.compareTo(points[startIndex]) == -1) {
                            endpoint1 = points[startIndex];
                            continue;
                        }
                        if(endpoint1.compareTo(points[startIndex + 1]) == -1) {
                            endpoint1 = points[startIndex + 1];
                            continue;
                        }
                        if(endpoint1.compareTo(points[startIndex + 2]) == -1) endpoint1 = points[startIndex + 2];
                    }
                    
                    //find lowest y (or x if line is horizontal)
                    //Point endpoint2 = points[startIndex];
                    Point endpoint2 = current;
                    for(int z = 0; z < 3; z++){
                        if(endpoint2.compareTo(points[startIndex]) == 1) {
                            endpoint2 = points[startIndex];
                            continue;
                        }
                        if(endpoint2.compareTo(points[startIndex + 1]) == 1) {
                            endpoint2 = points[startIndex + 1];
                            continue;
                        }
                        if(endpoint2.compareTo(points[startIndex + 2]) == 1) endpoint2 = points[startIndex + 2];
                    }
                    
                    for(Point mp : maxPoints){ // check if this line segment has already been added
                        if (Objects.equals(mp.toString(), endpoint1.toString()) ){ // line segment already exists, so it won't be added again
                          numberOfCollinearPoints = 0;
                          startIndex = -1;
                          alreadyExists = true;
                          break; 
                        }
                    }
                    
                    if(!alreadyExists){
                        maxPoints.add(endpoint1);
                        lineSegments.add(new LineSegment(endpoint1, endpoint2));
                    }
                    numberOfCollinearPoints = 0;
                    startIndex = -1;
                    alreadyExists = false;
                    break;
                }
                if(current.slopeTo(points[j]) == current.slopeTo(points[j + 1])) {
                    if (startIndex == -1) startIndex = j;
                    numberOfCollinearPoints++;
                    continue;
                }
                else {
                    numberOfCollinearPoints = 0;
                    startIndex = -1;
                }
                
            }
        }
        
    }
    
    public int numberOfSegments() { // the number of line segments
        return lineSegments.size();
    }
    
    public LineSegment[] segments() { // the line segments
        //convert arraylist to array
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
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
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}