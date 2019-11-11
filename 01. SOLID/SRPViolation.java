import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class Journal {

    private List<String> list = new ArrayList<>();

    public void add(String item) {
        list.add(item);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), list);
    }

    public void saveFile(String fileName) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(this.toString());
        }
    }
}

public class SRPViolation {
    public static void main(String[] args) throws Exception {
        Journal journal = new Journal();
        journal.add("I did a thing today!");
        journal.add("I did another thing today!");
        journal.saveFile("file.txt");
    }
}
