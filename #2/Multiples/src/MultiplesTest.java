/**
 * Classe che permette di calcolare la somma di tutti i numeri che sono multipli di 3 o 
 * di 5 sotto 10, sotto 100, sotto 1000.
 * @author qwertyteam
 * @version 1.0
 */

public class MultiplesTest {

	public static void main(String [] args){
		LogTime logtime=new LogTime();
		System.out.println("La somma di tutti i multipli di 3 o 5 sotto 10 è: ");
		logtime.getTime();
		System.out.println(calculateSum(3, 5, 10));
		logtime.getTime();
		logtime.getDelta(LogTime.MILLI);
		System.out.println("La somma di tutti i multipli di 3 o 5 sotto 100 è: ");
		logtime.getTime();
		System.out.println(calculateSum(3, 5, 100));
		logtime.getTime();
		logtime.getDelta(LogTime.MILLI);
		System.out.println("La somma di tutti i multipli di 3 o 5 sotto 1000 è: ");
		logtime.getTime();
		System.out.println(calculateSum(3, 5, 1000));
		logtime.getTime();
		logtime.getDelta(LogTime.MILLI);
	}
	
	/**
	 * Se si considera la somma dei numeri che sono multipli di due numeri è necessario 
	 * prevedere anche il conto dei numeri che vengono contati due volte: viene quindi trovato 
	 * l'LCM (Least Common Multiple) che permette di sottrarre la somma dei numeri che costituiscono 
	 * l'insieme intersezione tra i due multipli. 
	 * @param firstNumber Primo multiplo 
	 * @param secondNumber Secondo multiplo
	 * @param threshold Valore limite su cui ci si ferma ad analizzare i numeri 
	 * @return Somma totale degli N numeri che sono sia multipli del primo che del secondo
	 */
	
	public static int calculateSum(int firstNumber, int secondNumber, int threshold){
		int firstSum = gaussAlgorithm(firstNumber, threshold);
		int secondSum = gaussAlgorithm(secondNumber, threshold);
		int intersectionSum = firstSum + secondSum 
				- gaussAlgorithm(leastCommonMultiple(firstNumber, secondNumber), threshold);
		return intersectionSum;
	}
	
	/**
	 * Metodo che implementa la somma dei primi N numeri naturali (di Gauss), applicata
	 * al problema. Per trovare la somma dei primi N numeri naturali che sono multipli di M
	 * bisognerà moltiplicare la serie numerica per M.
	 * @param multiple Multiplo da considerare
	 * @param threshold Valore limite N su cui ci si ferma ad analizzare i numeri
	 * @return
	 */
	
	public static int gaussAlgorithm(int multiple, int threshold){
		int numberOfTerms = (threshold-1) / multiple;
		return multiple*((numberOfTerms * (numberOfTerms + 1))/2);
	}
	
	/**
	 * Metodo che permette di calcolare l'LCM (Least Common Multiple = il più piccolo 
	 * multiplo tra due numeri). È basato sulla divisione tra il prodotto dei due numeri
	 * e il GCD (Greatest Common Divisor).
	 * 
	 * @param firstNumber Primo numero
	 * @param secondNumber Secondo numero
	 * @return LCM tra i due numeri
	 */
	
	public static int leastCommonMultiple(int firstNumber, int secondNumber){
		return Math.abs(firstNumber * secondNumber)/greatestCommonDivisor(firstNumber, secondNumber);
	}
	
	/**
	 * Metodo che permette di calcolare il GCD (Greatest Common Divisor = il più grande
	 * divisore in comune tra due numeri). È l'implementazione dell'algoritmo di Euclide.
	 * @param firstNumber Primo numero
	 * @param secondNumber Secondo numero
	 * @return GCD tra i due numeri
	 */
	
	public static int greatestCommonDivisor(int firstNumber, int secondNumber){
		int tmp = 0;
		while(secondNumber > 0){
			tmp = firstNumber % secondNumber;
			firstNumber = secondNumber;
			secondNumber = tmp;
		}
		return firstNumber;
	}
}
