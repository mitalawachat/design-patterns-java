import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    String[] streets;
    String city;

    public Address(String[] streets, String city) {
        this.streets = streets;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address [streets=" + Arrays.toString(streets) + ", city=" + city + "]";
    }
}

class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    String fullname;
    Address address;

    public Person(String fullname, Address address) {
        this.fullname = fullname;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person [fullname=" + fullname + ", address=" + address.toString() + "]";
    }
}

class Cloner<T> {
    public T clone(T t, Class<T> clazz) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(bos);
        outputStream.writeObject(t);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream inputStream = new ObjectInputStream(bis);
        Object response = inputStream.readObject();

        return clazz.isInstance(response) ? clazz.cast(response) : null;
    }
}

public class PrototypeSerialization {
    public static void main(String[] args) throws Exception {
        Person john = new Person("John Smith", new Address(new String[] { "Street #1" }, "London"));

        Person jane = new Cloner<Person>().clone(john, Person.class);
        jane.fullname = "Jane Smith";
        jane.address.streets[0] = "Street #2";
        jane.address.city = "New York";

        System.out.println(john);
        System.out.println(jane);
    }
}
