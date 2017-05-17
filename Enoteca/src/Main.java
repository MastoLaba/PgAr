import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.xml.stream.XMLStreamException;

public class Main {
	static FromXml lettore;
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		Vector<Vino> magazzino;
		System.out.println("dimmi il file del magazzino da leggere");
		String tmp = in.next();
		try {
			lettore = new FromXml(tmp);
			magazzino = lettore.getTree();
			Magazzino magaz =new Magazzino(magazzino);
			do{
				tmp="";
				System.out.println("Scegli cosa vuoi fare");
				System.out.println("0-Esci");
				System.out.println("1-Stampare la lista dei vini contenuti nel magazzino");
				System.out.println("2-Stampare la quantità di bottiglie per ogni Vino");
				System.out.println("3-Stampare la quantità di Bottiglie per ogni Produttore");
				System.out.println("4-Stampare il possibile guadagno in Multi-valuta, data come variabile esterna la valuta desiderata");
				System.out.println("5-Data una certa fascia di Annata (es. dal 1990 al 2015) stampare tutti i vini");
				tmp = in.next();
				switch (tmp){
					case "0":
						System.out.println("Arrivederci!");
						System.exit(0);
					case "1":
						magaz.listaVini();
						break;
					case "2":
						magaz.quantBottVino();
						break;
					case "3":
						magaz.quantBottProduttore();
						break;
					case "4":
						String valuta="";
						System.out.println("Inserisci la valuta desiderata(€/$)");
						valuta = in.next().trim();
						magaz.possGuadagno(valuta);
						break;
					case "5":
						magaz.annata();
						break;
					default:
						System.err.println("Inserisci un valore corretto");
						break;
				}
			}while(!tmp.equals("0"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
