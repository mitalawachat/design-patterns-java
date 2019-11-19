class BankAccount {
    private String name;
    private int balance;

    public BankAccount(String name) {
        this.name = name;
    }

    public Memento deposit(int amount) {
        this.balance += amount;
        return new Memento(balance);
    }

    public void restore(Memento memento) {
        this.balance = memento.getBalance();
    }

    @Override
    public String toString() {
        return "BankAccount [name=" + name + ", balance=" + balance + "]";
    }
}

class Memento {
    private int balance;

    public Memento(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}

public class MementoExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("John Smith");
        System.out.println(account);

        Memento m1 = account.deposit(50);
        System.out.println(account);

        Memento m2 = account.deposit(25);
        System.out.println(account);

        account.restore(m1);
        System.out.println(account);

        account.restore(m2);
        System.out.println(account);
    }
}
