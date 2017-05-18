package it.unibs.ing.ieee.iproblemi;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XmlWriter {

	public static void saveTimeStamp(String filename, String timestamp1, String timestamp2) throws XMLStreamException {
		System.out.println("Scrittura su file");

		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			writer = output.createXMLStreamWriter(new FileWriter(filename));

			writer.writeComment("data saved");
			writer.writeStartDocument("utf-8", "1.0");
			writer.writeStartElement("time");
			writer.writeStartElement("timestamp");
			writer.writeCharacters(timestamp1);
			writer.writeEndElement(); // End timestamp1
			writer.writeStartElement("timestamp");
			writer.writeCharacters(timestamp2);
			writer.writeEndElement(); // End timestamp2
			writer.writeComment("Millisecondi");
			writer.writeStartElement("delta");
			writer.writeCharacters(String.valueOf(Double.parseDouble(timestamp2) / TimeStamp.MILLI
					- Double.parseDouble(timestamp1) / TimeStamp.MILLI));
			writer.writeEndElement(); // End delta
			
			writer.writeComment("Secondi");
			writer.writeStartElement("delta");
			writer.writeCharacters(String.valueOf(Double.parseDouble(timestamp2) / TimeStamp.SECOND
					- Double.parseDouble(timestamp1) / TimeStamp.SECOND));
			writer.writeEndElement(); // End delta
			
			writer.writeEndElement(); // End time
			writer.writeEndDocument(); // End Document
			writer.flush();
			writer.close();
			System.out.println("End!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Errore!");
			e.printStackTrace();

		}

	}
}
