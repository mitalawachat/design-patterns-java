interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

interface Faxer {
    void fax();
}

class MultiPrinter implements Printer, Scanner {
    @Override
    public void print() {
        System.out.println("Printing...");
    }

    @Override
    public void scan() {
        System.out.println("Scanning...");
    }
}

public class ISP {
    public static void main(String[] args) {
        MultiPrinter multiPrinter = new MultiPrinter();
        multiPrinter.print();
        multiPrinter.scan();
    }
}
