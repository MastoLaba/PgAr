package dijkstra_kruskal.myUtil.myClass;

import java.util.*;
import myStatic.InputClass;

public class Person {

	private String nome = "nome";
	private String cognome = "cognome";
	private GregorianCalendar dataDiNascita;
	private String sesso = "maschio";
	private Address indirizzo;

	/**
	 * il costruttore vuoto
	 */
	public Person() {
	}

	/**
	 * ritorna il nome della persona
	 * 
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * si imposta il nome della persona
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * ritorna il cognome della persona
	 * 
	 * @return
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * si imposta il cognome della persona
	 * 
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * ritorna la data di nascita della persona
	 * 
	 * @return
	 */
	public GregorianCalendar getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * si imposta la data di nascita della persona
	 * 
	 * @param dataDiNascita
	 */
	public void setDataDiNascita(GregorianCalendar dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	/**
	 * ritorna il sesso della persona
	 * 
	 * @return
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * si imposta il sesso della persona
	 * 
	 * @param sesso
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * ritorna l'indirizzo della persona
	 * 
	 * @return
	 */
	public Address getAddress() {
		return indirizzo;
	}

	/**
	 * si imposta l'indirizzo
	 * 
	 * @param indirizzo
	 */
	public void setAddress(Address indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * si imposta l'indirizzo della persona attraverso il metodo
	 * InputClass.readAddress()
	 */
	public void setAddress() {
		indirizzo = dijkstra_kruskal.myUtil.myStatic.InputClass.readAddress();
	}

	/**
	 * calcola l'età della persona
	 * 
	 * @return
	 */
	public int getEtà() {
		GregorianCalendar oggi = new GregorianCalendar();
		int età = -1;

		if (oggi.get(Calendar.MONTH) == dataDiNascita.get(Calendar.MONTH))
			if (oggi.get(Calendar.DAY_OF_MONTH) < dataDiNascita.get(Calendar.DAY_OF_MONTH))
				età = oggi.get(Calendar.YEAR) - dataDiNascita.get(Calendar.YEAR) - 1;

		if (oggi.get(Calendar.MONTH) < dataDiNascita.get(Calendar.MONTH)) {
			età = oggi.get(Calendar.YEAR) - dataDiNascita.get(Calendar.YEAR) - 1;

		}

		else
			età = oggi.get(Calendar.YEAR) - dataDiNascita.get(Calendar.YEAR);

		return età;
	}

}
