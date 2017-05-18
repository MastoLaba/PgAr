package it.unibs.ing.ieee.recursion;

public class Struttura {
	private StringBuffer digits = new StringBuffer();
	private int occurrences;
	private StringBuffer coded  = new StringBuffer();
	
	public void appendDigit(char digit) {
		digits.append(digit);
	}
	
	public void incrementOccurrences() {
		occurrences++;
	}
	
	public void appendStar(char star) {
		coded.append(star);
	}
	
	public void print() {
		System.out.println(digits.toString());
		System.out.println(occurrences);
		System.out.println(coded.toString());
	}
	
	public void recursiveFunction(StringBuffer str, char c) {

		if(str.length() > 0) {
			char k = str.charAt(0);
			str = new StringBuffer(str.substring(1));
			if(Character.isDigit(k)) {
				appendDigit(k);
			}
			
			if(k == c) {
				incrementOccurrences();
				appendStar('*');
			}
			else
				appendStar(k);
			
			
			recursiveFunction(str, c);
		}
		
			
	}
	
	
}
