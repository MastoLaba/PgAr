package dijkstra_kruskal.myUtil.myStatic;

import java.util.*;
import myClass.*;
import java.io.*;

public class InputClass {
	
	private static final String SPACE=" ";
	private static final String ERRORE_DI_FORMATO="Hai inserito delle parole al posto dei numeri";
	
			
	/**
	 * controlla se il file nell'indirizzo pathname 
	 * esista o meno
	 * @param pathname indirizzo del file
	 * @return se il file esiste
	 * @author mauro
	 */
	public static boolean exists(String pathname){
		boolean condition=false;
		File file = new File(pathname);
		if (file.exists()) condition=true;

		return condition;
	}
	
	/**
	 * legge un qualsiasi file di testo è lo torna in
	 * un vector di stringhe
	 * @return la stringa con tutto ciò che è stato letto
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @author mauro
	 */
	public static Vector<String> readStreamText(String pathname) 
									throws IOException, FileNotFoundException  {
		
		Vector <String> reader = new Vector();
		
		String string;
		
		//creo lo stream per il testo
		File file = new File (pathname);
		BufferedReader source = new BufferedReader ( new FileReader ( file ));	
		
		//passo tutto in un Vector string
		do{
			string = source.readLine();
			if(! (string == null))
				reader.add(string);
		//finisce quando la riga è uguale a null ovvero è finito il file
		}while (! (string == null));
		
		//STRA IMPORTANTE DA CHIUDERE SEMPRE
		source.close();
		return reader;
	}

	
	/**
	 * legge il primo carattere immesso
	 * @param messaggio
	 * @return char 
	 * @author mauro
	 */
	public static char readChar(String messaggio){
		System.out.print(messaggio + SPACE);
		Scanner lettore = new Scanner(System.in);
		char carattere;
		String stringa=lettore.next();
		carattere=stringa.charAt(0);
		return carattere;
	}
	
	/**
	 * legge la prima stringa fino all'invio
	 * @param messaggio
	 * @return la stringa letta
	 * @author mauro
	 */
	public static String readString(String messaggio){
		System.out.print(messaggio + SPACE);
		Scanner lettore = new Scanner(System.in);
		String stringa;
		do{
			stringa=lettore.nextLine();
		}while (stringa.isEmpty());
		return stringa;
	}
	
	/**
	 * legge un intero
	 * @param messaggio
	 * @return il numero intero letto
	 * @author mauro
	 */
	public static int readInt(String messaggio){
		int numero = 0;
		Scanner lettore = new Scanner(System.in);
		boolean finito=false;
		do{
			System.out.print(messaggio + SPACE);
			try{
				numero=lettore.nextInt();
				finito=true;
			}
			catch(InputMismatchException e){
				String scarto = lettore.next();
				System.out.println(ERRORE_DI_FORMATO);
			}
		}while(!finito);
		return numero;
	}
	
	/**
	 * legge un double
	 * @param messaggio
	 * @return il double letto
	 * @author mauro
	 */
	public static double readDouble(String messaggio){
		double numero = 0;
		Scanner lettore = new Scanner(System.in);
		boolean finito=false;
		do{
			System.out.print(messaggio + SPACE);
			try{
				numero=lettore.nextDouble();
				finito=true;
			}
			catch(InputMismatchException e){
				String scarto = lettore.next();
			}
		}while(!finito);
		return numero;
	}
	
	/**
	 * controlla se un anno è bisestile o meno
	 * @param anno
	 * @return se l'anno sia bisestile o meno
	 * @author mauro
	 */
	public static boolean controllYear(int anno){
		boolean condition=false;
		if(anno%400==0) condition=true;
		else if (anno%100==0) condition=false;
		else if (anno%4==0) condition=true;
		
		return condition;
	}

	/**
	 * controlla se una data è valida
	 * @param anno
	 * @param mese
	 * @param giorno
	 * @return se la data sia valida o meno
	 * @author mauro
	 */
	public static boolean controllMonth(int anno, int mese, int giorno){
		boolean condition=false;
		if (mese==1 || mese==3 || mese==5 || mese==7 || mese==8 || mese==10 || mese==12)
			if (giorno>0 && giorno<32) condition=true;
		if (mese==4 || mese==6 || mese==9 || mese==11)
			if (giorno>0 && giorno<31) condition=true;
		if (mese==2){
			if(controllYear(anno) && giorno>0 && giorno<30) condition=true;
			else if(giorno>0 && giorno<29) condition=true;
		}
					
		return condition;
	}

	/**
	 * legge una data
	 * @return ritorna la data
	 * @author mauro
	 */
	public static GregorianCalendar readDate(){
		Scanner lettore = new Scanner(System.in);
		int anno, mese, giorno;
		System.out.println("Inserire l'anno ");
		anno = lettore.nextInt();
		do{
			System.out.println("Inserire il mese ");
			mese = lettore.nextInt();
		}while (mese<0 || mese>12);
		do{
			System.out.println("Inserire il giorno ");
			giorno = lettore.nextInt();
		}while(!controllMonth(anno, mese, giorno));
		
		GregorianCalendar data = new GregorianCalendar(anno, mese, giorno);
		
		return data;
	}
	
	/**
	 * legge un indirizzo con nazione, provincia, città, via
	 * @return l'indirizzo
	 * @author mauro
	 */
	public static dijkstra_kruskal.myUtil.myClass.Address readAddress(){
		dijkstra_kruskal.myUtil.myClass.Address indirizzo = new dijkstra_kruskal.myUtil.myClass.Address();
		Scanner lettore = new Scanner(System.in);
		String stringa;
		System.out.println("inserire la nazione ");
		stringa=lettore.next();
		indirizzo.setNazione(stringa);
		System.out.println("inserire la provincia ");
		stringa=lettore.next();
		indirizzo.setProvincia(stringa);
		System.out.println("inserire la città ");
		stringa=lettore.next();
		indirizzo.setCittà(stringa);
		System.out.println("inserire l'indirizzo ");
		stringa=lettore.next();
		indirizzo.setIndirizzo(stringa);
		
		return indirizzo;
	}
	
	/**
	 * Imposta i dati di una persona preesistente
	 * @param persona
	 * @author mauro
	 */
	public static void readPerson (Person persona) {
		Scanner lettore = new Scanner(System.in);
		String stringa="AAA";
		System.out.print("Inserire il nome ");
		stringa=lettore.next();
		
		
		persona.setNome(stringa);
		System.out.print("Inserire il cognome ");
		stringa=lettore.next();
		persona.setCognome(stringa);
		
		System.out.println("Inserire il sesso ");
		do{
			stringa=lettore.next();
			persona.setSesso(stringa);
		}while (!persona.getSesso().equals("maschio") && !persona.getSesso().equals("femmina"));
		
		persona.setDataDiNascita(readDate());
		
	}
	
	/**
	 * Imposta i dati di una persona preesistente più l'indirizzo
	 * @param persona
	 * @author mauro
	 */
	public static void readPersonComplete (Person persona){
		
		readPerson(persona);
		
		persona.setAddress(readAddress());
	}

}
