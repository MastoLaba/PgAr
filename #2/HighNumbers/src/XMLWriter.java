import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Classe di utilità per la scrittura di file XML.
 * @author qwertyteam
 * @version 1.0
 * @since 1.0
 */

public class XMLWriter {
	public static final String INITIAL_WRITE_STATE = "Inizio scrittura file XML su disco.";
	public static final String FINAL_WRITE_STATE = "Scrittura su disco completata.";
	public static final String WRITE_ERROR = "Scrittura su disco fallita.";
	public static final String LOWEST_VALUE = "lowest";
	public static final String GREATEST_VALUE = "greatest";
	public static final String MEDIANA = "mediana";
	public static final String MEDIANA_INFO = "È la media tra: ";
	
	/**
	 * Metodo che permette di scrivere in output il valore minimo, massimo e mediana
	 * tra l'insieme dei numeri analizzati dal documento XML di input.
	 * @param fileName Nome del file
	 * @param lowestValue Array contenente base ed esponente del valore minimo
	 * @param greatestValue Array contenente base ed esponente del valore massimo
	 * @param mediana Array contenente base/i ed esponente/i della mediana
	 * @throws XMLStreamException Eccezione lanciata in caso di errori nello stream XML
	 */
	
	public static void executeWrite(String fileName, int [] lowestValue,
			int[] greatestValue, int [] mediana) throws XMLStreamException{
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			System.out.println();
			System.out.println(INITIAL_WRITE_STATE);
			writer = output.createXMLStreamWriter(new FileWriter(fileName));
			writer.writeStartDocument("UTF-8","1.0");
			writer.writeStartElement("numbers");
			writer.writeStartElement(LOWEST_VALUE);
			writer.writeAttribute("b", Integer.toString(lowestValue[0]));
			writer.writeAttribute("e", Integer.toString(lowestValue[1]));
			writer.writeEndElement();
			writer.writeStartElement(GREATEST_VALUE);
			writer.writeAttribute("b", Integer.toString(greatestValue[0]));
			writer.writeAttribute("e", Integer.toString(greatestValue[1]));
			writer.writeEndElement();		
			writer.writeStartElement(MEDIANA);
			if(mediana.length == 4){
				writer.writeCharacters(MEDIANA_INFO + "b: " + mediana[0] + " exp: " 
			+ mediana[1] + " e b: " + mediana[2] + " exp: " + mediana[3]);
			}else{
				writer.writeAttribute("b", Integer.toString(mediana[0]));
				writer.writeAttribute("e", Integer.toString(mediana[1]));
			}
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println(WRITE_ERROR);
			e.printStackTrace();
		}
		
		System.out.println(FINAL_WRITE_STATE);
	}
}
