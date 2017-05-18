package it.unibs.ing.ieee.enoteca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.w3c.dom.stylesheets.LinkStyle;

public class XmlParser {

	File filename;

	public Winehouse parseXml(String filename) throws FileNotFoundException, XMLStreamException {

		// Apre il file e controlla se esiste nella directory
		try {
			this.filename = new File(filename);
		} catch (Exception e) {
			System.out.println("Il file " + filename + " non è disponibile o non è presente nella directory");
			return null;
		}

		// Inizializzo le variabili
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));

		Winehouse listWines = null;
		Wine tmp = null;
		String data = "";
		Currency currency = null;

		// Ciclo di lettura file (finchè c'è da leggere)
		while (reader.hasNext()) {
			switch (reader.next()) {
			case XMLStreamConstants.START_DOCUMENT:
				System.out.println("Inizio a leggere il documento");
				break;

			case XMLStreamConstants.START_ELEMENT:
				switch (reader.getLocalName()) {
				case "wines":
					// Se trovo il tag <wines> creo listWines
					listWines = new Winehouse();
					System.out.println("Inizio a leggere l'albero");
					break;
				case "wine":
					// Se trovo il tag <wine>, creo il wine
					tmp = new Wine();
					break;
				case "price":
					currency = Currency.getCurrency(reader.getAttributeValue(null, "val"));
					break;
				}
				break;

			// Leggo i valori tra i tag di apertura e chiusura
			case XMLStreamConstants.CHARACTERS:
				if (reader.getTextLength() > 0) {
					data = reader.getText().trim();
				}
				break;

			// I tag di chiusura
			case XMLStreamConstants.END_ELEMENT:
				switch (reader.getLocalName()) {
				case "wines":
					System.out.println("Ho finito di leggere il documento");
					break;
				case "wine":
					listWines.getWineList().add(tmp);
					tmp = null;
					break;
				case "name":
					tmp.setName(data);

					break;
				case "price":
					tmp.setPrice(Double.parseDouble(data));
					tmp.setCurrency(currency);
					break;
				case "cont":
					if (data.equals("")) {
						tmp.setCount(1);
					} else {
						tmp.setCount((int) Integer.parseInt(data));
					}

					break;
				case "date":
					tmp.setYear((int) Integer.parseInt(data));

					break;
				case "geo":
					tmp.setPlace(data);

					break;
				case "farmer":
					tmp.setWinery(data);

					break;
				}
				break;

			case XMLStreamConstants.END_DOCUMENT:
				System.out.println("Ho finito di leggere il documento");
				break;

			}

		}
		return listWines;
	}

}
