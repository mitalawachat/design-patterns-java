import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<TArgs> {
    private int count = 0;
    private Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();

    public Subscription addHandler(Consumer<TArgs> handler) {
        int id = count;
        handlers.put(count++, handler);
        return new Subscription(this, id);
    }

    public void fire(TArgs args) {
        for (Consumer<TArgs> consumer : handlers.values()) {
            consumer.accept(args);
        }
    }

    public class Subscription implements AutoCloseable {
        private Event<TArgs> event;
        private int id;

        public Subscription(Event<TArgs> event, int id) {
            this.event = event;
            this.id = id;
        }

        @Override
        public void close() {
            event.handlers.remove(id);
        }
    }
}

class PropertyChangeEventArgs<T> {
    public T source;
    public String propertyName;
    public Object newValue;

    public PropertyChangeEventArgs(T source, String propertyName, Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }
}

class Person {
    public Event<PropertyChangeEventArgs<Person>> propertyChanged = new Event<>();

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) {
            return;
        }
        this.age = age;
        propertyChanged.fire(new PropertyChangeEventArgs<Person>(this, "age", age));
    }
}

public class EventExample {
    public static void main(String[] args) {
        Person person = new Person();
        Event.Subscription subscription = person.propertyChanged
                .addHandler(x -> System.out.println("Person's " + x.propertyName + " changed to " + x.newValue));
        person.setAge(16);
        subscription.close();
        person.setAge(17);
    }
}
