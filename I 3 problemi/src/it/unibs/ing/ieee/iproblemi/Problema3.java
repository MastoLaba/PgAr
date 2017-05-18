package it.unibs.ing.ieee.iproblemi;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.stream.XMLStreamException;


/**
 * 
 * @author Stefano Poma
 * @author Daniele Vezzoli
 * @author Matteo Zanolla
 *
 */
public class Problema3 {
	public static void esegui() {

		XmlParser xp = new XmlParser();
		ArrayList<BigNumber> lista = new ArrayList<>();
		LogTime log = new LogTime();

		try {
			lista = xp.parseXml("inProbC.xml");
		} catch (FileNotFoundException | XMLStreamException e) {
			System.err.println("File non presente");
		}

		log.getTime();

		for (BigNumber b : lista) {
			b.generateNumber();
		}

		Collections.sort(lista);

		log.getTime();

		int i = 1;
		for (BigNumber bigNumber : lista) {
			System.out.println(i + ":\t" + bigNumber);
			i++;
		}

		System.err.println("Il numero più piccolo è: " + lista.get(0).getBase() + "^" + lista.get(0).getExp());
		System.err.println("Il numero più grande è: " + lista.get(lista.size() - 1).getBase() + "^"
				+ lista.get(lista.size() - 1).getExp());
		//Stampiamo solo il numero centrale dell'array.
		System.err.println("La mediana è : " + lista.get(499).getBase() + "^" + lista.get(499).getExp());

		System.err.println("\n\n");

		log.getDelta(TimeStamp.MILLI);
		log.getDelta(TimeStamp.NANO);

		try {
			XmlWriter.saveTimeStamp("problema3.xml", log.getTimestamp().get(0), log.getTimestamp().get(1));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

	}

}
