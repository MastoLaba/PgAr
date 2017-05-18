package enoteca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Classe di utility per la lettura del file enoteca.xml
 * @author qwertyteam
 * @version 1.0
 *
 */

public class ParserEnoteca {
	ArrayList<Vino> enoteca = new ArrayList<Vino>();
	File filename;
	
	/**
	 * Costruttore della classe ParserEnoteca.
	 * @param filename Nome del file da leggere
	 * @throws FileNotFoundException Eccezione lanciata se il file non è stato trovato
	 * @throws XMLStreamException Eccezione lanciata in caso di errore nello stream XML
	 */
	
	public ParserEnoteca(String filename) throws FileNotFoundException, XMLStreamException{
		
		try{
			this.filename=new File(filename);
		}catch(Exception e){
			System.out.println("Il file a " + filename + " non è disponibile, o il path è errato.");
			return;
		}
		
		/*
		 *	Inizializzazione oggetti libreria StAX 
		 */
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));
		
		/*
		 * Inizializzazione variabili utili per la lettura del documento XML
		 */
		Vino vinoTemporaneo = null;
		char valuta = ' ';
		String data = "";
		
		/*
		 * Ciclo di lettura del documento XML
		 */
		
		while(reader.hasNext()){
			switch(reader.next()){
				case XMLStreamConstants.START_DOCUMENT:
					System.out.println("Inizio a leggere il documento...");
					break;
				
				case XMLStreamConstants.START_ELEMENT:
					if("wines".equals(reader.getLocalName())){
						enoteca= new ArrayList<Vino>();
						System.out.println("Inizio a creare l'enoteca...\n");
					}
					
					if("wine".equals(reader.getLocalName())){
						vinoTemporaneo = new Vino();
					}
					
					if("price".equals(reader.getLocalName())){
						if("val".equals(reader.getAttributeName(0).toString())){
							valuta = reader.getAttributeValue(0).toString().charAt(0);
							vinoTemporaneo.setValuta(valuta);
						}
					}
					
					break;
					
				case XMLStreamConstants.CHARACTERS:
					if(reader.getText().trim().length()>0){
						data = reader.getText().trim();
					}else{
						data = "";
					}
					
					break;
					
				case XMLStreamConstants.END_ELEMENT:
					switch(reader.getLocalName()){
						case "wines":
							System.out.println("Lettura enoteca completata!");
							break;
						
						case "name":
							if(!data.equals("Enoteca"))
								vinoTemporaneo.setNomeVino(data);
							break;
						
						case "wine":
							enoteca.add(vinoTemporaneo);
							vinoTemporaneo=null;
							break;
						
						case "date":
							int annata = Integer.parseInt(data);
							vinoTemporaneo.setAnnataVino(annata);
							break;
						
						case "geo":
							vinoTemporaneo.setLocazioneGeografica(data);
							break;
						
						case "price":
							double prezzo = Double.parseDouble(data);
							vinoTemporaneo.setPrezzoVino(prezzo);
							break;
						
						case "cont": 
							if(!data.equals("")){
								int cont = Integer.parseInt(data);
								vinoTemporaneo.setInDeposito(cont);
							}
							break;
							
						case "farmer":
							vinoTemporaneo.setProduttore(data);
							break;
					}
					break;
				
				case XMLStreamConstants.END_DOCUMENT:
					System.out.println("Lettura documento completata!");
					break;
				
			}
		
		}
	}
	
	/**
	 * Metodo che permette di restituire una versione clonata dell'enoteca in modo
	 * che l'originale non venga modificata.
	 * @return Versione clonata dell'enoteca.
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList<Vino> getEnoteca(){
		return (ArrayList<Vino>)enoteca.clone();
	}
}
