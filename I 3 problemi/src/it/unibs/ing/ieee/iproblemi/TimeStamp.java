package it.unibs.ing.ieee.iproblemi;

/**
 * @author RAD
 *
 */
public interface TimeStamp {
	final int MILLI = 1;
	final int SECOND = 1000;
	final int MINUTE = SECOND * 60;
	final int HOUR = MINUTE * 60;
	final int DAY = HOUR * 24;
	final double MICRO = 1e-3;
	final double NANO = 1e-6;

	/**
	 * print all delta time
	 */
	public void getDelta();

	/**
	 * Same function of getDelta(), with base to convert
	 * 
	 * @param format
	 */
	public void getDelta(int base);

	/**
	 * save current time
	 */
	public void getTime();

	/**
	 * return current time
	 * 
	 * @return
	 */
	public String showTime();

	void getDelta(double base);

}
