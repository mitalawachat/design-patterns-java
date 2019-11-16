import java.util.*;

class Sentence {
    private String plainText;
    private Map<Integer, WordToken> tokens = new HashMap<>();

    public Sentence(String plainText) {
        this.plainText = plainText;
    }

    public WordToken getWord(int index) {
        if (tokens.containsKey(index)) {
            return tokens.get(index);
        }
        WordToken token = new WordToken(index);
        tokens.put(index, token);
        return token;
    }

    @Override
    public String toString() {
        String words[] = plainText.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            if (tokens.containsKey(i) && tokens.get(i).capitalize) {
                words[i] = words[i].toUpperCase();
            }
        }

        return String.join(" ", words);
    }

    class WordToken {
        int index;

        public WordToken(int index) {
            this.index = index;
        }

        public boolean capitalize;
    }
}

public class FlyweightExample3 {
    public static void main(String[] args) {
        Sentence sentence = new Sentence("hello world");
        sentence.getWord(1).capitalize = true;
        System.out.println(sentence);
    }
}