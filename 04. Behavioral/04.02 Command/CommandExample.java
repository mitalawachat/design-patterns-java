import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BankAccount {
    private int balance;
    private int overdraftLimit = -500;

    public boolean deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ", balance is now " + balance);
        return true;
    }

    public boolean withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ", balance is now " + balance);
            return true;
        } else {
            System.out.println("Low balance...");
            return false;
        }
    }

    @Override
    public String toString() {
        return "BankAccount [balance=" + balance + ", overdraftLimit=" + overdraftLimit + "]";
    }
}

interface Command {
    void call();

    void undo();
}

class BankAccountCommand implements Command {

    public enum Action {
        DEPOSIT, WITHRAW
    }

    private BankAccount account;
    private Action action;
    private int amount;
    private boolean success;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void call() {
        switch (action) {
        case DEPOSIT: {
            success = account.deposit(amount);
            break;
        }
        case WITHRAW: {
            success = account.withdraw(amount);
            break;
        }
        }
    }

    @Override
    public void undo() {
        if (!success) {
            return;
        }

        switch (action) {
        case DEPOSIT: {
            account.withdraw(amount);
            break;
        }
        case WITHRAW: {
            account.deposit(amount);
            break;
        }
        }
    }
}

public class CommandExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        System.out.println(account);

        List<BankAccountCommand> commands = new ArrayList<>();
        commands.add(new BankAccountCommand(account, BankAccountCommand.Action.DEPOSIT, 100));
        commands.add(new BankAccountCommand(account, BankAccountCommand.Action.WITHRAW, 1000));

        for (Command command : commands) {
            command.call();
        }
        System.out.println(account);

        Collections.reverse(commands);
        for (Command command : commands) {
            command.undo();
        }
        System.out.println(account);
    }
}
