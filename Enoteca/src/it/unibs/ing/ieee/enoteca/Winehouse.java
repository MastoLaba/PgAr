package it.unibs.ing.ieee.enoteca;

import java.util.ArrayList;
import java.util.Collection;

public class Winehouse implements Interfaccia {
	ArrayList<Wine> wineList = new ArrayList<Wine>();

	@Override
	public String stampaVino() {
		StringBuffer str = new StringBuffer();
		for (Wine w : wineList) {
			str.append(w.getRecap());
		}
		return str.toString();
	}

	public ArrayList<Wine> getWineList() {
		return wineList;
	}

	public void setWineList(ArrayList<Wine> wineList) {
		this.wineList = wineList;
	}

	@Override
	public void possibileGuadagno(String valuta) throws IllegalArgumentException {

		double sum = 0;
		for (Wine w : wineList) {
			w.convertToDollars();
			sum += (w.getPrice() * w.getCount());
		}

		switch (valuta) {
		case "euro":
		case "Euro":
			sum /= Wine.CONVERSION_EURO_DOLLAR_VALUE;
			System.out.printf("%2.2f€\n", sum);
			break;
		case "dollaro":
		case "Dollaro":
			System.out.printf("%2.2f$\n", sum);
			break;
		default:
			throw new IllegalArgumentException("Inserisci una valuta valida!");
		}
	}

	@Override
	public ArrayList<Wine> stampaFasciaVini(int annoInizio, int annoFine) throws IllegalArgumentException {
		if (annoInizio > annoFine) {
			throw new IllegalArgumentException("Anno inizio > anno fine");
		}
		ArrayList<Wine> list = new ArrayList<>();
		for (Wine w : wineList) {
			if (w.getYear() >= annoInizio && w.getYear() <= annoFine) {
				list.add(w);
			}
		}
		return list;
	}

	@Override
	public long numeroViniPerNomeVino(String nome) {
		long n = 0;

		for (Wine w : wineList) {
			if (w.getName().equalsIgnoreCase(nome))
				n += (long) w.getCount();
		}

		if (n == 0)
			System.out.println("Produttore non presente o non ci sono vini per quel produttore");

		return n;
	}

	@Override
	public long numeroViniPerNomeProduttore(String nome) {
		long n = 0;

		for (Wine w : wineList) {
			if (w.getWinery().equalsIgnoreCase(nome))
				n += w.getCount();
		}

		if (n == 0)
			System.out.println("Produttore non presente o non ci sono vini per quel produttore");

		return n;
	}

}
