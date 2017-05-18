package enoteca;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

/**
 * Classe Test per l'Enoteca.
 * @author qwertyteam
 * @version 1.0
 */

public class TestMain {
	
	public static void main(String[] args)throws FileNotFoundException, XMLStreamException {
		ParserEnoteca parser = new ParserEnoteca("enoteca.xml");
		ListaVini lista = new ListaVini(parser.getEnoteca());
		lista.ordineAlfabetico(); 
		lista.stampaVini(); //Stampa la lista dei vini contenuti nel magazzino
		lista.ordineDeposito(); //Stampa la quantità di bottiglie per ogni vino
		lista.ordineProduttore(); //Stampa la quantità di bottiglie per ogni produttore
		ListaVini listaDue = lista.compresiAnnata();
		listaDue.stampatutto(); //data una certa fascia di annata stampa tutti i vini
		lista.multiValuta();
		lista.stampaVini(); //stampa il possibile guadagno in multi-valuta
	}

}
