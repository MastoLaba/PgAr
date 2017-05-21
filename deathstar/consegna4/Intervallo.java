package deathstar.consegna4;

import java.util.Comparator;

/**
 * Classe che implementa l'idea di un periodo di tempo all'interno di una giornata.
 * 
 * @author Michele Dusi
 */
public class Intervallo {

	public static final int HCOEFF = 100;
	public static final int MINUTES_PER_HOUR = 60;
	public static final int DAY_LIMIT = 2400; // L'attributo è public perchè mi serve nel main
	public static final String STRING_FORMAT = "%02d:%02d-%02d:%02d";
	
	private int ora_inizio;
	private int ora_fine;
	// Sono numeri di 4 cifre del tipo HHMM, con notazione a 24 ore.
	
	public static final Comparator<Intervallo> COMPARATOR = (int1, int2) -> {
		if (int1.getFine() < int2.getInizio()) return -1;
		else if (int1.getInizio() > int2.getFine()) return 1;
		else return 0;
	};
	
	public Intervallo(int _start, int _end) {
		this.ora_inizio = _start;
		this.ora_fine = _end;
	}
	
	public int getInizio() {
		return this.ora_inizio;
	}

	public int getFine() {
		return this.ora_fine;
	}
	
	public boolean controllaSovrapposizione(Intervallo altro_intervallo) {
		return (this.ora_fine > altro_intervallo.ora_inizio) && (altro_intervallo.ora_fine > this.ora_inizio);
	}

	public void unisci(Intervallo nuovo_intervallo) {
		this.ora_inizio = Math.min(this.ora_inizio, nuovo_intervallo.ora_inizio);
		this.ora_fine = Math.max(this.ora_fine, nuovo_intervallo.ora_fine);
	}
	
	public boolean isInvalido() {
		return (this.ora_inizio >= this.ora_fine);
	}
	
	@Override
	public String toString() {
		int min_inizio = this.ora_inizio % HCOEFF;
		int min_fine = this.ora_fine % HCOEFF;
		int h_inizio = this.ora_inizio / HCOEFF;
		int h_fine = this.ora_fine / HCOEFF;
		return String.format(STRING_FORMAT, h_inizio, min_inizio, h_fine, min_fine);
	}

	public boolean contains(Intervallo intervallo_da_controllare) {
		return (this.ora_inizio < intervallo_da_controllare.ora_fine && this.ora_fine > intervallo_da_controllare.ora_inizio);
	}

}
