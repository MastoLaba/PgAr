import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Classe di utilità per la lettura e scrittura di file XML.
 * @author Qwertyteam
 * @version 1.4
 * @since 1.4
 */

public class XMLWriter {
	public static final String INITIAL_WRITE_STATE = "Inizio scrittura file XML su disco.";
	public static final String FINAL_WRITE_STATE = "Scrittura su disco completata.";
	public static final String WRITE_ERROR = "Scrittura su disco fallita.";
	
	/**
	 * Metodo che permette la scrittura dell'albero dei cammini minimi su disco.
	 * @param fileName Nome del file
	 * @param shortestPath Albero dei cammini minimi
	 * @param matrix Matrice delle Adiacenze
	 * @throws XMLStreamException Errore nello Stream su disco del file XML
	 */
	public static void executeWrite(String fileName, Vector<Nodo> shortestPath, MatriceAdiacenze matrix) throws XMLStreamException{
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			System.out.println();
			System.out.println(INITIAL_WRITE_STATE);
			writer = output.createXMLStreamWriter(new FileWriter(fileName));
			writer.writeStartDocument("UTF-8","1.0");
			writer.writeStartElement("tree");
			for(int i = 0; i < shortestPath.size(); i++){
				writer.writeStartElement("node");
				if(shortestPath.get(i).isSourceNode()){
					writer.writeAttribute("start","true");
				}
				if(shortestPath.get(i).isDestinationNode()){
					writer.writeAttribute("end","true");
				}
					writer.writeStartElement("label");
					writer.writeCharacters(Character.toString(shortestPath.get(i).getLabel()));
					
					if(i < shortestPath.size()-1){
						writer.writeStartElement("edge");
						writer.writeAttribute("weight", Integer.toString((int)matrix.getAdjacencyMatrix()[shortestPath.get(i).getIDNode()][shortestPath.get(i+1).getIDNode()]));
						writer.writeCharacters(Character.toString(shortestPath.get(i+1).getLabel()));									
						writer.writeEndElement();
						writer.writeEndElement(); 
					}
				writer.writeEndElement(); 
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
