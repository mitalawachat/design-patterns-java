import java.io.Serializable;

class Singleton implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    protected Object readResolve() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Singleton";
    }
}

public class SingletonSerializable {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);
    }
}
