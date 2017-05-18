package problem1;

public class stampa {
	
	public static void main(String[] args) {
		
		Risultato();
		
		//TODO implementare timestamp
		
		if(args.length<=0){
			System.err.println("Set limit to count");
			return ;
		}
		int limit=(int) 1e8;
		LogTime logtime=new LogTime();
		logtime.getTime();
		for(int i=0; i<limit; i++);
		logtime.getTime();
		
		for(int i=0; i<limit; i++){
			if (i==1e2)System.out.println("1e2\t"+logtime.showTime());
			if (i==1e4)System.out.println("1e4\t"+logtime.showTime());
			if (i==1e6)System.out.println("1e6\t"+logtime.showTime());
		}
		logtime.getTime();
		for(int i=0; i<limit; i++){
			if (i==1e2)System.err.println("1e2\t"+logtime.showTime());
			if (i==1e4)System.err.println("1e4\t"+logtime.showTime());
			if (i==1e6)System.err.println("1e6\t"+logtime.showTime());
		}
		logtime.getTime();
		for(int i=0; i<limit; i++){
			System.err.println(i);
			if (i==1e2)System.err.println("1e2\t"+logtime.showTime());
			if (i==1e4)System.err.println("1e4\t"+logtime.showTime());
			if (i==1e6)System.err.println("1e6\t"+logtime.showTime());
		}
		logtime.getTime();
		for(int i=0; i<limit; i++){
			System.out.println(i);
			if (i==1e2)System.err.println("1e2\t"+logtime.showTime());
			if (i==1e4)System.err.println("1e4\t"+logtime.showTime());
			if (i==1e6)System.err.println("1e6\t"+logtime.showTime());
		}
		logtime.getTime();
		logtime.getDelta(LogTime.MILLI);
		logtime.getDelta(LogTime.SECOND);
		logtime.getDelta(LogTime.MINUTE);
		
	}
	//somma dei multipli di 3 e 5 minori di 1000,100,10
	public static void Risultato() {
		
	int primoMultiplo = 3;
	int secondoMultiplo = 5;
	int mcm = primoMultiplo*secondoMultiplo;
	int risultato = 0;
	int risultato1 = 0;
	int risultato2 = 0;
	int ripetizione = 0;
	int ripetizione1 = 0;
	
	{ for (int i = 1; i<=333; i++){ // 333 è il numero di volte che i multipli di 3 sono presenti sotto 1000
		if (i<=199) // 199 è il numero di volte che i multipli di 5 sono presenti sotto 1000
				risultato += secondoMultiplo*i + primoMultiplo*i ;
		else
				risultato += primoMultiplo*i ;
			
		}
	for (int i = 1; i<67; i++){
		ripetizione += mcm*i;
			}
	int soluzione = risultato - ripetizione;
		System.out.println("somma multipli di 3,5 minori di 1000: " + soluzione);
		}
	{
			for (int i = 1; i<=33; i++){ 
				if (i<=19)
						risultato1 += secondoMultiplo*i + primoMultiplo*i ;
				else
						risultato1 += primoMultiplo*i ;
					
				}
			for (int i = 1; i<7; i++){
				ripetizione1 += mcm*i;
					}
			int soluzione = risultato1 - ripetizione1;
				System.out.println("somma multipli di 3,5 minori di 100: " + soluzione);
		}
	{
		for (int i = 1; i<=3; i++){ 
			if (i<=1)
					risultato2 += secondoMultiplo*i + primoMultiplo*i ;
			else
					risultato2 += primoMultiplo*i ;
			}
			System.out.println("somma multipli di 3,5 minori di 10: " + risultato2);
		}
	}	
}

