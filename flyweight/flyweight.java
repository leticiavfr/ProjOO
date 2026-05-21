import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Arvore{
    private String nome;
    private String textura;
    private String cor;

    public Arvore(String nome, String textura, String cor){
        this.nome = nome;
        this.textura = textura;
        this.cor = cor;
    }

    public void exibir(int altura, int x, int y){
        System.out.println("Árvore de nome " + nome + ", textura " + textura + ", cor " + cor + 
                            ", altura " + altura + " e posição (" + x + ", " + y + ")");
    }
}

class ArvoreFactory{
    private static Map<String, Arvore> tipoArvore = new HashMap<>();

    public static Arvore getTipoArvore(String nome, String textura, String cor){
        String chave = nome + textura + cor;
        if (!tipoArvore.containsKey(chave)) {
            tipoArvore.put(chave, new Arvore(nome, textura, cor));
        }

        return tipoArvore.get(chave);
    }

    public static int getQtdTipo() {
        return tipoArvore.size();
    }
}

class ArvoreFloresta{
    private int altura;
    private int x;
    private int y;

    private Arvore tipo;

    public ArvoreFloresta(int altura, int x, int y, Arvore tipo){
        this.altura = altura;
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public void exibir(){
        tipo.exibir(altura, x, y);
    }
}

class Floresta{
    private List<ArvoreFloresta> arvores = new ArrayList<>();

    public void registraArvore(int altura, int x, int y, String nome, String textura, String cor){
        Arvore tipo = ArvoreFactory.getTipoArvore(nome, textura, cor);
        ArvoreFloresta arvore = new ArvoreFloresta(altura, x, y, tipo);
    
        arvores.add(arvore);
    }

    public void exibirFloresta(){
        for (ArvoreFloresta a : arvores){
            a.exibir();
        }
    }

    public int getQtdArvore() {
        return arvores.size();
    }
}

public class Main{
    public static void main(String[] args){
        Floresta floresta = new Floresta();
        Random random = new Random();

        for (int i = 0; i < 30000; i++){
            int tipo = random.nextInt(5);

            if (tipo == 1){
                floresta.registraArvore(random.nextInt(100), random.nextInt(100), random.nextInt(100), "ipê", "áspera", "amarela");
            }
            else if (tipo == 2){
                floresta.registraArvore(random.nextInt(100), random.nextInt(100), random.nextInt(100), "baobá", "lisa", "marrom");
            }
            else if (tipo == 3){
                floresta.registraArvore(random.nextInt(100), random.nextInt(100), random.nextInt(100), "coqueiro", "áspera", "verde");
            }
            else if (tipo == 4){
                floresta.registraArvore(random.nextInt(100), random.nextInt(100), random.nextInt(100), "macieira", "lisa", "verde");
            }
            else{
                floresta.registraArvore(random.nextInt(100), random.nextInt(100), random.nextInt(100), "palmeira", "rugosa", "verde");
            }
        }

        floresta.exibirFloresta();

        System.out.println("quantidade de árvores na floresta: " + floresta.getQtdArvore());

        System.out.println("quantidade de tipos compartilhados: " + ArvoreFactory.getQtdTipo());
    }
}

/*  análise de economia de memória:
    supondo que cada campo do tipo string consome 20 bytes e cada 
    campo do tipo int consome 4 bytes, temos um total de 
    (20*3)+(4*3) = 72 bytes por árvore.
    
    sem flyweight: total de árvores * tamanho = 
                   30000 * 72 = 2160000 bytes = 
                   2.16 MB
    
    com flyweight: cada árvore guarda altura + x + y + referência
                    ao flyweight. Supondo que referência consome 8 
                    bytes, temos:
                        30000 * 20 = 600000 bytes =
                        0.6 MB
                    considerando apenas o estado extrínseco. 
                    Considerando o estado intrínseco com dados 
                    compartilhados temos:
                        (nome + textura + cor) * tipos =
                        (20 + 20 + 20) * 5 = 300 bytes =
                        0.0003 MB
                    total com flyweight = 0.6003 MB
    
    economia de memória = 2.16 - 0.6003 =
                          1.5597 MB economizados
*/