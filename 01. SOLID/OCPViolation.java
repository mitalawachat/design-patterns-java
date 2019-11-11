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
    public Stream<Product> filterByColor(List<Product> list, COLOR color) {
        return list.stream().filter(p -> p.getColor() == color);
    }

    public Stream<Product> filterBySize(List<Product> list, SIZE size) {
        return list.stream().filter(p -> p.getSize() == size);
    }

    public Stream<Product> filterByColorAndSize(List<Product> list, COLOR color, SIZE size) {
        return list.stream().filter(p -> p.getColor() == color && p.getSize() == size);
    }
}

public class OCPViolation {

    public static void main(String args[]) {

        Product p1 = new Product("Product 1", COLOR.BLUE, SIZE.SMALL);
        Product p2 = new Product("Product 2", COLOR.GREEN, SIZE.SMALL);
        Product p3 = new Product("Product 3", COLOR.BLUE, SIZE.MEDUIM);

        List<Product> list = List.of(p1, p2, p3);

        ProductFilter productFilter = new ProductFilter();

        System.out.println("Blue products are:");
        productFilter.filterByColor(list, COLOR.BLUE).forEach(System.out::println);

        System.out.println("Blue and medium products are:");
        productFilter.filterByColorAndSize(list, COLOR.BLUE, SIZE.MEDUIM).forEach(System.out::println);
    }
}
