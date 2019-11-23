abstract class Expression {
    public abstract void print(StringBuilder builder);
}

class DoubleExpression extends Expression {
    private double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void print(StringBuilder builder) {
        builder.append(String.valueOf(value));
    }
}

class AdditionExpression extends Expression {
    private Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print(StringBuilder builder) {
        builder.append("(");
        left.print(builder);
        builder.append("+");
        right.print(builder);
        builder.append(")");
    }
}

class ExpressionPrinter {
    public static void print(Expression e, StringBuilder builder) {

    }
}

public class IntrusiveVisitor {
    public static void main(String[] args) {
        AdditionExpression expression = new AdditionExpression(new DoubleExpression(1),
                new AdditionExpression(new DoubleExpression(2), new DoubleExpression(3)));

        StringBuilder builder = new StringBuilder();
        expression.print(builder);
        System.out.println(builder);
    }
}
