package deathstar.consegna3;

import java.io.FileWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/** Main class per il lancio del programma per la risoluzione dei problemi
 *  assegnati. Si occupa inoltre della registrazione dei tempi di esecuzione
 * 
 * @author Lorenzo Nodari
 * @author Michele Dusi
 *
 */
public class ProblemsMain {
	
	private static final String INFO = "Il seguente programma e' stato scritto e sviluppato con il solo scopo di risultare efficiente nella risoluzione "
									  + "dei problemi proposti, date per scontate le condizioni iniziali specificate al tempo della consegna.\n"
									  + "Il codice risulta pertanto scritto in quest'ottica, non presenta un buon esempio di programmazione ad oggetti, "
									  + "ci teniamo a specificare che questo non e' il nostro usuale stile di programmazione. E' stata dura scrivere il "
									  + "codice cosi, ci piangeva il cuore nel farlo, ma e' stato necessario\n -Lorenzo & Michele\n";

	public static void main(String[] args) {
		System.out.println("DISCLAIMER:");
		System.out.println(INFO);
		
		System.out.println("Inizio computazione");
		long time_a = 0L;
		long time_b = 0L;
		long time_c = 0L;
		long time_tmp = 0L;
		
		try {
			System.out.println("Problema C...");
			time_tmp = System.nanoTime();
			ProblemC.solve("output_c.xml");
			time_c = System.nanoTime() - time_tmp;
			
			System.out.println("Problema B...");
			time_tmp = System.nanoTime();
			ProblemB.solveWithCombinations("output_b.xml");
			time_b = System.nanoTime() - time_tmp;
			
			System.out.println("Problema A...");
			time_tmp = System.nanoTime();
			ProblemA.solve("output_a.xml");
			time_a = System.nanoTime() - time_tmp;
			System.out.println("Done");
			System.out.println();
		}
		catch (Exception e) {
			System.out.println("ERRORE: Non sono riuscito a risolvere i problemi -> ESCO");
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("Time A: " + String.valueOf(time_a / 1e9) + "s");
		System.out.println("Time B: " + String.valueOf(time_b / 1e9) + "s");
		System.out.println("Time C: " + String.valueOf(time_c / 1e9) + "s");
		
		System.out.println();
		System.out.println("Scrivo i risultati prestazionali in un file xml...");
		try {
			FileWriter file_writer = new FileWriter("times.xml");
			XMLStreamWriter writer = XMLOutputFactory.newFactory().createXMLStreamWriter(file_writer);
			
			writer.writeStartDocument();
			writer.writeStartElement("times");
			
			writer.writeStartElement("time");
			writer.writeAttribute("problema", "a");
			writer.writeCharacters(String.valueOf(time_a / 1e9) + "s");
			writer.writeEndElement();
			
			writer.writeStartElement("time");
			writer.writeAttribute("problema", "b");
			writer.writeCharacters(String.valueOf(time_b / 1e9) + "s");
			writer.writeEndElement();
			
			writer.writeStartElement("time");
			writer.writeAttribute("problema", "c");
			writer.writeCharacters(String.valueOf(time_c / 1e9) + "s");
			writer.writeEndElement();
			
			writer.writeEndDocument();
			writer.close();
			
			System.out.println("Done");
		}
		catch(Exception e) {
			System.out.println("ERRORE: Non sono riuscito a scrivere il file con i tempi di esecuzione");
		}
	}

}
