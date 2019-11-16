import java.util.function.Supplier;

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

class ColoredShape<T extends Shape> implements Shape {
    private Shape shape;
    private String color;

    public ColoredShape(Supplier<? extends T> constructor, String color) {
        super();
        this.shape = constructor.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " - has " + color + " color";
    }
}

class TransparentShape<T extends Shape> implements Shape {
    private Shape shape;
    private float transparency;

    public TransparentShape(Supplier<? extends T> constructor, float transparency) {
        super();
        this.shape = constructor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " - has " + transparency + " transparency";
    }
}

public class StaticDecorator {
    public static void main(String[] args) {
        ColoredShape<Square> blueSquare = new ColoredShape<>(() -> new Square(20), "blue");
        System.out.println(blueSquare.info());

        TransparentShape<ColoredShape<Circle>> transparentShape = new TransparentShape<>(
                () -> new ColoredShape<>(() -> new Circle(5), "green"), 0.5f);
        System.out.println(transparentShape.info());
    }
}
