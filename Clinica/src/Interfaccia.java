import java.util.Date;
import java.util.Vector;

public interface Interfaccia {

	public String stampaMedici(); //toString di tutti i medici visualizzando ID univoco, Cognome, Nome, Anno Laurea, Anno Assunzione

	public void stampaOrari(int matricola); //Stampa gli Orari del Medico inserito in base all'ID, o al Nome, o all'anno di Laurea 
	//(casi di omonimia, stampa tutti i risultati) 

	public Vector<Appuntamenti> stampaAppuntamenti(int matricola); //Stampa tutti gli appuntamenti del Medico Inserito

	public String stampaPazienti(); //Stampa tutti i Pazienti inseriti nel Programma
	//TODO
	public void stampaAppuntamenti(Date dataInizio, Date dataFine); //Stampa gli appuntamenti su un certo intervallo, qui prima E era un int

	public String stampaNumAppuntamenti(int matricola); //Stampa il numero di appuntamenti per un certo Medico

	//Ho inserito le funzionalità  di base, così potete sbizzarrirvi nel creare gli altri Metodi
	//TODO
	public void getTime(); //Recupera la data attuale (almeno i millisecondi).
	//Dopo la prima esecuzione, stampa in Debug il delay time di chiamata in chiamata, partendo dalla prima volta.

}
