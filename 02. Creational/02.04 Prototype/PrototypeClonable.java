import java.util.Arrays;

class Address implements Cloneable {
    String[] streets;
    String city;

    public Address(String[] streets, String city) {
        this.streets = streets;
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Address(this.streets.clone(), this.city);
    }

    @Override
    public String toString() {
        return "Address [streets=" + Arrays.toString(streets) + ", city=" + city + "]";
    }
}

class Person implements Cloneable {
    String fullname;
    Address address;

    public Person(String fullname, Address address) {
        this.fullname = fullname;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Person(this.fullname, (Address) this.address.clone());
    }

    @Override
    public String toString() {
        return "Person [fullname=" + fullname + ", address=" + address.toString() + "]";
    }
}

public class PrototypeClonable {
    public static void main(String[] args) throws Exception {
        Person john = new Person("John Smith", new Address(new String[] { "Street #1" }, "London"));

        Person jane = (Person) john.clone();
        jane.fullname = "Jane Smith";
        jane.address.streets[0] = "Street #2";
        jane.address.city = "New York";

        System.out.println(john);
        System.out.println(jane);
    }
}
