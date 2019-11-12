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

class ComputerFactory {
    public static Computer getComputer(String type, String ram, String hdd, String cpu) {
        if ("pc".equalsIgnoreCase(type)) {
            return new PC(ram, hdd, cpu);
        }
        if ("server".equalsIgnoreCase(type)) {
            return new Server(ram, hdd, cpu);
        }
        return null;
    }
}

public class FactorySuper {
    public static void main(String[] args) {
        Computer computer = ComputerFactory.getComputer("server", "16 GB", "1 TB", "2.4 GHz");
        System.out.println(computer);
    }
}
