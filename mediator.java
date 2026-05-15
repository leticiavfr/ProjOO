import java.util.ArrayList;
import java.util.List;

interface Mediator {
    void send (String message, User user);
    void addUsers (User user);
}

abstract class User{
    protected Mediator med;
    protected String name;

    public User (Mediator med, String name){
        this.med = med;
        this.name = name;
    }

    public abstract void send (String message);
    public abstract void receive (String message);
}

class Chat implements Mediator{
    private List<User> users;

    public Chat(){
        this.users = new ArrayList<>();
    }

    @Override
    public void addUsers (User user){
        this.users.add(user);
    }

    @Override
    public void send (String message, User user){
        for (User u : this.users){
            if (u != user){
                u.receive(message);
            }
        }
    }
}

class UserImplementation extends User{
    public UserImplementation(Mediator med, String name) {
        super(med, name);
    }
    
    @Override
    public void send (String message){
        System.out.println("" + this.name + " enviando mensagem " + message);
        med.send(message, this);
    }
    
    @Override
    public void receive (String message){
        System.out.println("" + this.name + " recebeu mensagem " + message );
    }
}

public class Main{
    public static void main (String [] args){
        Chat chat = new Chat();

        User user1 = new UserImplementation(chat, "Joe");
        User user2 = new UserImplementation(chat, "Jonas");
        User user3 = new UserImplementation(chat, "Dylan");

        chat.addUsers(user1);
        chat.addUsers(user2);
        chat.addUsers(user3);

        user1.send("Boa noite!");
    }
} 