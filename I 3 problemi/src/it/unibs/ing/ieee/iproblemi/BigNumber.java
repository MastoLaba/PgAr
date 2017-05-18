package it.unibs.ing.ieee.iproblemi;

public class BigNumber implements Comparable<BigNumber> {
	private long base;
	private long exp;
	private double number;

	public long getBase() {
		return base;
	}

	public long getExp() {
		return exp;
	}

	public BigNumber(long base, long exp) {
		this.base = base;
		this.exp = exp;
	}

	@Override
	public String toString() {
		return String.format("%d ^ %d -> %f", base, exp, number);
	}

	public void generateNumber() {
		number = (exp / Math.pow(10, 5)) * log100(base);

	}

	private double log100(long n) {
		return Math.log10(n) / Math.log10(100);

	}

	@Override
	public int compareTo(BigNumber bn) {
		return Double.compare(this.number, bn.getNumber());
	}

	public double getNumber() {
		return number;
	}

}
