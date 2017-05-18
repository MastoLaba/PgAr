package it.unibs.ing.ieee.iproblemi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Classe per l'acquisizione di dati da file XML
 * 
 * @author RAD
 * @author Daniele Vezzoli
 *
 */
public class XmlParser {

	File filename;
	long base, exp;

	ArrayList<BigNumber> lista;

	public ArrayList<BigNumber> parseXml(String filename) throws FileNotFoundException, XMLStreamException {

		// Apre il file e controlla se esiste nella directory
		try {
			this.filename = new File(filename);
		} catch (Exception e) {
			System.err.println("Il file " + filename + " non è disponibile o non è presente nella directory");
			return null;
		}

		// Inizializzo le variabili
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));

		// Ciclo di lettura file (finchè c'è da leggere)
		while (reader.hasNext()) {
			switch (reader.next()) {
			case XMLStreamConstants.START_DOCUMENT:
				System.out.println("Inizio a leggere il documento");
				break;

			case XMLStreamConstants.START_ELEMENT:
				switch (reader.getLocalName()) {
				case "candidates":
					lista = new ArrayList<>();
					break;
				case "candiate":
					base = Long.parseLong(reader.getAttributeValue(null, "b"));
					exp = Long.parseLong(reader.getAttributeValue(null, "e"));
					BigNumber tmp = new BigNumber(base, exp);
					lista.add(tmp);
					break;
				}
				break;

			// Leggo i valori tra i tag di apertura e chiusura
			case XMLStreamConstants.CHARACTERS:
				break;

			// I tag di chiusura
			// case XMLStreamConstants.END_ELEMENT:
			// switch (reader.getLocalName()) {
			// case "candiate":
			// BigNumber tmp = new BigNumber(base, exp);
			// lista.add(tmp);
			// }
			// break;

			case XMLStreamConstants.END_DOCUMENT:
				break;

			}

		}
		return lista;
	}

}