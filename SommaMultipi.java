package problemi;

import java.util.*;

public class SommaMultipi {

	public static void main(String args[])
	{
		LogTime logtime = new LogTime();
		logtime.getTime();
		int num1= 5;
		final int num2=3;
		final int limite1=10;
		final int limite2=100;
		final int limite3=1000;
		logtime.getTime();
		calcolaSomma(limite1,num1,num2);
		logtime.getTime();
		logtime.getDelta();
		logtime.getTime();
		calcolaSomma(limite2,num1,num2);
		logtime.getTime();
		logtime.getDelta();
		logtime.getTime();
		calcolaSomma(limite3,num1,num2);
		logtime.getTime();
		logtime.getDelta();
	}
	/**
	 * questo metodo calcola se multipo � effettivamente un multiplo di num
	 * @param multiplo
	 * @param num
	 * @return
	 */
	public static int isMultipleOf(int multiplo,int num)
	{
		int siMultiplo=1;
		int noMultiplo=0;
		if(multiplo==0) return siMultiplo;
		else{
				if(multiplo%num==0)
				{
					return siMultiplo;
				}
				else return noMultiplo;
		}
	}
	/**
	 * questo metodo calco la somma dei multipli di due dati interi sotto un limite dato
	 * @param limite
	 * @param num1
	 * @param num2
	 */
	public static void calcolaSomma(int limite,int num1,int num2)
	{
		int somma=0;
		for(int i=0;i<(limite);i++)
		{
			
			if(isMultipleOf(i,num1)==1)
			{
				somma+=i;	
			}
			if(isMultipleOf(i,num2)==1)
			{
				somma+=i;
			}	
		}

		System.out.println("la somma dei multipli di "+num1+" o di "+num2+" minori di "+limite+" � "+somma);
	}
	
	
	
	
	/*	Se elenchiamo tutti i numeri naturali sotto i 10 che sono multipli di 3 o 5, otteniamo 3, 5, 6 e 9. La somma di questi multipli � 23.
		Trova la somma di tutti i multipli di 3 o 5 sotto i 1000 */
}
