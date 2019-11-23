import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum State {
    OFF_HOOK, ON_HOOK, CONNECTING, CONNECTED, ON_HOLD
}

enum Trigger {
    CALL_DIALED, HUNG_UP, CALL_CONNECTED, PLACED_ON_HOLD, TAKEN_OFF_HOLD, LEFT_MESSAGE, STOP_USING_PHONE
}

public class StateExample2 {
    private static Map<State, List<Pair<Trigger, State>>> rules = new HashMap<>();
    private static State currentState = State.OFF_HOOK;
    private static State exitState = State.ON_HOOK;

    static {
        rules.put(State.OFF_HOOK, List.of(new Pair<>(Trigger.CALL_DIALED, State.CONNECTING),
                new Pair<>(Trigger.STOP_USING_PHONE, State.ON_HOOK)));

        rules.put(State.CONNECTING, List.of(new Pair<>(Trigger.HUNG_UP, State.OFF_HOOK),
                new Pair<>(Trigger.CALL_CONNECTED, State.CONNECTED)));

        rules.put(State.CONNECTED, List.of(new Pair<>(Trigger.LEFT_MESSAGE, State.OFF_HOOK),
                new Pair<>(Trigger.HUNG_UP, State.OFF_HOOK), new Pair<>(Trigger.PLACED_ON_HOLD, State.ON_HOLD)));

        rules.put(State.ON_HOLD, List.of(new Pair<>(Trigger.TAKEN_OFF_HOLD, State.CONNECTED),
                new Pair<>(Trigger.HUNG_UP, State.OFF_HOOK)));
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Phone state = " + currentState);

            System.out.println("Select trigger:");
            for (int i = 0; i < rules.get(currentState).size(); i++) {
                Trigger trigger = rules.get(currentState).get(i).getKey();
                System.out.println(i + ". " + trigger);
            }

            boolean parseOk;
            int choice = 0;
            do {
                try {
                    System.out.println("Please enter your choice:");
                    choice = Integer.parseInt(reader.readLine());
                    parseOk = choice >= 0 && choice < rules.get(currentState).size();
                } catch (Exception e) {
                    parseOk = false;
                }
            } while (!parseOk);

            currentState = rules.get(currentState).get(choice).getValue();
            if (currentState == exitState) {
                break;
            }
        }

        System.out.println("Done!");
    }
}

class Pair<K, V> {
    public final K key;
    public final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pair other = (Pair) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pair [key=" + key + ", value=" + value + "]";
    }
}
