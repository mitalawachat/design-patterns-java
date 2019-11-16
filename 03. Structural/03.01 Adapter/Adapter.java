import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + "]";
    }
}

class Line {
    Point start, end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Line [start=" + start.toString() + ", end=" + end.toString() + "]";
    }
}

class Vector extends ArrayList<Line> {
    private static final long serialVersionUID = 1L;
}

class VectorRectangle extends Vector {
    private static final long serialVersionUID = 1L;

    public VectorRectangle(int x, int y, int width, int height) {
        add(new Line(new Point(x, y), new Point(x + width, y)));
        add(new Line(new Point(x + width, y), new Point(x + width, y + height)));
        add(new Line(new Point(x, y), new Point(x, y + height)));
        add(new Line(new Point(x, y + height), new Point(x + width, y + height)));
    }
}

class LineToPointAdapter extends ArrayList<Point> {
    private static final long serialVersionUID = 1L;
    private static int count = 0;

    public LineToPointAdapter(Line line) {
        String message = String.format("%d Generating points for line [%d,%d]-[%d,%d] (no caching)", ++count,
                line.start.x, line.start.y, line.end.x, line.end.y);
        System.out.println(message);

        int left = Math.min(line.start.x, line.end.x);
        int right = Math.max(line.start.x, line.end.x);
        int top = Math.min(line.start.y, line.end.y);
        int bottom = Math.max(line.start.y, line.end.y);
        int dx = right - left;
        int dy = line.end.y - line.start.y;

        if (dx == 0) {
            for (int y = top; y <= bottom; ++y) {
                add(new Point(left, y));
            }
        } else if (dy == 0) {
            for (int x = left; x <= right; ++x) {
                add(new Point(x, top));
            }
        }
    }
}

public class Adapter {
    private final static List<Vector> vectors = new ArrayList<>(Arrays.asList(new VectorRectangle(1, 1, 10, 10),
            new VectorRectangle(2, 2, 6, 6), new VectorRectangle(2, 2, 6, 6)));

    public static void drawPoint(Point p) {
        System.out.println("Printing " + p.toString());
    }

    private static void draw() {
        for (Vector vector : vectors) {
            for (Line line : vector) {
                LineToPointAdapter adapter = new LineToPointAdapter(line);
                adapter.forEach(Adapter::drawPoint);
            }
        }
    }

    public static void main(String[] args) {
        draw();
    }
}
