package it.unibs.ing.ieee.enoteca;

public enum Currency {
	EURO, DOLLARO;

	public static Currency getCurrency(String currency) {
		switch (currency) {
		case "$":
			return DOLLARO;
		case "€":
			return EURO;
		default:
			return null;
		}
	}
}