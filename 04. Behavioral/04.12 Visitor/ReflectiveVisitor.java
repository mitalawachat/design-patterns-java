abstract class Expression {
}

class DoubleExpression extends Expression {
    private double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

class AdditionExpression extends Expression {
    private Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}

class ExpressionPrinter {
    public static void print(Expression expression, StringBuilder builder) {
        if (expression.getClass() == DoubleExpression.class) {
            builder.append(((DoubleExpression) expression).getValue());
        } else if (expression.getClass() == AdditionExpression.class) {
            AdditionExpression addExpression = (AdditionExpression) expression;
            builder.append("(");
            print(addExpression.getLeft(), builder);
            builder.append("+");
            print(addExpression.getRight(), builder);
            builder.append(")");
        }
    }
}

public class ReflectiveVisitor {
    public static void main(String[] args) {
        AdditionExpression expression = new AdditionExpression(new DoubleExpression(1),
                new AdditionExpression(new DoubleExpression(2), new DoubleExpression(3)));

        StringBuilder builder = new StringBuilder();
        ExpressionPrinter.print(expression, builder);
        System.out.println(builder);
    }
}
