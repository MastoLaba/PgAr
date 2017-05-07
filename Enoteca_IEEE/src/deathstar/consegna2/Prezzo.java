package deathstar.consegna2;

public class Prezzo {
	
	private static final double TASSO_EURO_DOLLARO = 1.09365;
	private static final double TASSO_DOLLARO_EURO = 0.91436;

	private double valore;
	private Valuta valuta;
	
	public Prezzo(double valore, Valuta valuta) {
		this.valore = valore;
		this.valuta = valuta;
	}

	public double getValore() {
		return valore;
	}

	public Valuta getValuta() {
		return valuta;
	}
	
	public Prezzo toDollari() {
		if (this.valuta == Valuta.EURO) {
			return new Prezzo(this.valore * TASSO_EURO_DOLLARO, Valuta.DOLLARI);
		}
		else {
			return this;
		}
	}
	
	public Prezzo toEuro() {
		if (this.valuta == Valuta.DOLLARI) {
			return new Prezzo(this.valore * TASSO_DOLLARO_EURO, Valuta.EURO);
		}
		else {
			return this;
		}
	}
	
	public boolean isEuro() {
		return this.valuta == Valuta.EURO;
	}
	
	public boolean isDollari() {
		return this.valuta == Valuta.DOLLARI;
	}
	
	public String toString() {
		return (isEuro() ? "€" : "$") + " " + valore;
	}
}
