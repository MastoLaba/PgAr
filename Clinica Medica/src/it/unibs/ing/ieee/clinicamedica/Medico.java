package it.unibs.ing.ieee.clinicamedica;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Medico extends Persona{


	private GregorianCalendar annoLaurea;
	private GregorianCalendar annoAssunzione;
	private GiornoLavorativo[] giorniLavorativi = new GiornoLavorativo[7];
	private int numeroAppuntamenti;
	
	public Medico(String _nome, String _cognome, GregorianCalendar _annoLaurea, GregorianCalendar _annoAssunzione) {
		super(_nome, _cognome);
		annoLaurea = _annoLaurea;
		annoAssunzione = _annoAssunzione;
		
		this.setId(this.hashCode());
	}
	
//	public String stampaOrari(){
//		
//	}

	public String toString(){
		return String.format("nome: %s\ncognome: %s\nid: %d\nanno di laurea: %s\nanno di assunzione: %s\n",this.getNome(),this.getCognome(),this.getId(),annoLaurea.get(Calendar.YEAR),annoAssunzione.get(Calendar.YEAR));
	}

	public GiornoLavorativo[] getGiorniLavorativi() {
		return giorniLavorativi;
	}

	public void setGiorniLavorativi(GiornoLavorativo[] giorniLavorativi) {
		this.giorniLavorativi = giorniLavorativi;
	}

	public GregorianCalendar getAnnoLaurea() {
		return annoLaurea;
	}
	

	public int getNumeroAppuntamenti() {
		return numeroAppuntamenti;
	}

	public void setNumeroAppuntamenti(int numeroAppuntamenti) {
		this.numeroAppuntamenti = numeroAppuntamenti;
	}
	
}
