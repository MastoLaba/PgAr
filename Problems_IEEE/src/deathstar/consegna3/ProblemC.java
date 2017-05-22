package deathstar.consegna3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/** Classe che risolve il problema C.
 * 
 * @author Michele Dusi
 * @author Lorenzo Nodari
 * 
 */
public final class ProblemC {

	// Utility constants
	private static final int BASE = 0;
	private static final int ESPONENTE = 1;
	private static final int IN_BUFF_SIZE = 1500;
	private static final int OUT_BUFF_SIZE = 1500;
	private static final int NUM_CANDIDATES = 1000;
	
	// File di input
	private static final String INPUT_FILE = "problem_c.xml";
	// XML tags
	private static final String ROOT_XML_TAGNAME = "solutions";
	private static final String SOLUTION_XML_TAGNAME = "solution";
	private static final String CANDIDATE_XML_TAGNAME = "candiate";
	// XML attributes
	private static final String GOAL_XML_ATTRIBUTENAME = "goal";
	private static final String BASE_XML_ATTRIBUTENAME = "b";
	private static final String ESPONENTE_XML_ATTRIBUTENAME = "e";
	private static final String NATURAL_LOGARITHM_XML_ATTRIBUTENAME = "ln";
	private static final String SORTED_INDEX_XML_ATTRIBUTENAME = "sortedindex";
	// Utility Strings
	private static final String TIME_DISCLAIMER = "I tempi di esecuzione sono contenuti nel file \"Time.xml\"";
	private static final String PARITY_DISCLAIMER = "Poiché il numero di elementi è pari, la mediana (per definizione) è la media aritmetica fra i due elementi centrali:";
	
	@SuppressWarnings("unused")
	public static void solve(String output_file_name) throws Exception {

		// Creazione StreamReader
		XMLInputFactory factory = XMLInputFactory.newFactory();

		// Lettura dei candidati
		XMLStreamReader reader = factory.createXMLStreamReader(new BufferedReader(new FileReader(INPUT_FILE), IN_BUFF_SIZE));
		int [][] exp_list = new int [NUM_CANDIDATES][2];
		
		// Salto al primo TAG candidate
		while (reader.next() != XMLStreamConstants.START_ELEMENT || !reader.getLocalName().equals(CANDIDATE_XML_TAGNAME));
		
		/** RICERCA DEL MASSIMO E DEL MINIMO **/
		
		// Variabili ausiliarie temporanee
		int temp_max_base = Integer.parseInt(reader.getAttributeValue(BASE)); // Posso inizializzarli a 0 perché ho per ipotesi numeri Naturali (non negativi).
		int temp_max_esponente = Integer.parseInt(reader.getAttributeValue(ESPONENTE));
		int temp_min_base = temp_max_base;
		int temp_min_esponente = temp_max_esponente;
		int current_base;
		int current_esponente;
		double current_power;

		// Massimo e minimo temporanei.
		double max_power = Math.log(temp_max_base) * temp_max_esponente;
		double min_power = max_power;
		
		// Per tutti i candidati previsti
		int index = 1; // Il primo è già stato letto
		while (index < NUM_CANDIDATES && reader.hasNext()) {
			if (reader.next() == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals(CANDIDATE_XML_TAGNAME)) {
				// Riempio la lista
				current_base = Integer.parseInt(reader.getAttributeValue(BASE));
				current_esponente = Integer.parseInt(reader.getAttributeValue(ESPONENTE));
				exp_list[index][BASE] = current_base;
				exp_list[index][ESPONENTE] = current_esponente;
				// Calcolo la potenza (su scala logaritmica)
				current_power = Math.log(current_base) * current_esponente;
				// Aggiorno eventuali massimo e minimo
				if (current_power > max_power) {
					max_power = current_power;
					temp_max_base = current_base;
					temp_max_esponente = current_esponente;
				} else if (current_power < min_power) { // Se è il nuovo massimo non ha senso chiedersi se è anche il minimo.
					min_power = current_power;
					temp_min_base = current_base;
					temp_min_esponente = current_esponente;
				}
				index++;
			}
		}	
		reader.close();

		// Creazione StreamWriter
		XMLOutputFactory out_factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = out_factory.createXMLStreamWriter(new BufferedWriter(new FileWriter(output_file_name), OUT_BUFF_SIZE));

		// Comincio la scrittura del file di output
		writer.writeStartDocument();
		writer.writeStartElement(ROOT_XML_TAGNAME);
		writer.writeComment(TIME_DISCLAIMER);
		
		// Scrivo la soluzione per il massimo
		writer.writeStartElement(SOLUTION_XML_TAGNAME);
		writer.writeAttribute(GOAL_XML_ATTRIBUTENAME, "Calcolo della potenza con valore MASSIMO.");
		
		writer.writeEmptyElement(CANDIDATE_XML_TAGNAME);
		writer.writeAttribute(BASE_XML_ATTRIBUTENAME, Integer.toString(temp_max_base));
		writer.writeAttribute(ESPONENTE_XML_ATTRIBUTENAME, Integer.toString(temp_max_esponente));
		writer.writeAttribute(NATURAL_LOGARITHM_XML_ATTRIBUTENAME, Double.toString(max_power));
			
		writer.writeEndElement();
		
		// Scrivo la soluzione per il minimo
		writer.writeStartElement(SOLUTION_XML_TAGNAME);
		writer.writeAttribute(GOAL_XML_ATTRIBUTENAME, "Calcolo della potenza con valore MINIMO.");
		
		writer.writeEmptyElement(CANDIDATE_XML_TAGNAME);
		writer.writeAttribute(BASE_XML_ATTRIBUTENAME, Integer.toString(temp_min_base));
		writer.writeAttribute(ESPONENTE_XML_ATTRIBUTENAME, Integer.toString(temp_min_esponente));
		writer.writeAttribute(NATURAL_LOGARITHM_XML_ATTRIBUTENAME, Double.toString(min_power));
		
		writer.writeEndElement();
		
		/** CALCOLO DELLA MEDIANA **/
		
		int tmp = 0;
		// Implemento un BubbleSort interessandomi solamente all'elemento centrale.
		// (Di fatto ordino ogni volta gli (n-2i) elementi centrali;
		// (Per i che scorre dall'inizio alla metà).
		for (int j = 0; j < NUM_CANDIDATES / 2 + 1; j++) {
			for (int k = j; k < NUM_CANDIDATES - j - 1; k++) {
				if (Math.log(exp_list[k][BASE]) * exp_list[k][ESPONENTE] > Math.log(exp_list[k + 1][BASE]) * exp_list[k + 1][ESPONENTE]) {
					// Scambio le basi
					tmp = exp_list[k][BASE];
					exp_list[k][BASE] = exp_list[k + 1][BASE];
					exp_list[k + 1][BASE] = tmp;
					
					// Scambio gli esponenti
					tmp = exp_list[k][BASE];
					exp_list[k][BASE] = exp_list[k + 1][BASE];
					exp_list[k + 1][BASE] = tmp;
				}
			}
		}

		// Scrivo la soluzione per la mediana
		writer.writeStartElement(SOLUTION_XML_TAGNAME);
		writer.writeAttribute(GOAL_XML_ATTRIBUTENAME, "Calcolo della MEDIANA della sequenza di potenze.");
			
		// Casistica pari/dispari (la definizione di mediana cambia a seconda della parità del numero di elementi).
		double k_medio = 0;
		int index_medio = NUM_CANDIDATES / 2;

		/* Sappiamo che il controllo della parità del numero di candidati è superfluo, ma abbiamo voluto 
		 * (almeno in questo caso) utilizzare la definizione di mediana corretta, con entrambe le casistiche.
		 */
		if (NUM_CANDIDATES % 2 == 0) {
			// CASO PARI -> Media aritmetica dei due numeri centrali adiacenti.
			double k1 = Math.log(exp_list[index_medio - 1][BASE]) * exp_list[index_medio - 1][ESPONENTE];
			double k2 = Math.log(exp_list[index_medio][BASE]) * exp_list[index_medio][ESPONENTE]; // Per come sono definiti, so per certo che k1 < k2;
			// Metodo esatto
			k_medio = k2 + Math.log(1 + Math.exp(k1 - k2)) - Math.log(2);
			
			writer.writeComment(PARITY_DISCLAIMER);
			writer.writeEmptyElement(CANDIDATE_XML_TAGNAME);
			writer.writeAttribute(BASE_XML_ATTRIBUTENAME, Integer.toString(exp_list[index_medio - 1][BASE]));
			writer.writeAttribute(ESPONENTE_XML_ATTRIBUTENAME, Integer.toString(exp_list[index_medio - 1][ESPONENTE]));
			writer.writeAttribute(NATURAL_LOGARITHM_XML_ATTRIBUTENAME, Double.toString(k1));
			writer.writeAttribute(SORTED_INDEX_XML_ATTRIBUTENAME, Integer.toString(index_medio - 1));
			
			writer.writeEmptyElement(CANDIDATE_XML_TAGNAME);
			writer.writeAttribute(BASE_XML_ATTRIBUTENAME, Integer.toString(exp_list[index_medio][BASE]));
			writer.writeAttribute(ESPONENTE_XML_ATTRIBUTENAME, Integer.toString(exp_list[index_medio][ESPONENTE]));
			writer.writeAttribute(NATURAL_LOGARITHM_XML_ATTRIBUTENAME, Double.toString(k2));
			writer.writeAttribute(SORTED_INDEX_XML_ATTRIBUTENAME, Integer.toString(index_medio));
			
			writer.writeComment("La mediana, pertanto, risulta");
			/* Poiché la mediana non coincide con nessun elemento della sequenza di potenze, il tag CANDIDATE
			 * che la contiene non presenta attributi BASE e ESPONENTE, poiché qualunque scelta porterebbe ad una 
			 * perdita di generalità nella soluzione. Pertanto, si è preferito fornire la risposta sotto forma di
			 * logaritmo naturale della potenza, numero che rappresenta l'esponente della base naturale "e".
			 */
			writer.writeEmptyElement(CANDIDATE_XML_TAGNAME);
			writer.writeAttribute(NATURAL_LOGARITHM_XML_ATTRIBUTENAME, Double.toString(k_medio));
			
		} else {
			// CASO DISPARI -> Elemento centrale della lista ordinata.
			k_medio = Math.log(exp_list[index_medio][BASE]) * exp_list[index_medio][ESPONENTE];

			writer.writeEmptyElement(CANDIDATE_XML_TAGNAME);
			writer.writeAttribute(BASE_XML_ATTRIBUTENAME, Integer.toString(exp_list[index_medio][BASE]));
			writer.writeAttribute(ESPONENTE_XML_ATTRIBUTENAME, Integer.toString(exp_list[index_medio][ESPONENTE]));
			writer.writeAttribute(NATURAL_LOGARITHM_XML_ATTRIBUTENAME, Double.toString(k_medio));
			writer.writeAttribute(SORTED_INDEX_XML_ATTRIBUTENAME, Integer.toString(index_medio));
		}
	
		writer.writeEndElement(); // Tag "solution"
		writer.writeEndElement(); // Root tag "solutions"
		writer.close();
	}
}
