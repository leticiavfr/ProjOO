interface Pedido {
    String getItem();
    double getPreco();
}

class Cafe implements Pedido {
    public String getItem(){
        return "Café";
    }

    public double getPreco(){
        return 5.0;
    }
}

class Capuccino implements Pedido {
    public String getItem(){
        return "Capuccino";
    }

    public double getPreco(){
        return 8.0;
    }
}

class Cha implements Pedido {
    public String getItem(){
        return "Cha";
    }

    public double getPreco(){
        return 6.0;
    }
}

abstract class ComplementoDecorator implements Pedido {
    protected Pedido pedido;
}

class Leite extends ComplementoDecorator {
    public Leite(Pedido pedido){
        this.pedido = pedido;
    }

    public String getItem(){
        return pedido.getItem() + ", leite";
    }

    public double getPreco(){
        return pedido.getPreco() + 1.0;
    }
}

class Chantilly extends ComplementoDecorator {
    public Chantilly(Pedido pedido){
        this.pedido = pedido;
    }

    public String getItem(){
        return pedido.getItem() + ", chantilly";
    }

    public double getPreco(){
        return pedido.getPreco() + 2.0;
    }
}

class Canela extends ComplementoDecorator {
    public Canela(Pedido pedido){
        this.pedido = pedido;
    }

    public String getItem(){
        return pedido.getItem() + ", canela";
    }

    public double getPreco(){
        return pedido.getPreco() + 0.5;
    }
}

class CaldaDeChocolate extends ComplementoDecorator {
    public CaldaDeChocolate(Pedido pedido){
        this.pedido = pedido;
    }

    public String getItem(){
        return pedido.getItem() + ", calda de chocolate";
    }

    public double getPreco(){
        return pedido.getPreco() + 2.0;
    }
}

public class Main {
    public static void main (String[] args){
        Pedido pedido = new Capuccino();

        pedido = new Canela(pedido);
        pedido = new CaldaDeChocolate(pedido);   

        System.out.println(pedido.getItem());
        System.out.println(pedido.getPreco()); 
    }
}