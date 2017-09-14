import java.util.*;

class FindKNearestPoints {
    private static class Point {
	int x, y;
	public Point(int x, int y) {
	    this.x = x;
	    this.y = y;
	}

	//@override
	public String toString() {
	    return x + " " + y;
	}
    }

    public static void main(String[] args) {
	Point origin = new Point(1,2);
	Point[] points = new Point[5];
	points[0] = new Point(1,1);
	points[1] = new Point(-1,1);
	points[2] = new Point(2,0);
	points[3] = new Point(3,-1);
	points[4] = new Point(-1,2);

	Point[] res = findPoints(points, origin, 2);
	for (Point p : res) System.out.println(p);
    }

    public static Point[] findPoints(Point[] array, Point origin, int k) {
	Point[] res = new Point[k];
	
	// sort by descending order of distance
	PriorityQueue<Point> q = 
	    new PriorityQueue<Point>(k,(Point a,Point b)->distance(b, origin)-distance(a,origin));
	for (Point p : array) {
	    q.offer(p);
	    if (q.size() > k) q.poll();
	}

	for (int i = k-1; i >= 0; i--) { // fill the array backforwards
	    res[i] = q.poll();
	}

	return res;
    }

    /* 
     * This function returns the distance between two points.
     * Note that it doesn't take square root of the result.
     * It is sufficient to use this value as distance.
     */
    private static int distance(Point a, Point b) {
	return (a.x-b.x) * (a.x-b.x) + (a.y-b.y) * (a.y-b.y);
    }
}
