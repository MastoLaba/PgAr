import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

import javax.xml.stream.XMLStreamException;

/**
 * Classe di Test che, dato in input un file XML contenente una sequenza di numeri in
 * formato base/esponente, trova il numero più piccolo, più grande e la mediana tra 
 * tutti quelli dell'elenco. Il risultato verrà fornito sotto forma di documento XML.
 * @author qwertyteam
 * @version 1.0
 */

public class HighNumbersTest {
	
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException{
		XMLReader reader = new XMLReader("problem_c.xml");
		LogTime logTime = new LogTime();
		logTime.getTime();
		LinkedHashMap<Integer, Double> reducedNumbers = Util.reduceValues(reader.getBases(), reader.getExponents());
		reducedNumbers = Util.sortByComparator(reducedNumbers);
		int [] lowestValues = new int[]{reader.getBases().get(Util.getKeyOfLowestValue(reducedNumbers)),
				reader.getExponents().get(Util.getKeyOfLowestValue(reducedNumbers))};
		int [] greatestValues = new int[]{reader.getBases().get(Util.getKeyOfGreatestValue(reducedNumbers)),
				reader.getExponents().get(Util.getKeyOfGreatestValue(reducedNumbers))};
		
		int [] mediana;
		
		/*
		 * Se il totale dei numeri dell'elenco è pari allora la mediana è formata da un unico
		 * numero di cui si va a prendere il valore di base ed esponente dalla LinkedHashMap
		 * originale attraverso le chiavi fornite dal metodo della classe Util. 
		 */
		
		if(Util.getKeysOfMediana(reducedNumbers).length == 1) 
			mediana = new int[]{
					reader.getBases().get(Util.getKeysOfMediana(reducedNumbers)[0]),
					reader.getExponents().get(Util.getKeysOfMediana(reducedNumbers)[0])};
		else{
			mediana = new int[]{reader.getBases().get(Util.getKeysOfMediana(reducedNumbers)[0]),
					reader.getExponents().get(Util.getKeysOfMediana(reducedNumbers)[0]),
					reader.getBases().get(Util.getKeysOfMediana(reducedNumbers)[1]),
					reader.getExponents().get(Util.getKeysOfMediana(reducedNumbers)[1])};
		}
		logTime.getTime();
		logTime.getDelta(LogTime.MILLI);
		XMLWriter.executeWrite("output_c.xml", lowestValues, greatestValues, mediana);
	}
	
}
