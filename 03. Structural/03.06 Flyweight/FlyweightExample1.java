import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class User {
    private String fullName;
}

class OptimizedUser {
    static List<String> dictionary = new ArrayList<>();
    private int[] names;

    public OptimizedUser(String fullName) {
        Function<String, Integer> getOrAdd = (String s) -> {
            int index = dictionary.indexOf(s);
            if (index != -1) {
                return index;
            }
            dictionary.add(s);
            return dictionary.size() - 1;
        };

        names = Arrays.stream(fullName.split(" ")).mapToInt(s -> getOrAdd.apply(s)).toArray();
    }

    public String getName() {
        String response[] = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            response[i] = dictionary.get(names[i]);
        }
        return String.join(" ", response);
    }
}

public class FlyweightExample1 {
    public static void main(String[] args) {
        OptimizedUser user1 = new OptimizedUser("John Smith");
        System.out.println(user1.getName());

        OptimizedUser user2 = new OptimizedUser("Jane Smith");
        System.out.println(user2.getName());
    }
}
