package it.unibs.ing.ieee.clinicamedica;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

public class ElencoAppuntamenti {
	private ArrayList<Appuntamento> appuntamenti = new ArrayList<>();

	public void addAppuntamento(Appuntamento a) {
		appuntamenti.add(a);
	}

	public void removeAppuntamento(Appuntamento a) {
		appuntamenti.remove(a);
	}

	public void modificaAppuntamento(Appuntamento a) {

	}

	public ArrayList<Appuntamento> stampaAppuntamenti() {
		return appuntamenti;
	}

	public ArrayList<Appuntamento> stampaAppuntamentiDatoMedico(int id) {
		ArrayList<Appuntamento> ritorno = new ArrayList<>();
		for (Appuntamento a : appuntamenti) {
			if (a.getMedico().getId() == id){
				ritorno.add(a);
			}
		}		
		return ritorno;

	}

	public ArrayList<Appuntamento> stampaAppuntamentiDatoInizioFine(GregorianCalendar inizio, GregorianCalendar fine) {
		ArrayList<Appuntamento> ritorno = new ArrayList<>();
		
		for (Appuntamento a : appuntamenti) {
			if(a.getData().compareTo(fine) < 0 || a.getData().compareTo(inizio) > 0) {
				ritorno.add(a);
			}
		}
		return ritorno;
	}

	public void aggiornaNumeroAppuntamenti() {
		for(Appuntamento a : appuntamenti)
			a.getPaziente().setNumeroAppuntamenti(0);
		
		
		for (Appuntamento a : appuntamenti) {
			a.getPaziente().incrementaNumeroAppuntamenti();
		}

	}

	public ArrayList<Appuntamento> getAppuntamenti() {
		return appuntamenti;
	}

	public int numAppuntamentiDatoMedico(int id) {
		int c=0;
		for (Appuntamento appuntamento : appuntamenti) {
			if(appuntamento.getMedico().getId() == id) {
				c++;
				appuntamento.getMedico().setNumeroAppuntamenti(c);
			}
				
		}
		
		return c;
	}
	
	public int numAppuntamentiDatoPaziente(int id) {
		int c=0;
		for (Appuntamento appuntamento : appuntamenti) {
			if(appuntamento.getPaziente().getId() == id) {
				c++;
				appuntamento.getPaziente().setNumeroAppuntamenti(c);
			}
				
		}
		return c;
	}
}
