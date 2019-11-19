import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

class Node<T> {
    T value;
    Node<T> left, right, parent;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
        left.parent = right.parent = this;
    }
}

class InorderIterator<T> implements Iterator<T> {
    Node<T> current, root;
    boolean yeildedStart;

    public InorderIterator(Node<T> root) {
        this.root = this.current = root;
        while (current.left != null) {
            current = current.left;
        }
    }

    private boolean hasRightMostParent(Node<T> node) {
        if (node.parent == null) {
            return false;
        }
        return node == node.parent.left || hasRightMostParent(node.parent);
    }

    @Override
    public boolean hasNext() {
        return current.left != null || current.right != null || hasRightMostParent(current);
    }

    @Override
    public T next() {
        if (!yeildedStart) {
            yeildedStart = true;
            return current.value;
        }
        if (current.right != null) {
            current = current.right;
            while (current.left != null) {
                current = current.left;
            }
            return current.value;
        } else {
            Node<T> p = current.parent;
            while (p != null && current == p.right) {
                current = p;
                p = p.parent;
            }
            current = p;
            return current.value;
        }
    }
}

class Tree<T> implements Iterable<T> {
    Node<T> root;

    Tree(Node<T> root) {
        this.root = root;
    }

    @Override
    public Iterator<T> iterator() {
        return new InorderIterator<T>(root);
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (T item : this) {
            action.accept(item);
        }
    }
}

public class TreeIterator {
    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1, new Node<Integer>(2), new Node<Integer>(3));

        InorderIterator<Integer> iterator = new InorderIterator<>(root);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ",");
        }
        System.out.println();

        Tree<Integer> tree = new Tree<Integer>(root);
        for (int value : tree) {
            System.out.print(value + ",");
        }
        System.out.println();
    }
}
