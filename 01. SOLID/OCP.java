import java.util.List;
import java.util.stream.Stream;

enum COLOR {
    RED, BLUE, GREEN;
}

enum SIZE {
    SMALL, MEDUIM, LARGE;
}

class Product {

    private String name;
    private COLOR color;
    private SIZE size;

    public Product(String name, COLOR color, SIZE size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    public COLOR getColor() {
        return color;
    }

    public SIZE getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Product [color=" + color + ", name=" + name + ", size=" + size + "]";
    }
}

class ProductFilter {
    public Stream<Product> filter(List<Product> list, Specification<Product> specification) {
        return list.stream().filter(p -> specification.isSatisfied(p));
    }
}

interface Specification<T> {
    public boolean isSatisfied(T t);
}

class ColorSpecification implements Specification<Product> {

    private COLOR color;

    public ColorSpecification(COLOR color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product product) {
        return product.getColor() == this.color;
    }
}

class SizeSpecification implements Specification<Product> {

    private SIZE size;

    public SizeSpecification(SIZE size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product product) {
        return product.getSize() == this.size;
    }
}

class AndSpecification<T> implements Specification<T> {

    private Specification<T> s1;
    private Specification<T> s2;

    public AndSpecification(Specification<T> s1, Specification<T> s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public boolean isSatisfied(T t) {
        return s1.isSatisfied(t) && s2.isSatisfied(t);
    }
}

public class OCP {

    public static void main(String args[]) {

        Product p1 = new Product("Product 1", COLOR.BLUE, SIZE.SMALL);
        Product p2 = new Product("Product 2", COLOR.GREEN, SIZE.SMALL);
        Product p3 = new Product("Product 3", COLOR.BLUE, SIZE.MEDUIM);

        List<Product> list = List.of(p1, p2, p3);

        ProductFilter productFilter = new ProductFilter();

        System.out.println("Blue products are:");
        productFilter.filter(list, new ColorSpecification(COLOR.BLUE)).forEach(System.out::println);

        System.out.println("Blue and medium products are:");
        productFilter.filter(list,
                new AndSpecification<Product>(new ColorSpecification(COLOR.BLUE), new SizeSpecification(SIZE.MEDUIM)))
                .forEach(System.out::println);
    }
}
