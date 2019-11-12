class Person {

    private String fullName;
    private int age;

    public Person(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [fullName=" + fullName + ", age=" + age + "]";
    }

    static class Builder {

        private String fullName;
        private int age;

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Person build() {
            return new Person(fullName, age);
        }
    }
}

public class FluentBuilder {
    public static void main(String[] args) {
        Person person = new Person.Builder()
            .fullName("John Wick")
            .age(50)
            .build();
        System.out.println(person);
    }
}
