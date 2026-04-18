import java.util.List;
import java.util.ArrayList;

class Subject{
  private List<Observer> observers = new ArrayList<Observer>();

  public void addObserver(Observer observer){
    observers.add(observer);
  }

  public void removeObserver(Observer observer){
    observers.remove(observer);
  }

  public void notifyObservers(double temp, double ph, double pa, double ura){
    for (Observer obs : observers){
      obs.update(temp, ph, pa, ura);
    }
  } 
}

interface Observer{
  public void update(double temp, double ph, double pa, double ura);
}

class PCD extends Subject{
  private double temp, ph, pa, ura;
   
  public void setDados (double temp, double ph, double pa, double ura){
    this.temp = temp;
    this.ph = ph;
    this.pa = pa;
    this.ura = ura;

    notifyObservers(temp, ph, pa, ura);
  }
}

abstract class Universidade implements Observer{
  protected String nome;

  public Universidade(String nome){
    this.nome = nome;
  }

  public void update(double temp, double ph, double pa, double ura){
    System.out.println(nome);
    System.out.println("Temperatura: " + temp);
    System.out.println("PH: " + ph);
    System.out.println("Pressão atmosférica: " + pa);
    System.out.println("Umidade relativa do ar: " + ura);
    System.out.println();
  }

}

class UniSP extends Universidade{
  public UniSP(){
    super("UniSP");
  }
}

class UniPOA extends Universidade{
  public UniPOA(){
    super("UniPOA");
  }
}

class UniRJ extends Universidade{
  public UniRJ(){
    super("UniRJ");
  }
}

class UniBSB extends Universidade{
  public UniBSB(){
    super("UniBSB");
  }
}

public class Main{
  public static void  main(String [] args){
    PCD pcd = new PCD();

    pcd.addObserver(new UniSP ());
    pcd.addObserver(new UniPOA ());
    pcd.addObserver(new UniRJ ());
    pcd.addObserver(new UniBSB ());

    pcd.setDados(35.0, 7.0, 1000.0, 80.0);
  }
}