interface Notification {
    void send(String message);
}

class Email implements Notification {
    public void send(String message) {
        System.out.println("Mensagem: " + message);
    }
}

class Sms implements Notification {
    public void send(String message) {
        System.out.println("Mensagem: " + message);
    }
}

class PushNotification implements Notification {
    public void send(String message) {
        System.out.println("Mensagem: " + message);
    }
}

class NotificationFactory {
    public static Notification create(String type) {
        if (type.equals("email"))
            return new Email();
        else if (type.equals("sms")) 
            return new Sms();
        else if (type.equals("push")) 
            return new PushNotification();
        else 
            return null;
    }
}

class Config {
    public String nome;
    public int qtdTentativas;

    private Config() {
        nome = "sistema";
        qtdTentativas = 3;
    }

    private static Config instance;

    public static Config getInstance() {
        if (instance == null)
            instance = new Config();
    
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        Notification n1 = NotificationFactory.create("email");
        n1.send("email");

        Notification n2 = NotificationFactory.create("sms");
        n2.send("sms");

        Config c1 = Config.getInstance();
        Config c2 = Config.getInstance();

        System.out.println(c1.nome);
        System.out.println("quantidade máxima de tentativas: " + c1.qtdTentativas);

        if (c1 == c2) {
            System.out.println("mesma instância");
        } else {
            System.out.println("instâncias diferentes");
        }
    }
}