package deathstar.consegna4;

import java.util.Collection;

/*La seguente classe interfaccia è utilizzabile solamente dopo aver cambiato con S, T, ed E la tipologia di dato desiderato*/



public interface Interfaccia<S, T, E> {

	public String stampaMedici(); //toString di tutti i medici visualizzando ID univoco, Cognome, Nome, Anno Laurea, Anno Assunzione

	public Collection<?> stampaOrari(S check); //Stampa gli Orari del Medico inserito in base all'ID, o al Nome, o all'anno di Laurea (casi di omonimia, stampa tutti i risultati) 

	public Collection<?> stampaAppuntamenti(T matricola); //Stampa tutti gli appuntamenti del Medico Inserito

	public String stampaPazienti(); //Stampa tutti i Pazienti inseriti nel Programma

	public Collection<?> stampaAppuntamenti(E dataInizio, E dataFine); //Stampa gli appuntamenti su un certo intervallo

	public String stampaNumAppuntamenti(int matricola); //Stampa il numero di appuntamenti per un certo Medico

	//Ho inserito le funzionalità di base, così potete sbizzarrirvi nel creare gli altri Metodi

	public void getTime(); //Recupera la data attuale (almeno i millisecondi). Dopo la prima esecuzione, stampa in Debug il delay time di chiamata in chiamata, partendo dalla prima volta.

}
