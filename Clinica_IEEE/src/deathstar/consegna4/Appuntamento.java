package deathstar.consegna4;

import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.SimpleDateFormat;

/** Classe per la rappresentazione dei dati che caratterizzano un appuntamento.
 *  La classe non fornisce informazioni sul Medico e sul Paziente coinvolti in quanto
 *  entrambe le classi tengono da se' traccia degli appuntamenti in cui sono coinvolte
 *  e della loro controparte in tale appuntamento
 * 
 * @author Lorenzo Nodari
 *
 */
public class Appuntamento {
	
	/** Funzione utilizzata per ordinare gli appuntamenti in termini di urgenza */
	public static final Comparator<Appuntamento> COMPARATOR_URGENZA = (app1, app2) -> {
		if (app1.getUrgenza() > app2.getUrgenza()) return -1;
		else if (app1.getUrgenza() < app2.getUrgenza()) return 1;
		else return 0;
		
	};
	
	public static final Comparator<Appuntamento> COMPARATOR_TEMPORALE = (app1, app2) -> {
		if (app1.getGiorno().after(app2.getGiorno())) return 1;
		else if (app1.getGiorno().before(app2.getGiorno())) return -1;
		else return Intervallo.COMPARATOR.compare(app1.getIntervallo(), app2.getIntervallo()); //Se i giorni sono uguali delega il confronto
																							   //al comparator della classe Intervallo
	};
	
	private static final String DAY_FORMAT_STRING = "dd/MM/yyyy";

	private GregorianCalendar giorno;
	private Intervallo intervallo;
	private int urgenza;
	
	/** Crea un nuovo appuntamento dati il giorno, l'intervallo di tempo e l'urgenza
	 * 
	 * @param giorno il giorno dell'appuntamento
	 * @param intervallo L'intervallo di tempo per cui dura l'appuntamento
	 * @param urgenza Il livello di urgenza dell'appuntamento: uno dei codici definiti nella classe Persona
	 */
	public Appuntamento(GregorianCalendar giorno, Intervallo intervallo, int urgenza) {
		this.giorno = giorno;
		this.intervallo = intervallo;
		this.urgenza = urgenza;
	}

	public GregorianCalendar getGiorno() {
		return giorno;
	}

	public void setGiorno(GregorianCalendar giorno) {
		this.giorno = giorno;
	}

	public Intervallo getIntervallo() {
		return intervallo;
	}

	public void setIntervallo(Intervallo intervallo) {
		this.intervallo = intervallo;
	}

	public int getUrgenza() {
		return urgenza;
	}

	public void setUrgenza(int urgenza) {
		this.urgenza = urgenza;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		Date date = giorno.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DAY_FORMAT_STRING);
		
		buffer.append(dateFormat.format(date) + ", ");
		buffer.append(intervallo.toString());
		
		return buffer.toString();	
	}
}
