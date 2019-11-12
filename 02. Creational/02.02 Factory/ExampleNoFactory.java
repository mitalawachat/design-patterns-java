enum CoordinateSystem {
    CARTESIAN, POLAR;
}

class Point {
    private double x, y;

    public Point(double a, double b, CoordinateSystem coordinateSystem) {
        switch (coordinateSystem) {
        case CARTESIAN:
            this.x = a;
            this.y = b;
            break;
        case POLAR:
            this.x = a * Math.cos(b);
            this.y = a * Math.sin(b);
            break;
        }
    }

    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + "]";
    }
}

public class ExampleNoFactory {
    public static void main(String[] args) {
        Point point = new Point(10, 20, CoordinateSystem.CARTESIAN);
        System.out.println(point);
    }
}
