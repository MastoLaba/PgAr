package ieee.primoes.grafi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.stream.*;

public class Dijkstra {
    private String[] nodi;
    private boolean[] ottimizzati;
    private String[] precedente;
    private int[] distanza;
    private int[] tipoN;
    private ArrayList<Integer> indici;
    private ArrayList<Collegamento> collegamenti;
    private ArrayList<Integer> costi;
    private int L;
    
    private final int INFINITO = Integer.MAX_VALUE;
    private final String CAPO = "\n";
    private final String SPA = " ";
    
    /**
     * Classe a doc per l'implementazione dell'algoritmo di Dijkstra che
     * semplifica la classe Legame
     */
    class Collegamento {
        
        private String nodoA;
        private String nodoB;

        public Collegamento() { }

        public Collegamento(String nA, String nB) { nodoA = nA; nodoB = nB; }

        public String getNodoA() { return nodoA; }

        public String getNodoB() { return nodoB; }
        
        @Override
        public String toString() { return nodoA + ":" + nodoB; }
    }
    
    /**
     * Ritorna un intero che indica il numero di nodi dai quali non si è ancora
     * passati
     * @return ritorno numero di nodi non ancora ottimizzati
     */
    public int nodiNonOttimizzati() {
        int ritorno = 0;
        for(int i = 0; i < L; i++) if(!ottimizzati[i]) ritorno++;
        return ritorno;
    }
    
    /**
     * Dato un collegamento ritorna l'indice del nodo figlio
     * @param collegamento
     * @return i indice del figlio
     */
    private int trovaIndice(Collegamento collegamento) {
        String c = collegamento.getNodoB();
        for(int i = 0; i < nodi.length; i++) if(c == nodi[i]) return i;
        return -1;
    }
    
    /**
     * Ritorna il valore indice dell'elemento figlio non ancora ottimizzato e 
     * con peso di collegamento minore
     * @param u
     * @return indice indica l'elemento da ottimizzare dopo u
     */
    private int scegliSuccessivo(int u) {
        int minD = INFINITO, indice = -1;
        for(int i = 0; i < indici.size(); i++)
            if(u == indici.get(i)) {
                int iTemp = trovaIndice(collegamenti.get(i));
                if(distanza[iTemp] < minD && !ottimizzati[iTemp]) {
                    minD = distanza[iTemp];
                    indice = iTemp;
                }
            }
        if(indice == -1)
            /*System.out.println("Non esiste un successivo")*/;
        return indice;
    }
    
    public String getPercorso(int a) {
        String ritorno = ")" + distanza[a] + "( ";
        ritorno += nodi[a] + " ";
        do {
            a = trovaIndice(new Collegamento(null, precedente[a]));
            ritorno += nodi[a] + " ";
        } while(distanza[a] != 0);
        String s = "";
        for(int i = ritorno.length() - 1; i >= 0; i--) {
            s += ritorno.charAt(i);
        }
        return s.trim();
    }
    
    /**
     * Dopo aver convertito la struttura del Grafo in quella più semplificata e
     * esplicativa della classe Dijkstra è implementato l'algoritmo di Dijkstra
     * @param g grafo di partenza
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Dijkstra(Grafo g) {
        
        ArrayList<Nodo> nGrafo = g.getNodi();
        L = nGrafo.size();
        nodi = new String[L];
        ottimizzati = new boolean[L];
        precedente = new String[L];
        distanza = new int[L];
        tipoN = new int[L];
        indici = new ArrayList<>();
        collegamenti = new ArrayList<>();
        costi = new ArrayList<>();
        
        int sorgente = -1;
        int arrivo = -1;
        for(int i = 0; i < L; i++) {
            nodi[i] = nGrafo.get(i).getEtichetta();
            tipoN[i] = 1;
            if(nGrafo.get(i).isPrimo()){ sorgente = i; tipoN[i] = 0; }
            if(nGrafo.get(i).isUltimo()){ arrivo = i; tipoN[i] = 2; }
            ottimizzati[i] = false;
            precedente[i] = null;
            distanza[i] = INFINITO;
            ArrayList<Legame> l = nGrafo.get(i).getLegami();
            for (Legame l1 : l) {
                indici.add(i);
                collegamenti.add(new Collegamento(nodi[i], l1.getNodo().getEtichetta()));
                costi.add(l1.getCosto());
            }
        }
        if(sorgente == -1) return;
        
        distanza[sorgente] = 0;
        int u = sorgente;
        while(nodiNonOttimizzati() > 0) {
            ottimizzati[u] = true;
            if(distanza[u] == INFINITO) break;
            for(int i = 0; i < indici.size(); i++) {
                if(indici.get(i) == u) {
                    int alt = distanza[u] + costi.get(i);
                    int v = trovaIndice(collegamenti.get(i));
                    if(alt < distanza[v]) {
                        distanza[v] = alt;
                        precedente[v] = nodi[u];
                    }
                }
            }
            u = scegliSuccessivo(u);
        }
        System.out.println(getPercorso(arrivo));
        
    }
    
    public boolean toXML(String nomeFile) throws FileNotFoundException, IOException, XMLStreamException {
        File file = new File(nomeFile);
        if(!file.exists())
            if(file.createNewFile()) 
                System.out.println("Il file è stato creato");
        else System.out.println("Il file è già stato creato");
        
        ArrayList<String> nodiScritti = new ArrayList<>();
        XMLOutputFactory fattoria = XMLOutputFactory.newInstance();
        XMLStreamWriter scrittore = fattoria.createXMLStreamWriter(new FileOutputStream(file));
        
        scrittore.writeStartDocument();
        scrittore.writeCharacters("\n");
        scrittore.writeStartElement("order");
        scrittore.writeCharacters("\n");
        scrittore.writeStartElement("name");
        scrittore.writeCharacters("Dijkstra");
        scrittore.writeEndElement();
        scrittore.writeCharacters("\n");
        scrittore.writeStartElement("tree");
        scrittore.writeCharacters("\n");
        for(int i = 0; i < nodi.length; i++) {
            nodiScritti.add(nodi[i]);
            scrittore.writeStartElement("node");
            if(tipoN[i] == 0) scrittore.writeAttribute("start", "true");
            else if(tipoN[i] == 2) scrittore.writeAttribute("end", "true");
            scrittore.writeCharacters("\n");
            scrittore.writeStartElement("label");
            scrittore.writeCharacters(nodi[i]);
            scrittore.writeEndElement();
            scrittore.writeCharacters("\n");
            scrittore.writeStartElement("parent");
            scrittore.writeAttribute("cost", "" + distanza[i]);
            scrittore.writeCharacters(precedente[i]);
            scrittore.writeEndElement();
            scrittore.writeCharacters("\n");
            scrittore.writeStartElement("edges");
            scrittore.writeCharacters("\n");
            for(int j = 0; j < indici.size(); j++) {
                if(indici.get(j) == i && nodiScritti.contains(collegamenti.get(j).getNodoB())) {
                    scrittore.writeStartElement("edge");
                    scrittore.writeAttribute("weight", "" + costi.get(j));
                    scrittore.writeCharacters(collegamenti.get(j).nodoB);
                    scrittore.writeEndElement();
                    scrittore.writeCharacters("\n");
                }
            }
            scrittore.writeEndElement();
            scrittore.writeCharacters("\n");
            scrittore.writeEndElement();
            scrittore.writeCharacters("\n");
        }
        scrittore.writeEndElement();
        scrittore.writeCharacters("\n");
        scrittore.flush();
        scrittore.writeEndDocument();
        return true;
    }
    
    /**
     * Ritorna nodo:precedente:costo (per arrivare fino a quel nodo)
     *  collegamento.toString():costo (del collegamento)
     * @return ritorno
     */
    @Override
    public String toString() {
        String ritorno = SPA;
        for(int i = 0; i < nodi.length; i++) {
            ritorno += CAPO + nodi[i] + ":" + precedente[i] + ":" + distanza[i] + SPA;
//            for(int j = 0; j < indici.size(); j++)
//                if(indici.get(j) == i) ritorno += collegamenti.get(j).toString() + ":" + costi.get(j) + SPA;
        }
        return ritorno.trim();
    }
}