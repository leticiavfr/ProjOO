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

class DadosAmbientais{
  double temp, ph, pa, ura;

  public DadosAmbientais(double temp, double ph, double pa, double ura){
    this.temp = temp;
    this.ph = ph;
    this.pa = pa;
    this.ura = ura;
  }
}

abstract class Universidade implements Observer{
  protected String nome;

  public Universidade(String nome){
    this.nome = nome;
  }

  abstract DadosAmbientais processaDados(double temp, double ph, double pa, double ura);
  abstract void exibeDados(DadosAmbientais dados);

  public void update(double temp, double ph, double pa, double ura){
    System.out.println(nome);

    DadosAmbientais dados = processaDados(temp, ph, pa, ura);
    
    exibeDados(dados);

    System.out.println();
  }
}

//exibe alerta de alta temperatura caso temperatura > 40
class UniSP extends Universidade{
  public UniSP(){
    super("UniSP");
  }

  DadosAmbientais processaDados(double temp, double ph, double pa, double ura){
    return new DadosAmbientais(temp, ph, pa, ura);
  }

  void exibeDados(DadosAmbientais dados){
    System.out.println("Temperatura: " + dados.temp);
    
    if (dados.temp > 40)
      System.out.println("-> Alerta de alta temperatura!");

    System.out.println("PH: " + dados.ph);
    System.out.println("Pressão atmosférica: " + dados.pa);
    System.out.println("Umidade relativa do ar: " + dados.ura);
  }
}

//exibe alerta de ar seco ou alta umidade para certos valores de ura
class UniPOA extends Universidade{
  public UniPOA(){
    super("UniPOA");
  }

  DadosAmbientais processaDados(double temp, double ph, double pa, double ura){
    return new DadosAmbientais(temp, ph, pa, ura);
  }

  void exibeDados(DadosAmbientais dados){
    System.out.println("Temperatura: " + dados.temp);
    System.out.println("PH: " + dados.ph);
    System.out.println("Pressão atmosférica: " + dados.pa);
    System.out.println("Umidade relativa do ar: " + dados.ura);

    if (dados.ura < 30)
      System.out.println("-> Alerta de ar seco!");
    else if (dados.ura > 70)
      System.out.println("-> Alta umidade!");
  }
}

//exibe alerta de risco ambiental para ph<5
class UniRJ extends Universidade{
  public UniRJ(){
    super("UniRJ");
  }

  DadosAmbientais processaDados(double temp, double ph, double pa, double ura){
    return new DadosAmbientais(temp, ph, pa, ura);
  }

  void exibeDados(DadosAmbientais dados){
    System.out.println("Temperatura: " + dados.temp);
    System.out.println("PH: " + dados.ph);

    if (dados.ph < 5)
      System.out.println("-> Alerta de risco ambiental!");
    
    System.out.println("Pressão atmosférica: " + dados.pa);
    System.out.println("Umidade relativa do ar: " + dados.ura);
  }
}

//não exibe valores valores inválidos de pressão atmosférica
class UniBSB extends Universidade{
  public UniBSB(){
    super("UniBSB");
  }

  DadosAmbientais processaDados(double temp, double ph, double pa, double ura){
    return new DadosAmbientais(temp, ph, pa, ura);
  }

  void exibeDados(DadosAmbientais dados){
    System.out.println("Temperatura: " + dados.temp);
    System.out.println("PH: " + dados.ph);
    
    if (dados.pa > 500.0 && dados.pa < 1050)
      System.out.println("Pressão atmosférica: " + dados.pa);

    System.out.println("Umidade relativa do ar: " + dados.ura);
  }
}

public class Main{
  public static void  main(String [] args){
    PCD pcd = new PCD();

    pcd.addObserver(new UniSP ());
    pcd.addObserver(new UniPOA ());
    pcd.addObserver(new UniRJ ());
    pcd.addObserver(new UniBSB ());

    pcd.setDados(42.0, 4.0, 3000.0, 80.0);
  }
}