import java.io.File;
import java.io.IOException;

class Singleton {

    private static Singleton instance;
    static {
        try {
            instance = new Singleton();
        } catch (Exception e) {
            System.err.println("Failed to create singleton. " + e.getMessage());
        }
    }

    private Singleton() throws Exception {
        System.out.println("Singleton is initializing...");
        File.createTempFile(".", "."); // this line throws exception
    }

    public static Singleton getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Singleton";
    }
}

public class SingletonStaticBlock {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);
    }
}
