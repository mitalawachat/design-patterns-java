class State {
    void on(LightSwitch lightSwitch) {
        System.out.println("Light is already on!");
    }

    void off(LightSwitch lightSwitch) {
        System.out.println("Light is already off!");
    }
}

class OnState extends State {
    public OnState() {
        System.out.println("Light is turned on");
    }

    @Override
    void off(LightSwitch lightSwitch) {
        System.out.println("Switching light off...");
        lightSwitch.setState(new OffState());
    }
}

class OffState extends State {
    public OffState() {
        System.out.println("Light is turned off");
    }

    @Override
    void on(LightSwitch lightSwitch) {
        System.out.println("Switching light on...");
        lightSwitch.setState(new OnState());
    }
}

class LightSwitch {
    State state;

    public LightSwitch() {
        state = new OffState();
    }

    public void setState(State state) {
        this.state = state;
    }

    void on() {
        state.on(this);
    }

    void off() {
        state.off(this);
    }
}

public class StateExample1 {
    public static void main(String[] args) {
        LightSwitch lightSwitch = new LightSwitch();
        lightSwitch.on();
        lightSwitch.off();
        lightSwitch.off();
    }
}
