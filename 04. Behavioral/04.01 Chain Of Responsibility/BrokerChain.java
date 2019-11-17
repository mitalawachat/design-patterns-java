import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<Args> {
    int index = 0;
    Map<Integer, Consumer<Args>> handlers = new HashMap<>();

    public int subscribe(Consumer<Args> handler) {
        int i = index;
        handlers.put(index++, handler);
        return i;
    }

    public void unsubscribe(int key) {
        handlers.remove(key);
    }

    public void fire(Args args) {
        for (Consumer<Args> handler : handlers.values()) {
            handler.accept(args);
        }
    }
}

class Query {
    enum Argument {
        ATTACK, DEFENCE
    }

    String creatureName;
    Argument argument;
    int result;

    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

class Game {
    public Event<Query> queries = new Event<>();
}

class Creature {
    Game game;
    String name;
    int baseAttack, baseDefence;

    public Creature(Game game, String name, int baseAttack, int baseDefence) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefence = baseDefence;
    }

    int getAttack() {
        Query query = new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(query);
        return query.result;
    }

    int getDefence() {
        Query query = new Query(name, Query.Argument.DEFENCE, baseDefence);
        game.queries.fire(query);
        return query.result;
    }

    @Override
    public String toString() {
        return "Creature [name=" + name + ", attack=" + getAttack() + ", defence=" + getDefence() + "]";
    }
}

class CreatureModifier {
    Game game;
    Creature creature;

    public CreatureModifier(Game game, Creature creature) {
        this.game = game;
        this.creature = creature;
    }
}

class DoubleAttackModifier extends CreatureModifier implements AutoCloseable {
    private final int token;

    public DoubleAttackModifier(Game game, Creature creature) {
        super(game, creature);
        token = game.queries.subscribe(q -> {
            if (q.creatureName.equals(creature.name) && q.argument == Query.Argument.ATTACK) {
                q.result *= 2;
            }
        });
    }

    @Override
    public void close() {
        game.queries.unsubscribe(token);
    }
}

class DoubleDefenceModifier extends CreatureModifier implements AutoCloseable {
    private final int token;

    public DoubleDefenceModifier(Game game, Creature creature) {
        super(game, creature);
        token = game.queries.subscribe(q -> {
            if (q.creatureName.equals(creature.name) && q.argument == Query.Argument.DEFENCE) {
                q.result *= 2;
            }
        });
    }

    @Override
    public void close() {
        game.queries.unsubscribe(token);
    }
}

public class BrokerChain {
    public static void main(String[] args) {
        Game game = new Game();
        Creature creature = new Creature(game, "Goblin", 4, 2);
        System.out.println(creature);

        DoubleAttackModifier attackModifier = new DoubleAttackModifier(game, creature);

        try (DoubleDefenceModifier defenceModifier = new DoubleDefenceModifier(game, creature)) {
            System.out.println(creature);
        }

        System.out.println(creature);
    }
}
