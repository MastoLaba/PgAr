package deathstar.consegna4;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Classe per la gestione della tabella di orari di un Medico.
 * 
 * @author Michele Dusi
 */
public class OrarioLavorativo {
	
	// Stringhe
	private static final String WHITESPACE = "  ";
	private static final String DAY_FORMAT = "\n%-13s";
	public static final String [] GIORNI_SETTIMANA = {
			"Lunedì",
			"Martedì",
			"Mercoledì",
			"Giovedì",
			"Venerdì",
			"Sabato",
			"Domenica"};
	// Lista di giorni lavorativi
	private List<List<Intervallo>> giorni;
	
	/**
	 * Costruttore vuoto.
	 * Inizializza gli orari vuoti.
	 */
	public OrarioLavorativo() {
		this.giorni = new ArrayList<List<Intervallo>>(GIORNI_SETTIMANA.length);
		for (int i = 0; i < GIORNI_SETTIMANA.length; i++) {
			giorni.add(new ArrayList<Intervallo>());
		}
	}
	
	/**
	 * Dato un giorno della settimana e un intervallo, controlla che questo non si sovrapponga con
	 * gli intervalli già presenti.
	 * 
	 * @param Giorno della settimana.
	 * @param Intervallo da controllare
	 * @return FALSE se ci sono sovrapposizioni.
	 */
	public boolean verificaDisponibilitaIntervallo(DayOfWeek dow, Intervallo intervallo_da_controllare) {
		for (Intervallo orario : giorni.get(dow.ordinal())) {
			if (orario.controllaSovrapposizione(intervallo_da_controllare))
				return false;
		}
		return true;
	}
	
	public boolean isOrarioLavorato(GregorianCalendar giorno, Intervallo intervallo_da_controllare) {
		int dow = giorno.get(GregorianCalendar.DAY_OF_WEEK) - 1;
		if (dow == 0)
			dow = 7;
		for (Intervallo orario : giorni.get(dow - 1)) {
			if (orario.contains(intervallo_da_controllare))
				return true;
		}
		return false;
	}
	
	/**
	 * Forza l'unione del nuovo intervallo alla tabella di orari.
	 * Questo metodo è pensato per essere utilizzato in risposta ad un'eventuale sovrapposizione di orari,
	 * in caso l'utente voglia forzare l'aggiunta di questo ai precedenti.
	 */
	public void unisciIntervallo(DayOfWeek dow, Intervallo nuovo_intervallo) {
		List<Intervallo> giorno = giorni.get(dow.ordinal());
		// Scorro tutti gli intervalli di lavoro del giorno in questione
		for (int i = giorno.size() - 1; i >= 0; i--) {
			Intervallo orario = giorno.get(i);
			if (orario.controllaSovrapposizione(nuovo_intervallo)) {
				// Il nuovo intervallo "ingloba" tutti gli intervalli con cui si sovrappone
				nuovo_intervallo.unisci(orario);
				// I vecchi intervalli ora sono contenuti nel nuovo, e possono essere eliminati
				giorno.remove(i);
			}
		}
		giorno.add(nuovo_intervallo);
		giorno.sort(Intervallo.COMPARATOR);
	}
	
	/**
	 * Rimuove tutti gli orari di uno specifico giorno della settimana.
	 * @param Giorno della settimana
	 */
	public void liberaGiorno(DayOfWeek dow) {
		giorni.get(dow.ordinal()).clear();
	}
	
	public String toString() {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < GIORNI_SETTIMANA.length; i++) {
			s.append(String.format(DAY_FORMAT, GIORNI_SETTIMANA[i]));
			for (Intervallo intervallo : giorni.get(i)) {
				s.append(WHITESPACE + intervallo.toString());
			}
		}
		return s.toString();
	}
	
}

