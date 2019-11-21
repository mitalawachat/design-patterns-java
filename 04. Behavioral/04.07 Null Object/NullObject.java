interface Log {
    void info(String msg);

    void warn(String msg);
}

class ConsoleLog implements Log {
    public void info(String msg) {
        System.out.println("INFO: " + msg);
    }

    public void warn(String msg) {
        System.out.println("WARN: " + msg);
    }
}

class NullLog implements Log {
    public void info(String msg) {
        // do nothing
    }

    public void warn(String msg) {
        // do nothing
    }
}

class BankAccount {
    private final Log log;
    private int balance;

    public BankAccount(final Log log) {
        this.log = log;
    }

    public void deposit(final int amount) {
        this.balance += amount;
        log.info("Deposited " + amount + "in account");
    }
}

public class NullObject {
    public static void main(String[] args) {
        // Log log = new ConsoleLog();
        Log log = new NullLog();
        BankAccount account = new BankAccount(log);
        account.deposit(100);
    }
}
