interface Notification {
    void send(String message);
}

class Email {
    public void enviarEmail(String message) {
        System.out.println("Mensagem: " + message);
    }
}

class Sms {
    public void enviarSms(String message) {
        System.out.println("Mensagem: " + message);
    }
}

class PushNotification {
    public void enviarPushNotification(String message) {
        System.out.println("Mensagem: " + message);
    }
}

class AdaptadorEmail implements Notification {
    private Email email;

    AdaptadorEmail (Email email){
        this.email = email;
    }

    public void send(String message){
        email.enviarEmail(message);
    }
}

class AdaptadorSms implements Notification {
    private Sms sms;

    AdaptadorSms (Sms sms){
        this.sms = sms;
    }

    public void send(String message){
        sms.enviarSms(message);
    }
}

class AdaptadorPushNotification implements Notification {
    private PushNotification pushnotification;

    AdaptadorPushNotification (PushNotification pushnotification){
        this.pushnotification = pushnotification;
    }

    public void send(String message){
        pushnotification.enviarPushNotification(message);
    }
}

class NotificationProxy implements Notification {
    private Notification base;
    private String type;
    private int qtdTentativas;

    NotificationProxy (Notification base, String type){
        this.base = base;
        this.type = type;
        this.qtdTentativas = 0;
    }

    public void send (String message){
        System.out.println("tentando enviar mensagem");

        if (type.equals("push")) {
            System.out.println("permissão negada!");
            return;
        }
        else if (qtdTentativas >= Config.getInstance().qtdTentativas){
            System.out.println("quantidade de tentativas excedida!");
            return;
        }

        qtdTentativas++;
        
        base.send(message);

        System.out.println("mensagem enviada!");
    }    
}

class NotificationFactory {
    public static Notification create(String type) {
        if (type.equals("email"))
            return new NotificationProxy (new AdaptadorEmail (new Email()), "email");
        else if (type.equals("sms")) 
            return new NotificationProxy (new AdaptadorSms (new Sms()), "sms");
        else if (type.equals("push")) 
            return new NotificationProxy (new AdaptadorPushNotification (new PushNotification()), "push");
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
        n2.send("sms");
        n2.send("sms");
        n2.send("sms");
        
        Notification n3 = NotificationFactory.create("push");
        n3.send("push");

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