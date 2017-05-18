/**
 * 
 */
package it.unibs.ing.ieee.enoteca;

/**
 * @author Daniele Vezzoli, Stefano Poma, Matteo Zanolla
 *
 */
public class Wine {
	private String name;
	private double price;
	private Currency currency; // dollaro, euro
	private int year;
	private String place;
	private String winery;

	private int count;

	public static final double CONVERSION_EURO_DOLLAR_VALUE = 1.093575;

	public Wine(String name, double price, Currency currency, int year, String place, String winery, int count) {
		this.name = name;
		this.price = price;
		this.currency = currency;
		this.year = year;
		this.place = place;
		this.winery = winery;
		this.count = count;
	}

	public Wine() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCurrency(Currency currency2) {
		this.currency = currency2;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setWinery(String winery) {
		this.winery = winery;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getCurrency() {
		return currency.name();
	}

	public int getYear() {
		return year;
	}

	public String getPlace() {
		return place;
	}

	public String getWinery() {
		return winery;
	}

	public int getCount() {
		return count;
	}

	public String getRecap() {
		return String.format("%s - %s - %s - %s : %d\n", name, place, winery, currency, year);
	}

	public void convertToDollars() {
		if (currency.equals(Currency.EURO)) {
			price *= CONVERSION_EURO_DOLLAR_VALUE;
			currency = Currency.DOLLARO;
		}
	}
}
