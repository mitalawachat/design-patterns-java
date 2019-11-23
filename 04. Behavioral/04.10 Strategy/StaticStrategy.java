import java.util.List;
import java.util.function.Supplier;

enum OutputFormat {
    MARKDOWN, HTML
}

interface ListStrategy {
    default void start(StringBuilder builder) {
    }

    void addListItem(StringBuilder builder, String item);

    default void end(StringBuilder builder) {
    }
}

class MarkdownListStrategy implements ListStrategy {
    @Override
    public void addListItem(StringBuilder builder, String item) {
        builder.append("* ").append(item).append(System.lineSeparator());
    }
}

class HTMLListStrategy implements ListStrategy {
    @Override
    public void start(StringBuilder builder) {
        builder.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder builder) {
        builder.append("</ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder builder, String item) {
        builder.append("<li>").append(item).append("</li>").append(System.lineSeparator());
    }
}

class TextProcessor<LS extends ListStrategy> {

    private StringBuilder builder = new StringBuilder();
    private LS listStrategy;

    public TextProcessor(Supplier<? extends LS> ctor) {
        listStrategy = ctor.get();
    }

    public void appendList(List<String> items) {
        listStrategy.start(builder);
        for (String item : items) {
            listStrategy.addListItem(builder, item);
        }
        listStrategy.end(builder);
    }

    public void clear() {
        builder.setLength(0);
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}

public class StaticStrategy {
    public static void main(String[] args) {
        TextProcessor<MarkdownListStrategy> text1 = new TextProcessor<>(MarkdownListStrategy::new);
        text1.appendList(List.of("apple", "banana", "carrot"));
        System.out.println(text1);

        TextProcessor<HTMLListStrategy> text2 = new TextProcessor<>(HTMLListStrategy::new);
        text2.appendList(List.of("apple", "banana", "carrot"));
        System.out.println(text2);
    }
}
