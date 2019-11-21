import java.util.ArrayList;
import java.util.List;

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

interface Observer<T> {
    void handle(PropertyChangeEventArgs<T> args);
}

class Observable<T> {
    private List<Observer<T>> observers = new ArrayList<>();

    public void subscribe(Observer<T> observer) {
        observers.add(observer);
    }

    protected void propertyChanged(T source, String propertyName, Object newValue) {
        for (Observer<T> o : observers) {
            o.handle(new PropertyChangeEventArgs<T>(source, propertyName, newValue));
        }
    }
}

class Person extends Observable<Person> {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) {
            return;
        }
        this.age = age;
        propertyChanged(this, "age", age);
    }
}

public class CustomObserver implements Observer<Person> {

    public CustomObserver() {
        super();
        Person person = new Person();
        person.subscribe(this);
        person.setAge(20);
    }

    @Override
    public void handle(PropertyChangeEventArgs<Person> args) {
        System.out.println("Person's " + args.propertyName + " changed to " + args.newValue);
    }

    public static void main(String[] args) {
        new ObserverPattern();
    }
}
