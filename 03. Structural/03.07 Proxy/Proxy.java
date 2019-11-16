interface Drivable {
    void drive();
}

class Car implements Drivable {
    Driver driver;

    public Car(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void drive() {
        System.out.println("Car being driven!");
    }
}

class Driver {
    int age;

    public Driver(int age) {
        this.age = age;
    }
}

class CarProxy extends Car {
    public CarProxy(Driver driver) {
        super(driver);
    }

    @Override
    public void drive() {
        if (driver.age >= 16) {
            super.drive();
        } else {
            System.out.println("Driver too young!");
        }
    }
}

class Proxy {
    public static void main(String[] args) {
        Car car1 = new Car(new Driver(12));
        car1.drive();

        Car car2 = new CarProxy(new Driver(12));
        car2.drive();
    }
}
