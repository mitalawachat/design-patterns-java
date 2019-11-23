interface ExpressionVisitor {
    void visit(DoubleExpression expression);

    void visit(AdditionExpression expression);
}

class ExpressionPrinter implements ExpressionVisitor {
    private StringBuilder builder = new StringBuilder();

    @Override
    public void visit(AdditionExpression expression) {
        builder.append("(");
        expression.getLeft().accept(this);
        builder.append("+");
        expression.getRight().accept(this);
        builder.append(")");
    }

    @Override
    public void visit(DoubleExpression expression) {
        builder.append(String.valueOf(expression.getValue()));
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}

class ExpressionCalculator implements ExpressionVisitor {
    public double result;

    @Override
    public void visit(AdditionExpression expression) {
        expression.getLeft().accept(this);
        double left = result;

        expression.getRight().accept(this);
        double right = result;

        result = left + right;
    }

    @Override
    public void visit(DoubleExpression expression) {
        result = expression.getValue();
    }

    @Override
    public String toString() {
        return String.valueOf(result);
    }
}

abstract class Expression {
    public abstract void accept(ExpressionVisitor visitor);
}

class DoubleExpression extends Expression {
    private double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
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

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}

public class DoubleDispatch {
    public static void main(String[] args) {
        AdditionExpression expression = new AdditionExpression(new DoubleExpression(1),
                new AdditionExpression(new DoubleExpression(2), new DoubleExpression(3)));

        ExpressionPrinter printer = new ExpressionPrinter();
        printer.visit(expression);

        ExpressionCalculator calculator = new ExpressionCalculator();
        calculator.visit(expression);

        System.out.println(printer + " = " + calculator);
    }
}
