package deathstar.consegna3;

import java.io.FileWriter;
import java.io.BufferedWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/** Classer di utilita' per risolvere il problema B assegnato
 * 
 * @author Lorenzo Nodari
 * @authir Michele Dusi
 *
 */
public class ProblemB {
	
	//Il primo array contiene i numeri da utilizzare per generare e combinazioni
	//Il secondo array e' utilizzato come buffer di dati condiviso tra
	//    le varie chiamate ricorsive della funzione che genera le combinazioni
	private static final int[] numbers = {1, 2, 5, 10, 20, 50, 100, 200};
	private static final int[] targets = {100, 200, 250};
	private static String[] comb_holder = new String[numbers.length];
	private static int comb_id = 0;
	private static final int BUFF_SIZE = 15000;
	private static final int BUFF_SIZE_2 = 1024;
	
	private static final String DELIMITER = ",";
	private static final String ZERO_STRING = "0";
	private static final String COMBS_XML_TAGNAME = "comb";
	private static final String ROOT_XML_TAGNAME = "solutions";
	private static final String SUBPROBLEM_XML_TAGNAME = "solution";
	
	//Risolve il problema B generando le combinazioni e non semplicemente contandole
	public static void solveWithCombinations(String output_file_name) throws Exception {
		
		//Creo il writer per generare l'output
		BufferedWriter file_writer = new BufferedWriter(new FileWriter(output_file_name), BUFF_SIZE);
		XMLOutputFactory factory = XMLOutputFactory.newFactory();
		XMLStreamWriter xml_writer = factory.createXMLStreamWriter(file_writer);
			
		//Apro il documento XML e root element
		xml_writer.writeStartDocument();
		xml_writer.writeStartElement(ROOT_XML_TAGNAME);
		xml_writer.writeComment("I coefficienti sono specificati nel seguente ordine: 1c, 2c, 5c, 10, 20c, 50c, 1e, 2e");
			
		//Inizio a risolvere il problema
		for (int i = 0; i < targets.length; i++) {
			//Apro l'elemento corrispondente alla sotto-soluzione
			xml_writer.writeStartElement(SUBPROBLEM_XML_TAGNAME);
			xml_writer.writeAttribute("pound", String.valueOf(targets[i]));
			
			//Prevedo quante saranno le combinazioni prima di generarle
			int num_combs = numCombinations(targets[i], numbers.length);
			xml_writer.writeAttribute("tot", String.valueOf(num_combs));
			
			//Inizio a generare le combinazioni e le scrivo man mano nel file
			//di output
			generateAndWrite(targets[i], numbers.length, xml_writer);
			
			xml_writer.writeEndElement();
			comb_id = 0;
		}
		xml_writer.writeEndDocument();
		xml_writer.close();
	}
	
	//Versione dell'algoritmo generico per il calcolo
	//del numero di combinazioni ottimizzata per il problema.
	//In particolare, questa versione dell'algoritmo fornisce
	//prestazioni nettamente maggiori ma funziona solo se la lista
	//di numeri da combinare contiene l'unita'
	private static int numCombinations(int target, int sub_array_len) {
		if (sub_array_len == 2) {
			return (target / numbers[1] + 1);
		}
		else {
			int combinations = 0;
			int max_number_coeff = 0;
			int tmp = max_number_coeff * numbers[sub_array_len - 1];
			while (tmp < target) {
				combinations += numCombinations(target - tmp, sub_array_len - 1);
				max_number_coeff++;
				tmp = max_number_coeff * numbers[sub_array_len - 1];
			}
			if (tmp == target) {
				combinations++;
			}
			return combinations;
		}
	}
	
	//Utilizzato da solveWithCombinations: genera le combinazioni ricorsivamente e le scrive sul disco
	private static void generateAndWrite(int target, int sub_array_len, XMLStreamWriter writer) throws Exception {
		if (sub_array_len == 2) {
			for (int i = 0; i <= target; i += numbers[1])  {
				comb_holder[1] = String.valueOf(i / numbers[1]);
				comb_holder[0] = String.valueOf(target - i);
				
				//Ho trovato una combinazione corretta, la scrivo nell'xml
				writer.writeStartElement(COMBS_XML_TAGNAME);
				writer.writeAttribute("id", String.valueOf(comb_id));
				writer.writeCharacters(String.join(DELIMITER, comb_holder));
				writer.writeEndElement();
				comb_id++;
			}
		}
		else {
			int coeff_max_number = 0;
			int tmp = coeff_max_number * numbers[sub_array_len - 1];
			while (tmp < target) {
				comb_holder[sub_array_len - 1] = String.valueOf(coeff_max_number);
				generateAndWrite(target - tmp, sub_array_len - 1, writer);
				coeff_max_number++;
				tmp = coeff_max_number * numbers[sub_array_len - 1];
			}
			if (tmp == target) {
				comb_holder[sub_array_len - 1] = String.valueOf(coeff_max_number);
				for (int i = 0; i < sub_array_len - 1; i++)
					comb_holder[i] = ZERO_STRING;
				
				writer.writeStartElement(COMBS_XML_TAGNAME);
				writer.writeAttribute("id", String.valueOf(comb_id));
				writer.writeCharacters(String.join(DELIMITER, comb_holder));
				writer.writeEndElement();
				
				comb_id++;
			}
		}
	}
	
	//Risolve il problema specificando nel file di output solo il numero delle combinazioni cercate
	//Tento un approccio multithreaded in quando le soluzioni dei tre sottoproblemi sono distinte
	// per verificare se puo' aiutare a migliorare l'efficienza
	public static void solveOnlyNumbers(String output_file_name) throws Exception {
		
		//Creo il writer per l'output
		BufferedWriter writer = new BufferedWriter(new FileWriter(output_file_name), BUFF_SIZE_2);
		XMLOutputFactory factory = XMLOutputFactory.newFactory();
		XMLStreamWriter xml_writer = factory.createXMLStreamWriter(writer);
		
		//Intestazione doumento e root element
		xml_writer.writeStartDocument();
		xml_writer.writeStartElement(ROOT_XML_TAGNAME);
		xml_writer.writeComment("I coefficienti sono specificati nel seguente ordine: 1c, 2c, 5c, 10, 20c, 50c, 1e, 2e");
		
		for (int i = 0; i < targets.length; i++) {
			xml_writer.writeEmptyElement(SUBPROBLEM_XML_TAGNAME);
			xml_writer.writeAttribute("pound", String.valueOf(targets[i]));
			xml_writer.writeAttribute("tot", String.valueOf(numCombinations(targets[i], numbers.length)));
		}
		
		xml_writer.writeEndDocument();
		xml_writer.close();
	}
	
	//Implementazione della soluzione con l'utilizzo di due thread.
	//NB: Il codice e' strettamente applicabile unicamente al contesto del problema
	//	  proposto, il livello di scalabilita' del codice e' essenzialmente nullo
	//	  L'utilizzo del multithreading e' inoltre estremamente rudimentale, in quanto
	//	  finalizzato alla pura verifica di una possibile maggiore efficienza d'esecuzione
	
	//Abbiamo tentato inoltre di risolvere il problema B utilizzando un semplice
	//approccio concorrente ma abbiamo notato che l'overhead dovuto all'istanziazione dei Thread
	//era ben maggiore del risparmio in quanto a tempo di computazione
	public static void solveOnlyNumbersWithThreads(String output_file_name) throws Exception {
		int[] solutions = new int[3];
		
		Runnable targets_1_2 = () -> {
			solutions[0] = numCombinations(targets[0], targets.length);
			solutions[1] = numCombinations(targets[1], targets.length);
		};
		
		Runnable target_3 = () -> {
			solutions[2] = numCombinations(targets[2], targets.length);
		};
		
		Thread t1 = new Thread(targets_1_2);
		Thread t2 = new Thread(target_3);
		
		t1.start();
		t2.start();
		
		XMLStreamWriter xml_writer = XMLOutputFactory.newFactory().createXMLStreamWriter(
										 new BufferedWriter(
										     new FileWriter(output_file_name), BUFF_SIZE_2));
		
		xml_writer.writeStartDocument();
		xml_writer.writeComment("I coefficienti sono specificati nel seguente ordine: 1c, 2c, 5c, 10, 20c, 50c, 1e, 2e");
		xml_writer.writeStartElement(ROOT_XML_TAGNAME);
		
		t1.join();
		t2.join();
		
		for (int i = 0; i < solutions.length; i++) {
			xml_writer.writeEmptyElement(SUBPROBLEM_XML_TAGNAME);
			xml_writer.writeAttribute("pounds", String.valueOf(targets[i]));
			xml_writer.writeAttribute("tot", String.valueOf(solutions[i]));
		}
		
		xml_writer.writeEndDocument();
		xml_writer.flush();
	}
}
