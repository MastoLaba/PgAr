package deathstar.consegna1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

//Attenzione: in input ci � fornito anche quale nodo � END quindi oltre a trovare l'albero dei cammini minimi bisogner� visualizzare il cammino ottimo da B fino ad E
/** Classe per il parsing di un file XML contenente la rappresentazione di un grafo per la
 *  generazione dei Nodi di tale grafo e la produzione di un file XML contenente una analoga rappresentazione
 *	
 * @author Andrea Rossi - <andrea.rossi.it@ieee.org>
 * @author Alessandro Ghirardi Luna
 * @author Lorenzo Nodari
 * @author Michele Dusi
 */
public class XMLUtil { 
	
	//Attributi
	private Vector<Node> tree; //Albero dei cammini minimi ottenuto dopo aver utilizzato gli algoritmi delle sottoclassi di Grafo
	//private boolean lastimport = false; //Mai utilizzata?
	private File filename; //Contiene il riferimento al file da cui ottenere i dati
	
	//Costruttore
	/**
	 * Costruisce un istanza di FromXML2Nodes, utilizzando il file alla locazione fornita come parametro, con attributi:
	 * - File contenente un testo in formato XML.
	 * - Albero dei cammini minimi di un grafo salvato in un Vector di nodi del grafo.
	 * - last import 
	 * 
	 * @param filename Stringa con il percorso del da cui ottenere le informazioni.
	 * @throws FileNotFoundException 
	 * @throws XMLStreamException
	 */
	public XMLUtil(String filename) throws FileNotFoundException, XMLStreamException{
		
		try {
			this.filename = new File(filename);
		}
		catch(Exception e){
			System.out.println("File at " + filename + " is not avaiable or correctly patthed");
			return;
		}
		
		XMLInputFactory factory = XMLInputFactory.newInstance();                                        //Costruzione di un oggetto in grado di leggere file in formato XML e
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));		//impostazione del nostro file a fonte da cui leggere XML
		
		Node tmp = null;  //Definizione di alcune variabili locali per poter costruire l'albero
		String data = "";
		@SuppressWarnings("unused")
		String reference = "";
		String weight = ""; 
		
		while(reader.hasNext()){
			switch(reader.next()){
				case XMLStreamConstants.START_DOCUMENT:			//Prima stampa
					System.out.println("Start reading Doc");
					break;
					
				case XMLStreamConstants.START_ELEMENT:								//Analisi degli elementi inizio tag
					if("tree".equals(reader.getLocalName())){							//Verifica presenza di un albero quindi 
						tree= new Vector<Node>();											//Costruzione di un albero vuoto
						System.out.println("Start reading tree");							//Stampa inizio scansione albero
					}
					if("node".equals(reader.getLocalName())){							//Verifica presenza di un nodo
						tmp = new Node();													//Costruzione di un nodo con valori di default
						//TODO improve detect attributes 
						if("start".equals(reader.getAttributeLocalName(0)))						//Verifica propriet�: nodo iniziale
							if("true".equals(reader.getAttributeValue(0)))						//Verifica valore propriet�
								tmp.setAsStart();													//Verifica valore propriet�
						if("end".equals(reader.getAttributeLocalName(0)))						//Verifica propriet�: nodo finale  
							if("true".equals(reader.getAttributeValue(0)))						//Verifica valore propriet�
								tmp.setAsEnd();														//Verifica valore propriet�
					}
					if("edges".equals(reader.getLocalName())){							//Verifica presenza di pi� archi/collegamenti (appartenenti al nodo appena individuto)
						reference = tree.lastElement().getLabel();								//Salva l'id del nodo appena inserito in una delle variabili locali Modificato: da getID a getLabel
					}
					if("edge".equals(reader.getLocalName())){							//Verifica presenza uno degli archi
						if("weight".equals(reader.getAttributeLocalName(0))){			//Verifica presenza peso/costo arco N.B. Se non ci fosse?---> weight = "";
							weight = reader.getAttributeValue(0).toString().trim();				//Salva il peso, dell'arco che parte dal nodo appena inserito, in una delle variabili locali
							}
					}
					break;
					
				case XMLStreamConstants.CHARACTERS:									//Analisi dei caratteri
					if(reader.getText().trim().length()>0){								//Verifica la presenza di caratteri
						data = reader.getText().trim();										//Salva il carattere del nodo o della destinazione dell'arco in una delle variabili locali
					}
					break;
					
				case XMLStreamConstants.END_ELEMENT:								//Analisi degli elementi fine tag
					switch(reader.getLocalName()){										
						case "tree":												//Fine acquisizione dell'albero
							System.out.println("Acquiring tree, completed!");			//Ultima stampa
							break;
						case "node":												//Fine acquisione dati relativi ad un nodo
							tree.add(tmp);												//Aggiunta del nodo in fondo all'albero dei cammini minimi
							tmp = null;													//libera la variabile locale dal suo riferimento puntandola nuovamente a null
							break;
						case "label":												//Fine acquisizione di un etichetta
							//tmp.setID(data);											
							tmp.setLabel(data);
							break;
						case "edge":
							//TODO maybe node still not exist
							tmp.addArc(getNodeByLabel(data), weight); //Modifica: da getNodeByID a get getNodeByLabel e da addNode a addArc
							weight="";
							break;
						case "edges":
							break;
					}
					break;
				
				case XMLStreamConstants.END_DOCUMENT:
					System.out.println("End reading Doc");
					break;
				
			}
		}
		
	}
	
	
	/*public boolean avaiable(){
		return lastimport;
	}*/
	
	private Node getNodeByLabel(String label){ //Modifica: da getNodeByID a get getNodeByLabel
		for(Node i:tree)
			if(i.getLabel().equals(label))
				return i;
		return null;
	}
	
	//Getter per la lista di Nodi creata dal parser.
	public Vector<Node> getNodi() {
		return tree;
	}
	
	// Setter per la lista di nodi.
	public void setNodes(Vector<Node> new_nodes) {
		this.tree = new_nodes;
	}
	
	
	//toString
	@Override
	public String toString(){
		String data = "";
		for(Node i : tree)
			data += "\n" + i.toString();
		return data;
	}
	
	//Per salvare il grafo in XML
	public boolean save(String filename) throws XMLStreamException{
		System.out.println("Scrittura su file");
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			writer = output.createXMLStreamWriter(new FileWriter(filename));

			writer.writeComment("data saved");
			writer.writeStartDocument("utf-8","1.0");
			//writer.setPrefix("", "");
			writer.writeStartElement("tree");
			for(Node i: tree){
				writer.writeStartElement("node");
				if(i.isStart()){
					writer.writeAttribute("start","true");
				}
				if(i.isEnd()){
					writer.writeAttribute("end","true");
				}
				writer.writeStartElement("label");
				writer.writeCharacters(i.getLabel());
				writer.writeEndElement();// End label
				if(i.hasArcs()){
					writer.writeStartElement("edges");
					for(Node j: i.getLinked()){						//scorre tutti i Nodi addiacenti al "Nodo parametro implicito"
						writer.writeStartElement("edge");
						writer.writeAttribute("weight", i.getWeightArc(j.getLabel()));				//modificato da .getID() a .getLabel 
						writer.writeCharacters(j.getLabel());									//modificato da .getID() a .getLabel
						writer.writeEndElement();//end Edge
					}
					writer.writeEndElement(); // End edges
				}
				writer.writeEndElement(); // End node
			}
			writer.writeEndElement(); // End Tree
			writer.writeEndDocument(); //End Document
			writer.flush();
			writer.close();
			System.out.println("End!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Vecchio, problema!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
