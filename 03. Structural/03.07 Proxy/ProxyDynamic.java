import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface Human {
    void walk();

    void talk();
}

class Person implements Human {
    @Override
    public void walk() {
        System.out.println("Walking...");
    }

    @Override
    public void talk() {
        System.out.println("Talking...");
    }
}

class LoggingHandler implements InvocationHandler {

    private final Object target;
    private Map<String, Integer> calls = new HashMap<>();

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();

        if (name.contains("toString")) {
            return calls.toString();
        }

        calls.merge(name, 1, Integer::sum);
        return method.invoke(target, args);
    }
}

public class ProxyDynamic {

    public static <T> T withLogging(T target, Class<T> interfase) {
        return (T) Proxy.newProxyInstance(interfase.getClassLoader(), new Class<?>[] { interfase },
                new LoggingHandler(target));
    }

    public static void main(String[] args) {
        Person person = new Person();
        Human loggedHuman = withLogging(person, Human.class);
        loggedHuman.walk();
        loggedHuman.talk();
        loggedHuman.talk();
        System.out.println(loggedHuman);
    }
}
