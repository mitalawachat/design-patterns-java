import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

interface INeuron extends Iterable<Neuron> {
    default void connectTo(INeuron other) {
        if(this == other) {
            return;
        }
        for(Neuron from : this) {
            for(Neuron to : other) {
                from.out.add(to);
                to.in.add(from);
            }
        }
    }
}

class Neuron implements INeuron {
    ArrayList<Neuron> in = new ArrayList<>();
    ArrayList<Neuron> out = new ArrayList<>();

    @Override
    public Iterator<Neuron> iterator() {
        return Collections.singleton(this).iterator();
    }

    @Override
    public Spliterator<Neuron> spliterator() {
        return Collections.singleton(this).spliterator();
    }

    @Override
    public void forEach(Consumer<? super Neuron> action) {
        action.accept(this);
    }
}

class NeuronLayer extends ArrayList<Neuron> implements INeuron {
    private static final long serialVersionUID = 1L;
}

public class CompositeExample2 {
    public static void main(String[] args) {
        Neuron neuron1 = new Neuron();
        Neuron neuron2 = new Neuron();

        NeuronLayer neuronLayer1 = new NeuronLayer();
        NeuronLayer neuronLayer2 = new NeuronLayer();

        neuron1.connectTo(neuron2);
        neuron1.connectTo(neuronLayer1);
        neuronLayer1.connectTo(neuron1);
        neuronLayer1.connectTo(neuronLayer2);
    }
}
