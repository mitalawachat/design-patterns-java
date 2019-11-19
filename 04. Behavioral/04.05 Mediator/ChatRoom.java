import java.util.ArrayList;
import java.util.List;

class Person {

    String name;
    ChatRoom chatRoom;
    List<String> chat = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public void receive(String sender, String message) {
        String displayMessage = sender + " : '" + message + "'";
        System.out.println("[" + name + "'s chat session] " + displayMessage);
        chat.add(displayMessage);
    }

    public void broadcast(String message) {
        chatRoom.broadcast(name, message);
    }

    public void message(String receiver, String message) {
        chatRoom.message(name, receiver, message);
    }
}

public class ChatRoom {
    private List<Person> people = new ArrayList<>();

    public void join(Person person) {
        String joinMessage = person.name + " join room";
        broadcast("Room", joinMessage);
        person.chatRoom = this;
        people.add(person);
    }

    public void broadcast(String source, String message) {
        for (Person person : people) {
            if (!person.name.equals(source)) {
                person.receive(source, message);
            }
        }
    }

    public void message(String source, String destination, String message) {
        people.stream().filter(p -> p.name.equals(destination)).findFirst()
                .ifPresent(person -> person.receive(source, message));
    }

    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        Person john = new Person("John");
        Person jane = new Person("Jane");

        chatRoom.join(john);
        chatRoom.join(jane);

        john.broadcast("Hello everyone!");
        jane.broadcast("Oh, Hey John!");

        Person simon = new Person("Simon");
        chatRoom.join(simon);
        simon.broadcast("hi everyone!");

        jane.message(simon.name, "Glad you could join :)");
    }
}
