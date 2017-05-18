import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Classe finalizzata alla lettura del file XML contenente l'insieme dei numeri da analizzare.
 * @author qwertyteam
 * @version 1.0
 */

public class XMLReader {
	private ArrayList<Integer> bases;
	private ArrayList<Integer> exponents;
	private File filename;
	
	/**
	 * Costruttore della classe XMLReader.
	 * @param filename Nome del file da leggere in input
	 * @throws FileNotFoundException Eccezione lanciata in caso di file non trovato
	 * @throws XMLStreamException Eccezione lanciata in caso di problemi di lettura dello
	 * stream input
	 */
	
	public XMLReader(String filename) throws FileNotFoundException, XMLStreamException{
		try{
			this.filename=new File(filename);
		}catch(Exception e){
			System.out.println("File at "+filename+" is not avaiable or correctly patthed");
			return;
		}
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));
		bases = new ArrayList<>();
		exponents = new ArrayList<>();
		
		while(reader.hasNext()){
			switch(reader.next()){
				case XMLStreamConstants.START_DOCUMENT:
					System.out.println("Inizio lettura DOC");
					break;
					
				case XMLStreamConstants.START_ELEMENT:
					if("candidates".equals(reader.getLocalName())){
						System.out.println("Inizio a leggere i candidati");
					}
					
					if("candiate".equals(reader.getLocalName())){
						//TODO improve detect attributes
						if("b".equals(reader.getAttributeLocalName(0))){
							bases.add(Integer.parseInt(reader.getAttributeValue(0)));
						}
						if("e".equals(reader.getAttributeLocalName(1))){
							exponents.add(Integer.parseInt(reader.getAttributeValue(1)));
						}
					}
					break;
					
				case XMLStreamConstants.END_DOCUMENT:
					System.out.println("Termine lettura DOC.");
					break;
				
			}
		}
	}
	
	/**
	 * Metodo getter che permette di restituire una versione clonata dell'ArrayList
	 * contenente l'insieme delle basi di tutti i numeri dell'elenco.
	 * @return Clone dell'ArrayList delle basi dei numeri contenuti nel file.
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getBases(){
		return (ArrayList<Integer>)bases.clone();
	}
	
	/**
	 * Metodo getter che permette di restituire una versione clonata dell'ArrayList
	 * contenente l'insieme degli esponenti di tutti i numeri dell'elenco.
	 * @return Clone dell'ArrayList degli esponenti dei numeri contenuti nel file.
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getExponents(){
		return (ArrayList<Integer>)exponents.clone();
	}
}
