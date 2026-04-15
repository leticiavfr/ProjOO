class TV {
    public void ligar(){
        System.out.println ("TV ligada");
    }
    public void desligar(){
        System.out.println ("TV desligada");
    }
}

class Projetor {
    public void ligar(){
        System.out.println ("Projetor ligado");
    }
    public void desligar(){
        System.out.println ("Projetor desligado");
    }
}

class Receiver {
    public void ligar(){
        System.out.println ("Receiver ligado");
    }
    public void desligar(){
        System.out.println ("Receiver desligado");
    }
}

class PlayerDeMidia {
    public void ligar(){
        System.out.println ("Player de mídia ligado");
    }
    public void desligar(){
        System.out.println ("Player de mídia desligado");
    }
}

class SistemaDeSom {
    public void ligar(){
        System.out.println ("Sistema de som ligado");
    }
    public void desligar(){
        System.out.println ("Sistema de som desligado");
    }
}

class LuzAmbiente {
    public void ligar(){
        System.out.println ("Luz ligada");
    }
    public void desligar(){
        System.out.println ("Luz desligada");
    }
}

class HomeTheaterFacade {
    private TV tv;
    private Projetor projetor;
    private Receiver receiver;
    private PlayerDeMidia player;
    private SistemaDeSom som;
    private LuzAmbiente luz;

    public HomeTheaterFacade (TV tv, Projetor projetor, Receiver receiver, PlayerDeMidia player, SistemaDeSom som, LuzAmbiente luz){
        this.tv = tv;
        this.projetor = projetor;
        this.receiver = receiver;
        this.player = player;
        this.som = som;
        this.luz = luz;
    }

    public void assistirFilme (){
        receiver.ligar();
        tv.ligar();
        projetor.ligar();
        player.ligar();
        som.ligar();
        luz.desligar();
    }

    public void ouvirMusica (){
        receiver.ligar();
        tv.ligar();
        projetor.desligar();
        player.ligar();
        som.ligar();
        luz.ligar();
    }
}

public class Main {
    public static void main (String[] args){
        TV tv = new TV();
        Projetor projetor = new Projetor();
        Receiver receiver = new Receiver();
        PlayerDeMidia player = new PlayerDeMidia();
        SistemaDeSom som = new SistemaDeSom();
        LuzAmbiente luz = new LuzAmbiente();

        HomeTheaterFacade htFacade = new HomeTheaterFacade (tv, projetor, receiver, player, som, luz);

        htFacade.assistirFilme();
        System.out.println();
        htFacade.ouvirMusica();
    }
}
