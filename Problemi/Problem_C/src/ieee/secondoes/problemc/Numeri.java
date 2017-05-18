package ieee.secondoes.problemc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Numeri {
    private ArrayList<Numero> n;
    
    public Numeri(String fileName) {
        try {
            if(!getDaXML(fileName)) System.out.println("Non è stato possibile "
                    + "generare l'oggetto \"Numeri\"");
        } catch (XMLStreamException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
            n = null;
        }
    }

    public ArrayList<Numero> getN() { return n; }
    
    public Numero getNumero(int index) { return n.get(index); }
    
    public int getSize() { return n.size(); }
    
    public boolean getDaXML(String fileName) throws XMLStreamException {
        File file = new File(fileName);
        if(!file.exists()) {
            System.out.println("ERRORE il file non esiste");
            return false;
        }
        
        XMLInputFactory fattoria = XMLInputFactory.newInstance();
        XMLStreamReader lettore = null;
        n = new ArrayList<>();
        try {
            lettore = fattoria.createXMLStreamReader(new FileInputStream(file));
        } catch (FileNotFoundException | XMLStreamException ex) {
            System.out.println("ERRORE creazione lettore"); return false;
        }
        
        while(lettore.hasNext())
            if(lettore.next() == XMLStreamConstants.START_ELEMENT)
                if("candiate".equals(lettore.getLocalName())) {
                    Numero t = new Numero();
                    double b = Double.parseDouble(lettore.getAttributeValue(0));
                    double e = Double.parseDouble(lettore.getAttributeValue(1));
                    t.setBase(b); t.setEsponente(e);
                    n.add(t);
                }
        
        return true;
    }
    
    public void ordina() {
        boolean scambio = true; int l = n.size() - 1; int u = n.size();
        while(scambio) {
            scambio = false;
            for(int i = 0; i < l; i++)
                if(n.get(i + 1).maggiore(n.get(i))) {
                    scambio = true;
                    Numero t = n.get(i).clone();
                    n.get(i).ugualia(n.get(i + 1));
                    n.get(i + 1).ugualia(t);
                    u = i;
                }
            l = u;
        }
    }
    
    public Numero mediana() {        
        if(!(n.size() % 2 == 0)) return n.get(n.size() / 2);
        int l = (n.size() / 2);
        Numero r = moltiplica(somma(n.get(l-1), n.get(l)), new Numero(2, -1));
        return r;
    }
    
    public Numero somma(Numero a, Numero b) {
        if(!a.maggiore(b)) return somma(b, a);
        Numero t = dividi(b, a);
        double v = t.getValore() + 1;
        t = a.clone(); t.moltiplica(v);
        return t;
    }
    
    public Numero dividi(Numero a, Numero b) {
        Numero c = new Numero(b.getBase(), -b.getEsponente());
        return moltiplica(a, c);
    }
    
    public Numero moltiplica(Numero a, Numero b) {
        Numero t = new Numero();
        if(a.getEsponente() == 1) {
            t = b.clone();
            t.moltiplica(a.getBase());
        } else if(b.getEsponente() == 1) {
            t = a.clone();
            t.moltiplica(b.getBase());
        } else {
            if(a.getEsponente() > b.getEsponente()) return moltiplica(b, a);
            double baset = Math.pow(a.getBase(), a.getEsponente() / b.getEsponente()) * b.getBase();
            t.setBase(baset); t.setEsponente(b.getEsponente());
        }
        return t;
    }
    
    @Override
    public String toString() {
        String r = "";
        for(Numero t: n)
            r += t.toString() + "\n";
        return r;
    }
}