package deathstar.consegna3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Classe che risolve il problema A.
 * Ricerca, cioè, tutti i multipli di 3 e 5 inferiori ad un dato numero limite, li somma
 * e stampa su file il risultato in due casi: nel primo caso i multipli comuni vengono conteggiati sia per il
 * primo numero (3) che per il secondo (5), quindi la loro somma viene raddoppiata; nel secondo caso invece
 * ogni multiplo (che sia di 3, di 5 o comune) viene considerato e sommato una e una sola volta.
 *
 * @author Michele Dusi
 * @author Lorenzo Nodari
 */

public final class ProblemA {
	
	private static final int NUM1 = 3;
	private static final int NUM2 = 5;
	private static final int MCM = 15;
	private static final int [] LIMIT = {10, 100, 1000};
	private static final int BUFF_SIZE = 1750;

	private static final String TIME_DISCLAIMER = "I tempi di esecuzione sono contenuti nel file \"Time.xml\"";
	private static final String ROOT_XML_TAGNAME = "solutions";
	private static final String SOLUTION_XML_TAGNAME = "solution";
	private static final String BELOW_XML_ATTRIBUTENAME = "below";
	private static final String ONCE_XML_ATTRIBUTENAME = "once";
	private static final String ALL_XML_ATTRIBUTENAME = "all";
	
	/**
	 * Metodo principale della classe.
	 * Risolve il problema A e si occupa della scrittura su file delle soluzioni.
	 * 
	 * @param Nome del file su cui scrivere.
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public static void solve(String output_file_name) throws XMLStreamException, IOException {
		// Creazione del writer
		XMLOutputFactory factory = XMLOutputFactory.newFactory();
		XMLStreamWriter writer = factory.createXMLStreamWriter(new BufferedWriter(new FileWriter(output_file_name), BUFF_SIZE));
		
		// Scrittura documento
		writer.writeStartDocument();
		writer.writeComment(TIME_DISCLAIMER);
		writer.writeStartElement(ROOT_XML_TAGNAME); // <solutions>
		
		for (int i = 0; i < LIMIT.length; i++) {
			ProblemA.sumMultiplesUnder(LIMIT[i], writer);
		}
		
		writer.writeEndElement(); // </solutions>
		writer.writeEndDocument();
		writer.close();
		
	}

	/**
	 * Metodo che implementa la risoluzione di un singolo sottoproblema, dato il limite massimo.
	 * 
	 * @param Tetto massimo per i multipli di 3 e 5.
	 * @param writer
	 * @throws XMLStreamException
	 */
	private static void sumMultiplesUnder(int max_number, XMLStreamWriter writer) throws XMLStreamException {
		long sum = sommatoria(max_number, NUM1) + sommatoria(max_number, NUM2) - max_number;
		/* Poiché tutti i limiti massimi (identificati con la variabile max_number) sono multipli di 5 ma non di 3, 
		 * possono essere sottratti alla somma una e una sola volta senza effettuare ulteriori controlli.
		 * A proposito, si veda anche il disclaimer nella classe Main.
		 */
		writer.writeEmptyElement(SOLUTION_XML_TAGNAME);
		writer.writeAttribute(BELOW_XML_ATTRIBUTENAME, Integer.toString(max_number));
		writer.writeAttribute(ALL_XML_ATTRIBUTENAME, Long.toString(sum));
		sum -= sommatoria(max_number, MCM); // Sottraggo i multipli comuni (che prima ho contato due volte)
		writer.writeAttribute(ONCE_XML_ATTRIBUTENAME, Long.toString(sum));
	}
	
	/**
	 * Metodo che calcola la somma dei multipli da 1 a max_number di un intero (num) passato come parametro.
	 * 
	 * @param Limite superiore dei multipli.
	 * @param Divisore comune a tutti i multipli.
	 * @return Somma di tutti i multipli.
	 */
	private static long sommatoria(int max_number, int num) {
		long n = max_number / num;
		return ((n * (n + 1) / 2) * num);
	}
}
