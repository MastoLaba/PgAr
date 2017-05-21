import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TreeMap;
import java.util.Vector;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Classe di utility XML per la lettura di clinica.xml, necessaria per aggregare
 * i dati nelle strutture Java apposite.
 * @author qwertyteam
 * @version 1.0
 */

public class XMLReader {
	
	private Vector<Medico> listaMedici;
	private Vector<Paziente> listaPazienti;
	private Vector<Appuntamento> listaAppuntamenti;
	private File filename;
	private final static String START_DOC = "Inizio ad analizzare il codice XML...";
	private final static String PATH_ERROR = "C'è un errore nel path. Ricontrolla la posizione del file.";
	private final static String END_DOC = "Ho finito di analizzare il codice XML.";
	
	/**
	 * Metodo per la lettura del file XML. Viene utilizzata per lo scopo la libreria
	 * StAX. 
	 * @param filename Nome del file da leggere
	 * @throws FileNotFoundException File non trovato nella directory
	 * @throws XMLStreamException Errore nella lettura dello stream XML
	 */
	
	public XMLReader(String filename) throws FileNotFoundException, XMLStreamException{
		
		try{
			this.filename = new File(filename);
		}catch(Exception e){
			System.out.println(PATH_ERROR);
			return;
		}
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));
		listaMedici = new Vector<>();
		listaPazienti = new Vector<>();
		listaAppuntamenti = new Vector<>();
		Medico medico = null;
		Paziente paziente = null;
		Appuntamento appuntamento = null;
		String data = "";
		TreeMap<Integer, Vector<LocalTime>> orariFissiMedico = null;
		Vector<LocalTime> orariMedico = null;
		int annoInizio = 0, meseInizio = 0, giornoInizio = 0, oraInizio = 0, minutoInizio = 0;
		int annoFine = 0, meseFine = 0, giornoFine = 0, oraFine = 0, minutoFine = 0;
		int dayOfWeek = 0, startHour = 0, endHour = 0;
		String urgenza = "";
		
		while(reader.hasNext()){
			switch(reader.next()){	
				case XMLStreamConstants.START_ELEMENT:
					if("clinicaMedica".equals(reader.getLocalName()))
						System.out.println(START_DOC);
					
					if("medico".equals(reader.getLocalName()))
						medico = new Medico();
					
					if("orariFissi".equals(reader.getLocalName()))
						orariFissiMedico = new TreeMap<>();
					
					if("orario".equals(reader.getLocalName())){
						if("dayOfWeek".equals(reader.getAttributeLocalName(0))){
							dayOfWeek = Integer.parseInt(reader.getAttributeValue(0));
						}
						
						if("startHour".equals(reader.getAttributeLocalName(1))){
							startHour = Integer.parseInt(reader.getAttributeValue(1));
						}
						
						if("endHour".equals(reader.getAttributeLocalName(2))){
							endHour = Integer.parseInt(reader.getAttributeValue(2));
						}
					}
					
					if("paziente".equals(reader.getLocalName())){
						paziente = new Paziente();
					}
					
					if("appuntamento".equals(reader.getLocalName())){
						appuntamento = new Appuntamento();
					}
					
					if("inizioData".equals(reader.getLocalName())){
						if("year".equals(reader.getAttributeLocalName(0))){
							annoInizio = Integer.parseInt(reader.getAttributeValue(0));
						}
							
						if("month".equals(reader.getAttributeLocalName(1))){
							meseInizio = Integer.parseInt(reader.getAttributeValue(1));
						}
						
						if("day".equals(reader.getAttributeLocalName(2))){
							giornoInizio = Integer.parseInt(reader.getAttributeValue(2));
						}
						
						if("hour".equals(reader.getAttributeLocalName(3))){
							oraInizio = Integer.parseInt(reader.getAttributeValue(3));
						}
						
						if("minute".equals(reader.getAttributeLocalName(4))){
							minutoInizio = Integer.parseInt(reader.getAttributeValue(4));
						}
					}
					
					if("fineData".equals(reader.getLocalName())){
						if("year".equals(reader.getAttributeLocalName(0))){
							annoFine = Integer.parseInt(reader.getAttributeValue(0));
						}
						
						if("month".equals(reader.getAttributeLocalName(1))){
							meseFine = Integer.parseInt(reader.getAttributeValue(1));
						}
						
						if("day".equals(reader.getAttributeLocalName(2))){
							giornoFine = Integer.parseInt(reader.getAttributeValue(2));
						}
						
						if("hour".equals(reader.getAttributeLocalName(3))){
							oraFine = Integer.parseInt(reader.getAttributeValue(3));
						}
						
						if("minute".equals(reader.getAttributeLocalName(4))){
							minutoFine = Integer.parseInt(reader.getAttributeValue(4));
						}
					}
					
					if("urgenza".equals(reader.getLocalName())){
						if("type".equals(reader.getAttributeLocalName(0)))
							urgenza = reader.getAttributeValue(0);
					}
					
					break;
				
				case XMLStreamConstants.CHARACTERS:
					if(reader.getText().trim().length()>0){
						data = reader.getText().trim();
					}
					break;
					
				case XMLStreamConstants.END_ELEMENT:
					switch(reader.getLocalName()){
						case "medico" :
							listaMedici.add(medico);
							medico = null;
							break;
						
						case "orario":
							orariMedico = new Vector<LocalTime>();
							orariMedico.add(LocalTime.of(startHour, 0));
							orariMedico.addElement(LocalTime.of(endHour, 0));
							orariFissiMedico.put(dayOfWeek, orariMedico);
							orariMedico = null;
							break;
						
						case "orariFissi":
							medico.setOrariFissi(orariFissiMedico);
							orariFissiMedico = null;
							break;
							
						case "paziente" :
							listaPazienti.add(paziente);
							paziente = null;
							break;
						
						case "nomeMedico":
							medico.setNome(data);
							break;
						
						case "cognomeMedico":
							medico.setCognome(data);
							break;
						
						case "id":
							medico.setID(Integer.parseInt(data));
							break;
						
						case "annoLaurea":
							medico.setLaurea(Integer.parseInt(data));
							break;
						
						case "annoAssunzione":
							medico.setAssunzione(Integer.parseInt(data));
							break;
						
						case "nomePaziente":
							paziente.setNome(data);
							break;
						
						case "cognomePaziente":
							paziente.setCognome(data);
							break;
						
						case "appuntamento":
							appuntamento.setInizio(LocalDateTime.of(annoInizio, meseInizio, giornoInizio, oraInizio, minutoInizio));
							appuntamento.setFine(LocalDateTime.of(annoFine, meseFine, giornoFine, oraFine, minutoFine));
							appuntamento.setUrgenza(Appuntamento.Urgenza.valueOf(urgenza));
							listaAppuntamenti.add(appuntamento);
							appuntamento = null;
							break;
					}
					
					break;
					
				case XMLStreamConstants.END_DOCUMENT:
					System.out.println(END_DOC);
					break;
				
			}
		}
	}
	
	/**
	 * Metodo che restituisce la lista dei medici analizzata dal file XML. 
	 * @return Lista dei medici
	 */
	
	public Vector<Medico> getMedici(){
		return listaMedici;
	}
	
	/**
	 * Metodo che restituisce la lista dei pazienti analizzata dal file XML. 
	 * @return Lista dei pazienti
	 */
	
	public Vector<Paziente> getPazienti(){
		return listaPazienti;
	}
	
	/**
	 * Metodo che restituisce la lista degli appuntamenti presenti nel file XML.
	 * @return Lista degli appuntamenti
	 */
	
	public Vector<Appuntamento> getAppuntamenti(){
		return listaAppuntamenti;
	}
}
