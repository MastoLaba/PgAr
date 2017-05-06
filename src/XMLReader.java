/*Il codice legge correttamente le informazioni dal file xml ma non siamo riusciti ad implementarlo nel programma
 * a causa di negligenza e poca comunicazione iniziale. 
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLReader {

	public void XMLRead() throws XMLStreamException, FileNotFoundException{
		XMLInputFactory factory = XMLInputFactory.newInstance();
		//creazione del primo lettore
	    Reader fileReader = new FileReader("input.xml");
	    XMLEventReader reader = factory.createXMLEventReader(fileReader);
	    //creazione di un secondo lettore che verrà utilizzato per fare una prima lettura con lo scopo di conoscere il numero di nodi.
	    Reader fileReaderPrimo = new FileReader("input.xml");
	    XMLEventReader readerPrimo = factory.createXMLEventReader(fileReaderPrimo);
	    Vector<Nodo> nodi = new Vector<Nodo>();
	    int i = 1; //l'id del nodo (ci ho pensato solo alla fine che avrei dovuto chiamarlo id), parte ad 1 perchè lo 0 è usato esculisvamente per il primo nodo 
	    int numeroNodi = 0; // variabile che indica il numero di nodi
	    while(readerPrimo.hasNext()){
	    	XMLEvent event = readerPrimo.nextEvent();
	    	// ogni volta che legge la parola "node" incrementa di uno la variabile numeroNodi
	    	if(event.isStartElement() && ((StartElement) event).getName().getLocalPart().equals("node"))
	    		numeroNodi++;
	    }
	    MatriceAdiacenze matrix = new MatriceAdiacenze(numeroNodi,false); //Matrice con tutti i collegamenti tra i nodi.
	    //è necessario conoscere il numero dei nodi per poter utilizzarla, che è il motivo per cui sono stati contati precedentemente
	    while (reader.hasNext()) {
	      XMLEvent event = reader.nextEvent();
	      if (event.isStartElement() && ((StartElement) event).getName().getLocalPart().equals("node")) {
	    	//inizializzazione dell'iteratore che leggere gli attributi. Se non ci sono attributi l'iteratore è vuoto  
	        Iterator iterator = ((StartElement) event).getAttributes();
	        if (iterator.hasNext()){
		        while (iterator.hasNext()) {
		          Attribute attribute = (Attribute) iterator.next();
		          QName name = attribute.getName();
		          String value = attribute.getValue();
		          //controllo per sapere se è il nodo iniziale
		          if (name.getLocalPart().equals("start") && value.equals("true")){
		        	  event = reader.nextTag();
		        	  event = reader.nextEvent();
		        	  Characters characters = (Characters) event;
		        	  Nodo nodo = new Nodo(0,characters.getData().charAt(0));
		        	  System.out.println("id del nodo: " + Integer.toString(nodo.getIDNode()) +" Nome del nodo: "+ nodo.getLabel());
		        	  nodi.add(nodo);
		          }
		          //controllo per sapere se è il nodo finale
		          else if (name.getLocalPart().equals("end") && value.equals("true")){
		        	  event = reader.nextTag();
		        	  event = reader.nextEvent();
		        	  Characters characters = (Characters) event;
		        	  Nodo nodo = new Nodo(i,characters.getData().charAt(0));
		        	  nodo.isDestinationNode();
		        	  System.out.println("id del nodo: " + Integer.toString(nodo.getIDNode()) +" Nome del nodo:" + nodo.getLabel());
		        	  i++;
		        	  nodi.add(nodo);
		          }
		        }
	        }
		    //nodi senza attributo (nodi intermedi)
	        else{
	        	event = reader.nextTag();
	        	event = reader.nextEvent();
	        	Characters characters = (Characters) event;
	        	Nodo nodo = new Nodo(i,characters.getData().charAt(0));
	        	nodi.add(nodo);
	        	i++;
	        	System.out.println("id del nodo: " + Integer.toString(nodo.getIDNode()) +" Nome del nodo: "+ nodo.getLabel());
	        }	
	      }
	      else if (event.isStartElement() && ((StartElement) event).getName().getLocalPart().equals("edges")){
	    	  while(!(event.isEndElement() && ((EndElement) event).getName().getLocalPart().equals("edges"))){
	    		  event = reader.nextEvent();
		    	  if(event.isStartElement()){
		    		Iterator iterator = ((StartElement) event).getAttributes();
	  	        	if (iterator.hasNext()){
			  		        while (iterator.hasNext()) {
			  		          Attribute attribute = (Attribute) iterator.next();
			  		          QName name = attribute.getName();
			  		          String value = attribute.getValue();
			  		          event = reader.nextEvent();
			  		          Characters characters = (Characters) event;
			  		          System.out.println( name + " = " + value +" Destinazione "+characters.getData());
			  		          
			  		        }
			  	        }       
		    	  }
	    	  }
	      }
	    }
	}
	
}
