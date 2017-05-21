import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Classe che rappresenta il concetto di Clinica medica. È progettata attualmente per 
 * poter gestire gli appuntamenti tra pazienti e medici.
 * @author qwertyteam
 * @version 0.1
 */

public class Clinica implements Interfaccia {
	private String nomeClinica;
	private Vector<Double> tempi;
	private Vector<Medico> listaMedici;
	private Vector<Paziente> listaPazienti;
	
	private final static String LISTA_PAZIENTI_INFO = "La lista dei pazienti presenti in clinica:";
	private final static String LISTA_MEDICI_INFO = "La lista dei medici presenti in clinica:";
	private final static String INITIAL_TIME = "Tempo iniziale: ";
	private final static String DELAY_INFO = "Il delay dal tempo %d al tempo %d è: ";
	private final static String APPOINTMENT_DOCTOR = "Il numero di appuntamenti del medico %s sono %d: ";
	
	/**
	 * Costruttore con parametri della classe Clinica.
	 * @param nomeClinica Nome associato alla clinica
	 * @param listaPazienti Lista dei pazienti
	 * @param listaMedici Lista dei medici che lavorano alla clinica
	 */
	
	public Clinica(String nomeClinica, Vector <Paziente> listaPazienti, Vector<Medico> listaMedici){
		this.nomeClinica = nomeClinica;
		this.listaPazienti = listaPazienti;
		this.listaMedici = listaMedici;
		tempi = new Vector<>();
	}
	
	/**
	 * Metodo per modificare il nome della clinica medica.
	 * @param nome Nuovo nome per la clinica
	 */
	
	public void setNomeClinica(String nome){
		this.nomeClinica = nome;
	}
	
	/**
	 * Metodo per cercare un medico nella lista della clinica tramite ID.
	 * @param ID ID del medico da cercare
	 * @return Il medico che soddisfa il criterio di ricerca.
	 */
	
	public Medico cercaMedico(int ID){
		for(Medico medico : listaMedici){
			if(medico.getID() == ID){
				return medico;
			}
		}
		return null;
	}
	
	/**
	 * Metodo per controllare la possibilità di aggiungere un appuntamento richiesto
	 * dal paziente ad una data ora con un dato medico. Se la possibilità c'è, aggiungi
	 * l'appuntamento alla lista.
	 * @param m Medico richiesto per l'appuntamento
	 * @param inizio Data d'inizio (solitamente ora d'inizio)
	 * @param fine Data di fine (solitamente ora di fine)
	 * @return Vero se c'è la disponibilità da parte del medico, falso altrimenti
	 */
	
	public boolean controllaAppuntamento(Medico m, Appuntamento ap){
		boolean isDoctorExists = false;
		for(Medico medico : listaMedici){
			if(medico.equals(m)){
				isDoctorExists = true;
				break;
			}
		}
		
		if(!isDoctorExists)
			return false;
		
		Vector<Appuntamento> listaAppuntamentiMedico = m.getOrariAppuntamenti()
				.get(ap.getInizio().getYear() + ap.getInizio().getMonthValue() + ap.getInizio().getDayOfMonth());
		
		if(listaAppuntamentiMedico != null && !listaAppuntamentiMedico.isEmpty()){
			
			for(Appuntamento appuntamento : listaAppuntamentiMedico){
				
				if((ap.getInizio().isBefore(appuntamento.getInizio()) && ap.getFine().isBefore(appuntamento.getInizio()))
						|| (ap.getInizio().isAfter(appuntamento.getFine()) && ap.getFine().isAfter(appuntamento.getFine()))){
				}
				else
					return false;
			} 
		
		}else{
			Vector<Appuntamento> appuntamento = new Vector<>();
			appuntamento.add(ap);
			m.getOrariAppuntamenti().put(ap.getInizio().getYear() + ap.getInizio().getMonthValue() + ap.getInizio().getDayOfMonth(), appuntamento);
			return true;
		}
		m.getOrariAppuntamenti().get(ap.getInizio().getYear() + ap.getInizio().getMonthValue() + ap.getInizio().getDayOfMonth()).add(ap);
		return true;
	}
	
	/**
	 * Metodo che permette di stampare gli orari di tutti i medici.
	 * @return Lista di tutti gli orari di tutti i medici.
	 */
	
	public Vector<LocalTime> stampaOrariMedici(){
		Vector<LocalTime> orariTotali = new Vector<LocalTime>();
		for(Medico medico: listaMedici){
			Iterator<Vector<LocalTime>> iterator = medico.getOrariFissi().values().iterator();
			while(iterator.hasNext()){
				for(LocalTime data: iterator.next()){
					orariTotali.add(data);
				}
			}
			
		}
		return orariTotali;
	}

	/**
	 * Metodo toString per la rappresentazione visuale dello stato della clinica.
	 * @return Stato della clinica
	 */
	
	public String toString(){
		return String.join("\n", nomeClinica, stampaPazienti());
	}
	
	/**
	 * Metodo che permette di stampare tutta lista dei medici che lavorano nella clinica.
	 * @return Stringa che mostra a schermo la lista di tutti i medici
	 */
	
	@Override
	public String stampaMedici() {
		StringBuilder sb = new StringBuilder();
		sb.append(LISTA_MEDICI_INFO).append("\n");
		
		for(Medico medico : listaMedici){
			sb.append(medico.toString()).append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Metodo che permette di restituire gli orari lavorativi di un dato medico.
	 * @return Lista contenente gli orari lavorativi, suddivisi anche in base alle 
	 * pause lavorative.
	 */
	
	@Override
	public TreeMap<Integer, Vector<LocalTime>> stampaOrari(Medico m) {
		for(Medico medico : listaMedici){
			if(m.equals(medico)){
				return m.getOrariFissi();
			}
		}
		return null;
	}
	
	/**
	 * Metodo che permette di restituire gli appuntamenti (compresi gli orari) di un
	 * dato medico dato il suo ID.
	 * @return Lista degli appuntamenti di un dato medico  
	 */
	
	@Override
	public TreeMap<Integer, Vector<Appuntamento>> stampaAppuntamenti(int ID) {
		for(Medico medico: listaMedici)
			if(medico.getID() == ID){
				return medico.getOrariAppuntamenti();
			}
		
		return null;
	}

	/**
	 * Metodo che permette di stampare la lista di tutti i pazienti presenti attualmente
	 * in clinica.
	 * @return Lista di tutti i pazienti in clinica 
	 */
	
	@Override
	public String stampaPazienti() {
		StringBuilder sb = new StringBuilder();
		sb.append(LISTA_PAZIENTI_INFO).append("\n");
		for(Paziente paziente : listaPazienti){
			sb.append(paziente.toString() + "\n");
		}
		return sb.toString();
	}

	/**
	 * Metodo che permette di stampare il delay tra un'attività della clinica e l'altra.
	 * Deve tenere informato l'utente anche delle attività precedenti effettuate e 
	 * stampare ogni delay a schermo. 
	 */
	
	@Override
	public void getTime() {
		tempi.add(Double.longBitsToDouble(Instant.now().toEpochMilli()));
		for(int i = 0; i < tempi.size(); i++){
			if(i == 0){
				System.out.println(INITIAL_TIME + tempi.get(i));
			}else{
				System.out.printf(DELAY_INFO + (tempi.get(i-1) - tempi.get(i)) + "\n", i-1, i);
			}
		}
	}

	/**
	 * Metodo che permette di stampare il numero di appuntamenti in clinica presenti in
	 * un certo intervallo di tempo.
	 * @return Numero di appuntamenti 
	 */
	
	@Override
	public Vector<Appuntamento> stampaAppuntamenti(LocalDateTime inizio, LocalDateTime fine) {
		Vector<Appuntamento> listaAppuntamenti = new Vector<Appuntamento>();
		for(Paziente paziente : listaPazienti){
			for(Appuntamento appuntamento : paziente.getStoriaAppuntamenti()){
				if(appuntamento.getInizio().isAfter(inizio) && appuntamento.getFine().isBefore(fine)){
					listaAppuntamenti.add(appuntamento);
				}
			}
		}
		return listaAppuntamenti;
	}
	
	/**
	 * Metodo che permette di stampare il numero totale degli appuntamenti di un medico
	 * data la sua matricola.
	 * @return Numero di appuntamenti totali
	 */
	
	@Override
	public String stampaNumAppuntamenti(int matricola) {
		int numeroAppuntamenti = 0;
		String cognomeMedico = "";
		for(Medico medico : listaMedici){
			if(medico.getID() == matricola){
				cognomeMedico = medico.getCognome();
				Iterator<Vector<Appuntamento>> it = medico.getOrariAppuntamenti().values().iterator();
				while(it.hasNext()){
					numeroAppuntamenti += it.next().size();
				}
			}
		}
		return String.format(APPOINTMENT_DOCTOR, cognomeMedico, numeroAppuntamenti);
	}
	
	/**
	 * Metodo che permette di stampare la lista dei pazienti con almeno un appuntamento prenotato
	 * e raggruppandoli per numero di appuntamenti futuri, mantenendone l'ordine di vicinanza.
	 * @return Lista dei pazienti
	 */
	
	public TreeMap<Integer,Vector<Paziente>> stampaPazientiPerAppuntamento(){
		Vector<Paziente> listaPazientiTemporanea = new Vector<>(); 
		TreeMap<Integer, Vector<Paziente>> mapPazienti = new TreeMap <Integer, Vector<Paziente>>(); 
		
		for(Paziente paziente : listaPazienti){
			if(!paziente.getStoriaAppuntamenti().isEmpty()){ 
				  if(mapPazienti.containsKey(paziente.getStoriaAppuntamenti().size())){ 
					  listaPazientiTemporanea = mapPazienti.get(paziente.getStoriaAppuntamenti().size());
					  listaPazientiTemporanea.add(paziente);
					  mapPazienti.put(paziente.getStoriaAppuntamenti().size(), listaPazientiTemporanea);
				  }
				  else{
					  if(listaPazientiTemporanea == null)
						  listaPazientiTemporanea = new Vector<>();
					  listaPazientiTemporanea.add(paziente);
					  mapPazienti.put(paziente.getStoriaAppuntamenti().size(), listaPazientiTemporanea);
				  }
			 }
			 listaPazientiTemporanea = null; 
		}
		return mapPazienti;
	}
}
