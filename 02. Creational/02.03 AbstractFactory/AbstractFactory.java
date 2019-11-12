
abstract class Computer {

    public abstract String getRAM();

    public abstract String getHDD();

    public abstract String getCPU();

    @Override
    public String toString() {
        return "Computer [cpu=" + getCPU() + ", hdd=" + getHDD() + ", ram=" + getRAM() + "]";
    }
}

class PC extends Computer {
    private String ram;
    private String hdd;
    private String cpu;

    public PC(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    public String getRAM() {
        return ram;
    }

    public String getHDD() {
        return hdd;
    }

    public String getCPU() {
        return cpu;
    }
}

class Server extends Computer {
    private String ram;
    private String hdd;
    private String cpu;

    public Server(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    public String getRAM() {
        return ram;
    }

    public String getHDD() {
        return hdd;
    }

    public String getCPU() {
        return cpu;
    }
}

interface IComputerFactory {
    public Computer create();
}

class PCFactory implements IComputerFactory {

    private String ram;
    private String hdd;
    private String cpu;

    public PCFactory(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    @Override
    public Computer create() {
        return new PC(ram, hdd, cpu);
    }
}

class ServerFactory implements IComputerFactory {

    private String ram;
    private String hdd;
    private String cpu;

    public ServerFactory(String ram, String hdd, String cpu) {
        this.ram = ram;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    @Override
    public Computer create() {
        return new Server(ram, hdd, cpu);
    }
}

class ComputerFactory {
    public static Computer create(IComputerFactory factory) {
        return factory.create();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        Computer computer = ComputerFactory.create(new ServerFactory("16 GB", "1 TB", "2.4 GHz"));
        System.out.println(computer);
    }
}
