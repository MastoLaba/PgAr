package ieee.secondoes.problemc;

public class ProblemaC {
    static String sErrore = "Il programma verrà terminato ...";

    public static void main(String[] args) {
        String fileName = "problem_c.xml";
        Numeri n = new Numeri(fileName);
        if(n.getN() == null){ System.out.println(sErrore); return; }
        long start, end;
        
        start = System.currentTimeMillis();
        n.ordina();
        Numero max = n.getNumero(0);
        Numero min = n.getNumero(n.getSize() - 1);
        Numero mediana = n.mediana();
        end = System.currentTimeMillis();
        
        long delta = end - start;
        System.out.println("Minimo:\t\t\t" + min);
        System.out.println("Massimo:\t\t" + max);
        System.out.println("Mediana:\t\t" + mediana);
        System.out.println("Tempo esecuzione:\t" + delta + " millisecondi");
        
    }
    
}
