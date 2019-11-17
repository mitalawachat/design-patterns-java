class Creature {
    String name;
    int attack, defence;

    public Creature(String name, int attack, int defence) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
    }

    @Override
    public String toString() {
        return "Creature [name=" + name + ", defence=" + defence + ", attack=" + attack + "]";
    }
}

class CreatureModifier {

    protected Creature creature;
    protected CreatureModifier next;

    public CreatureModifier(Creature creature) {
        this.creature = creature;
    }

    public void add(CreatureModifier modifier) {
        if (next != null) {
            next.add(modifier);
        } else {
            next = modifier;
        }
    }

    public void handle() {
        if (next != null) {
            next.handle();
        }
    }
}

class DoubleAttackModifier extends CreatureModifier {

    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Doubling " + creature.name + "'s attack");
        creature.attack *= 2;
        super.handle();
    }
}

class DoubleDefenceModifier extends CreatureModifier {

    public DoubleDefenceModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Doubling " + creature.name + "'s defence");
        creature.defence *= 2;
        super.handle();
    }
}

class NoBonusMofifier extends CreatureModifier {

    public NoBonusMofifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("No bonus from now on");
    }
}

public class MethodChain {
    public static void main(String[] args) {
        Creature goblin = new Creature("Goblin", 4, 2);
        System.out.println(goblin);

        CreatureModifier goblinRootModifier = new CreatureModifier(goblin);

        goblinRootModifier.add(new DoubleAttackModifier(goblin));
        goblinRootModifier.add(new NoBonusMofifier(goblin));
        goblinRootModifier.add(new DoubleDefenceModifier(goblin));
        goblinRootModifier.handle();

        System.out.println(goblin);
    }
}
