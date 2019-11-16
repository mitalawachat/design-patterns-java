import java.util.ArrayList;
import java.util.List;

class FormattedText {

    private String plainText;
    private List<TextRange> formatting = new ArrayList<>();

    public FormattedText(String plainText) {
        this.plainText = plainText;
    }

    public TextRange getRange(int start, int end) {
        TextRange range = new TextRange(start, end);
        formatting.add(range);
        return range;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            for(TextRange range : formatting) {
                if(range.covers(i) && range.capitalize) {
                    c = Character.toUpperCase(c);
                }                
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public class TextRange {
        public int start, end;
        public boolean capitalize, bold, italic;

        public TextRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean covers(int position) {
            return position >= start && position <= end;
        }
    }

}

public class FlyweightExample2 {
    public static void main(String[] args) {
        FormattedText formattedText = new FormattedText("wingardium leviosa");
        formattedText.getRange(0, 10).capitalize = true;
        System.out.println(formattedText);
    }
}
