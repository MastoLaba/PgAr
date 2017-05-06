package ieee.primoes.grafi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;

import javax.xml.stream.*;

public class Grafo {
	private ArrayList<Nodo> nodi;
	
	public Grafo() { nodi = new ArrayList<>(); }
	
	/**
	 * @return l'ArrayList di Nodi di questo Grafo
	 */
	public ArrayList<Nodo> getNodi() { return nodi; }

	public int getNumNodi(){ return nodi.size(); }
	
	/**
	 * Crea un nuovo nodo e lo aggiunge al grafo
	 * @param etichetta l'etichetta del Nodo da creare
	 */
	public void aggiungiNodo(String etichetta){
		for(Nodo n: nodi)
			if(n.getEtichetta().equals(etichetta)){
				System.out.println("Il nodo "+etichetta+" esiste già nel grafo!");
				return;
			}
		nodi.add(new Nodo(etichetta));
	}
	
	/**
	 * Aggiunge un nodo al Grafo
	 * @param nodo il Nodo (già creato)
	 */
	public void aggiungiNodo(Nodo nodo){
		for(Nodo temp: nodi)
			if(temp.getEtichetta().equals(nodo.getEtichetta())){				
				System.out.println("Il nodo "+nodo.getEtichetta()+" esiste già nel grafo!");
				return;
			}
		nodi.add(nodo);
	}
		
	/**
	 * Prende un Nodo dal Grafo
	 * @param etichetta l'etichetta del Nodo da trovare
	 * @return il Nodo, se trovato, null altrimenti
	 */
	public Nodo getNodo(String etichetta){
		for(Nodo temp: nodi)
			if(temp.getEtichetta().equals(etichetta))
				return temp;
		System.out.println("Nodo "+etichetta+" non trovato");
		return null;
	}
	
	/**
	 * Dice la posizione di un Nodo nell'ArrayList dei nodi
	 * @param etichetta l'etichetta del Nodo da trovare
	 * @return la posizione del Nodo, se trovato, -1 altrimenti
	 */
	public int getIndiceNodo(String etichetta){
		int i=0;
		for(Nodo n: nodi)
			if(n.getEtichetta().equals(etichetta))
				return i;
			else
				i++;
		System.out.println("Nodo "+etichetta+" non trovato");
		return -1;
	}
	
	/**
	 * @return il primo Nodo che è impostato come "primo", se trovato, null altrimenti
	 */
	public Nodo getPrimoNodo(){
		for(Nodo n: nodi)
			if(n.isPrimo())
				return n;
		System.out.println("Nessun nodo è il primo!");
		return null;
	}
	
	/**
	 * lega il primo nodo del Grafo al secondo e viceversa, impostando un costo specifico
	 * se non trova almeno uno dei due nodi, non esegue l'operazione
	 * @param n1 l'etichetta del primo Nodo da trovare
	 * @param n2 l'etichetta del secondo Nodo da trovare
	 * @param costo il costo del legame
	 */
	public void lega(String n1, String n2, int costo){
		if(getNodo(n1) != null && getNodo(n2) != null)
			lega(getNodo(n1), getNodo(n2), costo);
		else
			System.out.println("I nodi "+n1+" e/o "+n2+" non sono nel grafo");
	}
	
	/**
	 * lega il primo nodo al secondo e viceversa, impostando un costo specifico
	 * @param n1 il primo Nodo
	 * @param n2 il secondo Nodo
	 * @param costo il costo del legame
	 */
	private void lega(Nodo n1, Nodo n2, int costo){
		n1.lega(n2, costo);
		n2.lega(n1, costo);
	}
	
	/**
	 * Rimuove i legami di due Nodi del grafo
	 * @param n1 l'etichetta del primo Nodo
	 * @param n2 l'etichetta del secondo Nodo
	 */
	public void slega(String n1, String n2){
		if(getNodo(n1) != null && getNodo(n2) != null)
			slega(getNodo(n1), getNodo(n2));
		else
			System.out.println("I nodi "+n1+" e/o "+n2+" non sono nel grafo");
	}
	
	/**
	 * Rimuove i legami due Nodi del grafo
	 * @param n1 il primo Nodo
	 * @param n2 il secondo Nodo
	 */
	private void slega(Nodo n1, Nodo n2){
		n1.slega(n2.getEtichetta());
		n2.slega(n1.getEtichetta());
	}
	
	/**
	 * Crea un oggetto Grafo leggendo i dati da un file XML
	 * @param fileName il nome (o path) del file XML
	 * @return il Grafo creato
	 */
	public static Grafo getDaXML(String fileName) throws FileNotFoundException, XMLStreamException {	
        File file = new File(fileName);
        if(!file.exists()) {
            System.out.println("Il file " + fileName + " non esiste");
            return null;
        }
        
        XMLInputFactory fattoria = XMLInputFactory.newInstance();
        XMLStreamReader lettore = fattoria.createXMLStreamReader(new FileInputStream(file));
        Nodo nodoTemp = null;
        Grafo g = null;
        String costo = "", dati = "";
        
        while (lettore.hasNext()) {
            int bandierina = 0;
            try{
                bandierina = lettore.next();
            }catch(XMLStreamException ex){ System.out.println("Il file è vuoto"); }
            
            switch(bandierina){
                case XMLStreamConstants.START_DOCUMENT:
                    System.out.println("Inizio Documento...");
                    break;
                    
                case XMLStreamConstants.START_ELEMENT:
                    if("tree".equals(lettore.getLocalName()))
                        g = new Grafo();
                    else if("node".equals(lettore.getLocalName())) {
                        nodoTemp = new Nodo();
                        String nome_attributo = lettore.getAttributeLocalName(0);
                        if("start".equals(nome_attributo))
                            nodoTemp.setPrimo();
                        else if("end".equals(nome_attributo))
                            nodoTemp.setUltimo();
                    }
                    else if("edges".equals(lettore.getLocalName())) ;
                    else if("edge".equals(lettore.getLocalName())) {
                        if("weight".equals(lettore.getAttributeLocalName(0)))
                            costo = lettore.getAttributeValue(0).trim();
                    }
                    break;
                    
                case XMLStreamConstants.CHARACTERS:
                    if(lettore.getText().trim().length() > 0)
                        dati = lettore.getText().trim();
                    break;
                    
                case XMLStreamConstants.END_ELEMENT:
                    String nomeAttributo = lettore.getLocalName();
                    switch (nomeAttributo) {
                        case "tree":
                            break;
                        case "node":
                            try {
                                g.aggiungiNodo(nodoTemp);
                            } catch(NullPointerException ex) { System.out.println("Grafo non istanziato"); }
                            break;
                        case "label":
                            nodoTemp.setEtichetta(dati);
                            break;
                        case "edge":
                            g.lega(nodoTemp, g.getNodo(dati), Integer.parseInt(costo));
                            break;
                        case "edges":
                            break;
                    }
                    break;

	            case XMLStreamConstants.END_DOCUMENT:
//	                System.out.println("Fine Documento...");
	                break;
            }
        }	
		return g;
	}
	
	/**
	 * @param fileName il nome del file da scrivere
	 */
	public void toXML(String fileName) throws IOException, XMLStreamException{
		File file = new File(fileName);
		if(file.exists())
			file.delete();
		file.createNewFile();
		
		XMLOutputFactory fattoria = XMLOutputFactory.newInstance();
		XMLStreamWriter scrittore = fattoria.createXMLStreamWriter(new FileOutputStream(file));
		
		scrittore.writeStartDocument();
		scrittore.writeCharacters("\n");		
		scrittore.writeStartElement("order");
		scrittore.writeCharacters("\n");
		scrittore.writeStartElement("meta");
		scrittore.writeCharacters("\n");
			scrittore.writeStartElement("name");
				scrittore.writeCharacters("Krusko Output");
			scrittore.writeEndElement();
			scrittore.writeCharacters("\n");
		scrittore.writeEndElement();
		scrittore.writeCharacters("\n");
		scrittore.writeStartElement("tree");
		scrittore.writeCharacters("\n");
		ArrayList<Nodo> nodiScritti = new ArrayList<>();
		for(Nodo n: nodi){
			nodiScritti.add(n);
			scrittore.writeStartElement("node");
			if(n.isPrimo())
				scrittore.writeAttribute("start", "true");;
			if(n.isUltimo())
				scrittore.writeAttribute("end", "true");;
			scrittore.writeCharacters("\n");
				scrittore.writeStartElement("label");
					scrittore.writeCharacters(n.getEtichetta());
				scrittore.writeEndElement();
				scrittore.writeCharacters("\n");
				scrittore.writeStartElement("edges");
				scrittore.writeCharacters("\n");
				for(Legame l: n.getLegami()){
					if(nodiScritti.contains(l.getNodo())){
					scrittore.writeStartElement("edge");
						scrittore.writeAttribute("weight", ""+l.getCosto());
						scrittore.writeCharacters(l.getNodo().getEtichetta());
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
		scrittore.writeEndElement();
		
		scrittore.flush();
	}
	
	
	/**
	 * Controlla se il Grafo contiene un ciclo
	 * @return true se è ciclico, false altrimenti
	 */
	public boolean isCiclico(){
		for(Nodo n: nodi){
			boolean[] visited = new boolean[getNumNodi()];
			for(int i=0; i<getNumNodi(); i++)
				visited[i] = false;
			if(dfs(n, null, visited))
				return true;
		}
		return false;
	}
	
	/**
	 * Funzione ricorsiva usata dal metodo "isCiclico()"
	 * @param nodo il Nodo attuale
	 * @param padre il Nodo padre
	 * @param visited l'array di booleane che tiene conto dei Nodi già visitati
	 * @return true se il Grafo è ciclico, false altrimenti
	 */
	private boolean dfs(Nodo nodo, Nodo padre, boolean[] visited){
		visited[getIndiceNodo(nodo.getEtichetta())] = true;
		
		for(Nodo vicino: nodo.getVicini())
			if(visited[getIndiceNodo(vicino.getEtichetta())]){
				if(!vicino.equals(padre))
					return true;
			} else
				if(dfs(vicino, nodo, visited))
					return true;
		return false;
	}
	
	/**
	 * Crea un Grafo partendo da quello corrente e applicando l'algoritmo di Kruskal
	 * @return il Grafo creato
	 */
	public Grafo Kruskal(){
		// classe usata per tenere conto dei legami tra i nodi e relativi costi
		class Arco{
			String n1, n2;
			int costo;
			
			public Arco(String n1, String n2, int costo){
				this.n1 = n1;
				this.n2 = n2;
				this.costo = costo;
			}
			
			public String getN1() { return n1; }
			
			public String getN2() { return n2; }
			
			public int getCosto() { return costo; }
			
			/**
			 * Controlla se l'arco è uguale a un'altro
			 * @param arco
			 * @return
			 */
			public boolean equals(Arco arco) { 
				return ((n1.equals(arco.getN1()) && n2.equals(arco.getN2())) || (n1.equals(arco.getN2()) && n2.equals(arco.getN1())));
			}
			
			@Override
			public String toString() {	return n1 + "-" + n2 + " (" + costo + ")"; }
		}
		
		ArrayList<Arco> archi = new ArrayList<>();
		for(Nodo nodoTemp: nodi){
			for(Legame legameTemp: nodoTemp.getLegami()){
				Arco arco = new Arco(nodoTemp.getEtichetta(), legameTemp.getNodo().getEtichetta(), legameTemp.getCosto());
				boolean nonce = true;
				for(Arco arcoTemp: archi)
					if(arcoTemp.equals(arco))
						nonce = false;
				if(nonce)
					archi.add(arco);
			}
		}		

        Collections.sort(archi, new Comparator<Arco>() {
            @Override
            public int compare(Arco a1, Arco a2) { return a1.getCosto() - a2.getCosto(); }
        }); 
		
		Grafo krusko = new Grafo();			
		for(Nodo nodoTemp: nodi){
			Nodo n = new Nodo(nodoTemp.getEtichetta());
			if(nodoTemp.isPrimo()) n.setPrimo();
			else if(nodoTemp.isUltimo()) n.setUltimo();
			krusko.aggiungiNodo(n);
		}
			
		
		for(Arco arcoTemp: archi){
			krusko.lega(arcoTemp.getN1(), arcoTemp.getN2(), arcoTemp.getCosto());
			if(krusko.isCiclico())
				krusko.slega(arcoTemp.getN1(), arcoTemp.getN2());			
		}		
		return krusko;
	}	
		
	public String toString(){
		String s = "";
		for(Nodo n: nodi)
			s += n + "\n";
		return s;
	}
}
