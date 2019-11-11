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
}

interface JournalRepository {
    public void save() throws Exception;
}

class JournalFileRepository implements JournalRepository {

    private Journal journal;
    private String fileName;

    public JournalFileRepository(Journal journal, String fileName) {
        this.journal = journal;
        this.fileName = fileName;
    }

    @Override
    public void save() throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(this.fileName)) {
            writer.println(journal.toString());
        }
    }
}

public class SRP {
    public static void main(String[] args) throws Exception {
        Journal journal = new Journal();
        journal.add("I did a thing today!");
        journal.add("I did another thing today!");

        JournalRepository journalRepository = new JournalFileRepository(journal, "file.txt");
        journalRepository.save();
    }
}
