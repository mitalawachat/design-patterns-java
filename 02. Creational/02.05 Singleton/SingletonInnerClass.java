import sun.security.jca.GetInstance;

class Singleton {

    private InnerStaticSingleton() {
    }

    private static class SingletonInner {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInner.INSTANCE;
    }

    @Override
    public String toString() {
        return "Singleton";
    }
}

public class SingletonInnerClass {
    public static void main(String[] args) {
        InnterStaticSingleton instance = InnerStaticSingleton.getInstance();
        System.out.println(instance);
    }
}
