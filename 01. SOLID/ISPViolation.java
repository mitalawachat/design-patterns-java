interface Machine {
    void print();

    void scan();

    void fax();
}

class MultiPrinter implements Machine {
    @Override
    public void print() {
        System.out.println("Printing...");
    }

    @Override
    public void scan() {
        System.out.println("Scanning...");
    }

    @Override
    public void fax() {
        throw new UnsupportedOperationException("fax not supported");
    }
}

public class ISPViolation {
    public static void main(String[] args) {
        MultiPrinter multiPrinter = new MultiPrinter();
        multiPrinter.print();
        multiPrinter.scan();
        multiPrinter.fax();
    }
}
