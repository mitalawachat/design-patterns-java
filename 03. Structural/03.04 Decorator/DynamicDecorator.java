interface Shape {
    public String info();
}

class Circle implements Shape {
    private float radius;

    public Circle(float radius) {
        super();
        this.radius = radius;
    }

    public void resize(float factor) {
        this.radius *= factor;
    }

    @Override
    public String info() {
        return "Circle - has radius " + radius;
    }
}

class Square implements Shape {
    private float side;

    public Square(float side) {
        super();
        this.side = side;
    }

    @Override
    public String info() {
        return "Square - has side " + side;
    }
}

class ColoredShape implements Shape {
    private Shape shape;
    private String color;

    public ColoredShape(Shape shape, String color) {
        super();
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " - has " + color + " color";
    }
}

class TransparentShape implements Shape {
    private Shape shape;
    private float transparency;

    public TransparentShape(Shape shape, float transparency) {
        super();
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " - has " + transparency + " transparency";
    }
}

public class DynamicDecorator {
    public static void main(String[] args) {
        Shape circle = new Circle(10);
        System.out.println(circle.info());

        ColoredShape blueSquare = new ColoredShape(new Square(20), "blue");
        System.out.println(blueSquare.info());

        TransparentShape transparentShape = new TransparentShape(new ColoredShape(new Circle(5), "green"), 0.5f);
        System.out.println(transparentShape.info());
    }
}
