interface Visitor {
} // marker interface

interface ExpressionVisitor extends Visitor {
    void visit(Expression expression);
}

interface DoubleExpressionVisitor extends Visitor {
    void visit(DoubleExpression expression);
}

interface AdditionExpressionVisitor extends Visitor {
    void visit(AdditionExpression expression);
}

abstract class Expression {
    public void accept(Visitor visitor) {
        if (visitor instanceof ExpressionVisitor) {
            ((ExpressionVisitor) visitor).visit(this);
        }
    }
}

class DoubleExpression extends Expression {
    private double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor instanceof DoubleExpressionVisitor) {
            ((DoubleExpressionVisitor) visitor).visit(this);
        }
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
    public void accept(Visitor visitor) {
        if (visitor instanceof AdditionExpressionVisitor) {
            ((AdditionExpressionVisitor) visitor).visit(this);
        }
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}

class ExpressionPrinter implements DoubleExpressionVisitor, AdditionExpressionVisitor {
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
        builder.append(((DoubleExpression) expression).getValue());
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}

public class AcyclicVisitor {
    public static void main(String[] args) {
        AdditionExpression expression = new AdditionExpression(new DoubleExpression(1),
                new AdditionExpression(new DoubleExpression(2), new DoubleExpression(3)));

        ExpressionPrinter printer = new ExpressionPrinter();
        printer.visit(expression);

        System.out.println(printer);
    }
}
