package deathstar.consegna2;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public final class ParserXMLEnoteca {

	private static final String WINES = "wines";
	private static final String WINE = "wine";
	private static final String NAME = "name";
	private static final String WINERY = "farmer";
	private static final String PRICE = "price";
	private static final String DATE = "date";
	private static final String GEO = "geo";
	private static final String VAL = "val";

	private static final String START_READING = "Lettura del file iniziata...";
	private static final String END_READING = "Terminata lettura del file";
	
	public static ArrayList<Vino> XMLUtil(String filename) throws XMLStreamException {
		
		// Oggetti XML
		XMLStreamReader reader = null;
		FileInputStream input_stream = null;
		
		// Oggetti enoteca
		File file_vini;
		ArrayList<Vino> lista_vini = null;
		// Oggetti per memorizzare gli input.
		Vino wine = null;
		Prezzo price = null;
		
		// Varie variabili
		String name = null;
		Valuta val = null;
		int date = 0;
		double cost = 0.0;
		String geo = null;
		String winery = null;
		
		// Variabile che varia.
		String data = null;
		
		try {
			file_vini = new File(filename);
			input_stream = new FileInputStream(file_vini);
		}
		catch(Exception e){
			System.out.println("File at " + filename + " is not avaiable or correctly patthed");
			return null;
		}
		XMLInputFactory factory = XMLInputFactory.newInstance();
		reader = factory.createXMLStreamReader(input_stream);
		
		while(reader.hasNext()){
			switch(reader.next()){
				case XMLStreamConstants.START_DOCUMENT:
					System.out.println(START_READING);
					break;
					
				case XMLStreamConstants.START_ELEMENT:
					switch (reader.getLocalName().toLowerCase()) {
					case WINES:
						lista_vini = new ArrayList<Vino>();
						break;
					case WINE:
						break;
					case NAME:
						break;
					case PRICE:
						if (reader.getAttributeLocalName(0).equals(VAL)) {
							val = Valuta.getValuta(reader.getAttributeValue(0));
						}
						break;
					}
					break;
					
				case XMLStreamConstants.CHARACTERS:
					if(reader.getText().trim().length() > 0) {
						data = reader.getText().trim();
					}
					break;
					
				case XMLStreamConstants.END_ELEMENT:
					switch (reader.getLocalName()) {
					case WINES:
						break;
					case WINE:
						wine = new Vino(name, winery, date, geo, price);
						lista_vini.add(wine);
						break;
					case NAME:
						name = data;
						data = null;
						break;
					case PRICE:
						cost = Double.parseDouble(data);
						price = new Prezzo(cost, val);
						data = null;
						break;
					case DATE:
						date = Integer.parseInt(data);
						break;
					case GEO:
						geo = data;
					case WINERY:
						winery = data;
						break;
					}
					break;
				
				case XMLStreamConstants.END_DOCUMENT:
					System.out.println(END_READING);
					break;
				
			}
		}
	return lista_vini;	
	}
}