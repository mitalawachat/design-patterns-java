import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token {
    enum Type {
        INTEGER, PLUS, MINUS, LEFT_PARANTHESIS, RIGHT_PARANTHESIS
    }

    Type type;
    String text;

    Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}

interface Element {
    int eval();
}

class Integer implements Element {
    int value;

    Integer(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}

class BinaryOperation implements Element {
    enum Type {
        ADD, SUBSTRACT
    }

    Type type;
    Element left, right;

    @Override
    public int eval() {
        switch (type) {
        case ADD: {
            return left.eval() + right.eval();
        }
        case SUBSTRACT: {
            return left.eval() - right.eval();
        }
        default: {
            return 0;
        }
        }
    }
}

class Parser {
    static Element parse(List<Token> tokens) {
        BinaryOperation result = new BinaryOperation();
        boolean haveLHS = false;

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            switch (token.type) {
            case INTEGER: {
                Integer integer = new Integer(java.lang.Integer.parseInt(token.text));
                if (!haveLHS) {
                    result.left = integer;
                    haveLHS = true;
                } else {
                    result.right = integer;
                }
                break;
            }
            case PLUS: {
                result.type = BinaryOperation.Type.ADD;
                break;
            }
            case MINUS: {
                result.type = BinaryOperation.Type.SUBSTRACT;
                break;
            }
            case LEFT_PARANTHESIS: {
                int j = i; // j = location of RIGHT_PARANTHESIS
                for (; j < tokens.size(); j++) {
                    if (tokens.get(j).type == Token.Type.RIGHT_PARANTHESIS) {
                        break;
                    }
                }
                List<Token> subExpression = tokens.stream().skip(i + 1).limit(j - i - 1).collect(Collectors.toList());
                Element element = parse(subExpression);
                if (!haveLHS) {
                    result.left = element;
                    haveLHS = true;
                } else {
                    result.right = element;
                }
                i = j;
                break;
            }
            }
        }

        return result;
    }
}

class Lexer {
    static List<Token> lex(String input) {
        List<Token> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
            case '+':
                result.add(new Token(Token.Type.PLUS, "+"));
                break;
            case '-':
                result.add(new Token(Token.Type.MINUS, "-"));
                break;
            case '(':
                result.add(new Token(Token.Type.LEFT_PARANTHESIS, "("));
                break;
            case ')':
                result.add(new Token(Token.Type.RIGHT_PARANTHESIS, ")"));
                break;
            default:
                StringBuilder builder = new StringBuilder("" + input.charAt(i));
                for (int j = i + 1; j < input.length(); j++) {
                    if (Character.isDigit(input.charAt(j))) {
                        builder.append(input.charAt(j));
                        i++;
                    } else {
                        result.add(new Token(Token.Type.INTEGER, builder.toString()));
                        break;
                    }
                }
                break;
            }
        }
        return result;
    }
}

public class Interpreter {
    public static void main(String[] args) {
        String input = "(13+4)-(12+1)"; // no nesting of brackets allowed in this example
        List<Token> tokens = Lexer.lex(input);
        System.out.println(tokens.stream().map(t -> t.toString()).collect(Collectors.joining("\t")));

        Element parsed = Parser.parse(tokens);
        System.out.println(input + " = " + parsed.eval());
    }
}
