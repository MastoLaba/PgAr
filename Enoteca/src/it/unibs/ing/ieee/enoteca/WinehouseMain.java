package it.unibs.ing.ieee.enoteca;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class WinehouseMain {

	@SuppressWarnings("finally")
	public static void main(String[] args) {
		XmlParser xp = new XmlParser();
		Winehouse listWines = new Winehouse();

		String[] voci = { "Stampa i vini", "Numero prodotti per produttore", "Numero vini per nome",
				"Guadagno possibile", "Elenco vini per fascia annuale" };
		boolean esci = false;

		try {
			listWines = xp.parseXml("enoteca.xml");
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

		// System.out.println(listWines.stampaVino());

		MyMenu m = new MyMenu("Enoteca", voci);
		while (!esci) {
			switch (m.scegli()) {
			case 1:
				System.out.println(listWines.stampaVino());
				break;
			case 2:
				System.out.println(listWines.numeroViniPerNomeProduttore(
						InputDati.leggiStringaNonVuota("Inserisci il nome di un produttore: ")));
				break;
			case 3:
				System.out.println(listWines
						.numeroViniPerNomeVino(InputDati.leggiStringaNonVuota("Inserisci il nome di un produttore: ")));
				break;
			case 4:
				try {
					listWines.possibileGuadagno(
							InputDati.leggiStringaNonVuota("Inserisci una valuta [dollaro, euro]: "));
				} catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
				} finally {
					break;
				}

			case 5:
				ArrayList<Wine> w = null;
				try {
					w = listWines.stampaFasciaVini(InputDati.leggiInteroNonNegativo("Inserisci il minimo: "),
							InputDati.leggiInteroNonNegativo("Inserisci il massimo: "));
				} catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
					System.out.println("\n");
					break;
				}

				for (Wine u : w)
					System.out.println(u.getRecap());

				break;
			case 0:
				esci = true;
				break;
			default:
				assert (m.scegli() > 5 || m.scegli() < 0);
			}
		}

	}

}
