package it.unibs.ing.ieee.iproblemi;

import javax.xml.stream.XMLStreamException;

/**
 * 
 * @author Stefano Poma
 * @author Daniele Vezzoli
 * @author Matteo Zanolla
 *
 */
public class Problema2 {
	static final int coins[] = { 1, 2, 5, 10, 20, 50, 100, 200 };

	public static void esegui() {

		LogTime log = new LogTime();

		int soldi = 100;
		
		int a = 0;
		//Non sarà bello ma funziona...
		log.getTime();
		do {
			int count = 0;
			for (int d = 0; d <= soldi / coins[7]; d++) {
				for (int c = 0; c <= soldi / coins[6]; c++) {
					for (int l = 0; l <= soldi / coins[5]; l++) {
						for (int v = 0; v <= soldi / coins[4]; v++) {
							for (int x = 0; x <= soldi / coins[3]; x++) {
								for (int q = 0; q <= soldi / coins[2]; q++) {
									for (int i = 0; i <= soldi / coins[1]; i++) {
										for (int u = 0; u <= soldi / coins[0]; u++) {
											if ((d * coins[7] + c * coins[6] + l * coins[5] + v * coins[4] + x * coins[3] + q * coins[2] + i * coins[1] + u * coins[0]) == soldi)
												count++;
										}
									}
								}
							}
						}
					}
				}
			}
			System.out.println(count);
			a++;
			if(a==1)
				soldi = 200;
			if(a==2)
				soldi = 250;

		}while(a<3);
		log.getTime();
		

		try {
			XmlWriter.saveTimeStamp("problema2.xml", log.getTimestamp().get(0), log.getTimestamp().get(1));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		// log.getTime();
		// long c100 = count(coins, soldi);
		// long c200 = count(coins, 200);
		// long c250 = count(coins, 250);
		// log.getTime();
		//
		// System.out.println(c100);
		// System.out.println(c200);
		// System.out.println(c250);

	}
	
	/*****************************
	 * Programma più efficiente ma non fatto da noi
	 * 
	 *****************************/
	// public static long count(int money[], int n) {
	// long[] combinations = new long[n + 1];
	// int lunghezza = money.length;
	//
	// Arrays.fill(combinations, 0);
	//
	// combinations[0] = 1;
	//
	// for (int i = 0; i < lunghezza; i++) {
	// for (int j = money[i]; j <= n; j++) {
	// System.out.print(money[i] + " -> " + combinations[j] + "+=" +
	// (combinations[j - money[i]]));
	// combinations[j] += combinations[j - money[i]];
	// System.out.println(" " + combinations[j]);
	//
	// }
	// }
	//
	// return combinations[n];
	// }

}
