package deathstar.consegna2;

public enum Valuta {
	EURO,
	DOLLARI;
	
	public static Valuta getValuta(String symbol) {
		if ("$".equals(symbol))
			return DOLLARI;
		else if ("€".equals(symbol))
			return EURO;
		else
			return null;
	}
}


