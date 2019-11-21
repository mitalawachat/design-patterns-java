import java.lang.reflect.Proxy;

interface Log {
    void info(String msg);

    void warn(String msg);
}

class ConsoleLog implements Log {
    public void info(final String msg) {
        System.out.println("INFO: " + msg);
    }

    public void warn(final String msg) {
        System.out.println("WARN: " + msg);
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

public class NullObjectDynamic {

    @SuppressWarnings("unchecked")
    public static <T> T noOperation(final Class<T> interfase) {
        return (T) Proxy.newProxyInstance(interfase.getClassLoader(), new Class<?>[] { interfase },
                (proxy, method, argument) -> {
                    if (method.getReturnType().equals(Void.TYPE))
                        return null;
                    else
                        return method.getReturnType().getConstructor().newInstance();
                });
    }

    public static void main(final String[] args) {
        // Log log = new ConsoleLog();
        final Log log = noOperation(Log.class);
        final BankAccount account = new BankAccount(log);
        account.deposit(100);
    }

}
