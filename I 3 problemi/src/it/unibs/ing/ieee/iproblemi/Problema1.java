package it.unibs.ing.ieee.iproblemi;

import javax.xml.stream.XMLStreamException;


/**
 * 
 * @author Daniele Vezzoli
 *
 */
public class Problema1 {
	
	
	public static void esegui() {
		LogTime log = new LogTime();

		long below = 10;

		log.getTime();
		
		long n5 = (long) Math.ceil(below / 5);
		long n3 = (long) Math.ceil(below / 3);

		long sum = ((n3 * 3 * (1 + n3)) + (n5 * 5 * (1 + n5))) / 2;

		
		below = 100;

		
		n5 = (long) Math.ceil(below / 5);
		n3 = (long) Math.ceil(below / 3);

		sum = ((n3 * 3 * (1 + n3)) + (n5 * 5 * (1 + n5))) / 2;

		
		below = 1000;

		
		n5 = (long) Math.ceil(below / 5);
		n3 = (long) Math.ceil(below / 3);

		sum = ((n3 * 3 * (1 + n3)) + (n5 * 5 * (1 + n5))) / 2;

		log.getTime();


		System.out.println(sum);

		try {
			XmlWriter.saveTimeStamp("problema1.xml", log.getTimestamp().get(0), log.getTimestamp().get(1));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

	}

}
