import java.util.List;

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

class TextProcessor {
    private StringBuilder builder = new StringBuilder();
    private ListStrategy listStrategy;

    public TextProcessor(OutputFormat format) {
        setOutputFormat(format);
    }

    public void setOutputFormat(OutputFormat format) {
        switch(format) {
            case MARKDOWN:
                listStrategy = new MarkdownListStrategy();
                break;
            case HTML:
                listStrategy = new HTMLListStrategy();
                break;
        }
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

public class DynamicStrategy {
    public static void main(String[] args) {
        TextProcessor text = new TextProcessor(OutputFormat.MARKDOWN);
        text.appendList(List.of("apple", "banana", "carrot"));
        System.out.println(text);

        text.clear();

        text.setOutputFormat(OutputFormat.HTML);
        text.appendList(List.of("apple", "banana", "carrot"));
        System.out.println(text);
    }
}