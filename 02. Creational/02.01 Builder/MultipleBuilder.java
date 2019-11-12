class Person {
    // Personal info
    String name;

    // Address
    String street, city;

    // Employementc
    String company, position;

    @Override
    public String toString() {
        return "Person [name=" + name + ", company=" + company + ", position=" + position + ", street=" + street
                + ", city=" + city + "]";
    }
}

class PersonBuilder {
    protected Person person = new Person();

    public PersonBuilder name(String name) {
        person.name = name;
        return this;
    }

    public PersonAddressBuilder address() {
        return new PersonAddressBuilder(person);
    }

    public PersonEmploymentBuilder employment() {
        return new PersonEmploymentBuilder(person);
    }

    public Person build() {
        return person;
    }
}

class PersonAddressBuilder extends PersonBuilder {
    public PersonAddressBuilder(Person person) {
        this.person = person;
    }

    public PersonAddressBuilder street(String street) {
        person.street = street;
        return this;
    }

    public PersonAddressBuilder city(String city) {
        person.city = city;
        return this;
    }
}

class PersonEmploymentBuilder extends PersonBuilder {
    public PersonEmploymentBuilder(Person person) {
        this.person = person;
    }

    public PersonEmploymentBuilder company(String company) {
        person.company = company;
        return this;
    }

    public PersonEmploymentBuilder position(String position) {
        person.position = position;
        return this;
    }
}

public class MultipleBuilder {
    public static void main(String[] args) {
        Person person = new PersonBuilder()
            .name("Shaggy")
            .address()
            .street("Mystery Lane")
            .city("Mystery Machine")
            .employment()
            .company("Mystery Inc")
            .position("Slacker")
            .build();
        System.out.println(person);
    }
}