class Person {
    String name;
    String position;

    @Override
    public String toString() {
        return "Person [name=" + name + ", position=" + position + "]";
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>> {
    protected Person person = new Person();

    public SELF name(String name) {
        person.name = name;
        return self();
    }

    protected SELF self() {
        return (SELF) this;
    }

    public Person build() {
        return person;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

    public EmployeeBuilder position(String position) {
        person.position = position;
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

public class FluentBuilderRecursiveGenerics {
    public static void main(String[] args) {
        Person person = new EmployeeBuilder()
            .name("Johny")
            .position("CEO")
            .build();
        System.out.println(person);
    }
}
