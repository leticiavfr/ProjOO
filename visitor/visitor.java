interface Relatorio{
    void accept(Visitor v);
}

class RelatorioA implements Relatorio{
    String dadosA = "Dados do Relatório A.";

    public void accept(Visitor v){
        v.visit(this);
    }
}

class RelatorioB implements Relatorio{
    String dadosB = "Dados do Relatório B.";

    public void accept(Visitor v){
        v.visit(this);
    }
}

interface Visitor{
    void visit(RelatorioA a);
    void visit(RelatorioB b);
}

class PDFVisitor implements Visitor{
    public void visit(RelatorioA a){
        System.out.println("Gerando PDF do Relatório A: " + a.dadosA);
    }

    public void visit(RelatorioB b){
        System.out.println("Gerando PDF do Relatório B: " + b.dadosB);
    }
}

class XMLVisitor implements Visitor{
    public void visit(RelatorioA a){
        System.out.println("Gerando XML do Relatório A: " + a.dadosA);
    }

    public void visit(RelatorioB b){
        System.out.println("Gerando XML do Relatório B: " + b.dadosB);
    }
}

class HTMLVisitor implements Visitor{
    public void visit(RelatorioA a){
        System.out.println("Gerando HTML do Relatório A: " + a.dadosA);
    }

    public void visit(RelatorioB b){
        System.out.println("Gerando HTML do Relatório B: " + b.dadosB);
    }
}

class PlanilhaVisitor implements Visitor{
    public void visit(RelatorioA a){
        System.out.println("Gerando Planilha do Relatório A: " + a.dadosA);
    }

    public void visit(RelatorioB b){
        System.out.println("Gerando Planilha do Relatório B: " + b.dadosB);
    }
}

public class Main{
    public static void main(String[] args){
        Relatorio ra = new RelatorioA();
        Relatorio rb = new RelatorioB();

        Visitor pdf = new PDFVisitor();
        Visitor xml = new XMLVisitor();
        Visitor html = new HTMLVisitor();
        Visitor planilha = new PlanilhaVisitor();

        ra.accept(pdf);
        ra.accept(xml);

        rb.accept(html);
        rb.accept(planilha);
    }
}