import java.util.Arrays;

class Address {
    String[] streets;
    String city;

    public Address(String[] streets, String city) {
        this.streets = streets;
        this.city = city;
    }

    public Address(Address address) {
        this.streets = address.streets.clone();
        this.city = address.city;
    }

    @Override
    public String toString() {
        return "Address [streets=" + Arrays.toString(streets) + ", city=" + city + "]";
    }
}

class Person {
    String fullname;
    Address address;

    public Person(String fullname, Address address) {
        this.fullname = fullname;
        this.address = address;
    }

    public Person(Person person) {
        this.fullname = person.fullname;
        this.address = new Address(person.address);
    }

    @Override
    public String toString() {
        return "Person [fullname=" + fullname + ", address=" + address.toString() + "]";
    }
}

public class PrototypeCopyConstructor {
    public static void main(String[] args) {
        Person john = new Person("John Smith", new Address(new String[] { "Street #1" }, "London"));

        Person jane = new Person(john);
        jane.fullname = "Jane Smith";
        jane.address.streets[0] = "Street #2";
        jane.address.city = "New York";

        System.out.println(john);
        System.out.println(jane);
    }
}
