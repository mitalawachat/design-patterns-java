class MonostateEmployee {

    private static String name;
    private static int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        MonostateEmployee.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        MonostateEmployee.age = age;
    }

    @Override
    public String toString() {
        return "Employee [name=" + MonostateEmployee.name + ", age=" + MonostateEmployee.age + "]";
    }
}

public class SingletonMonostate {
    public static void main(String[] args) {
        MonostateEmployee e1 = new MonostateEmployee();
        e1.setName("Scooby");
        e1.setAge(10);
        System.out.println(e1);
    }
}
