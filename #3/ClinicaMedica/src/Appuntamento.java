import java.time.LocalDateTime;

/**
 * Classe che rappresenta il concetto di appuntamento.
 * @author qwertyteam
 * @version 1.0
 */

public class Appuntamento {
	private static final String INIZIO = "Inizio appuntamento: ";
	private static final String FINE = "Fine appuntamento: ";
	private static final String URGENZA = "Livello urgenza: ";
	public enum Urgenza {
		ROSSO,
		GIALLO,
		NERO,
		MARRONE,
	}
	private Urgenza urgenza;
	private LocalDateTime inizio;
	private LocalDateTime fine;
	
	/**
	 * Costruttore vuoto della classe Appuntamento.
	 */
	
	public Appuntamento(){
		
	}
	
	/**
	 * Costruttore con parametri della classe Appuntamento.
	 * @param urgenza Urgenza dell'appuntamento. ROSSO = In pericolo di vita, GIALLO = Grave, NERO = Deceduto, 
	 * MARRONE = In condizioni non gravi
	 * @param inizio Data d'inizio dell'appuntamento
	 * @param fine Data di fine dell'appuntamento
	 */
	
	public Appuntamento (Urgenza urgenza, LocalDateTime inizio, LocalDateTime fine){
		this.urgenza = urgenza;
		this.inizio = inizio;
		this.fine = fine;
	}
	
	/**
	 * Metodo che restituisce la data d'inizio dell'appuntamento.
	 * @return Data d'inizio dell'appuntamento.
	 */
	
	public LocalDateTime getInizio(){
		return inizio;
	}
	
	/**
	 * Metodo che restituisce la data di fine dell'appuntamento.
	 * @return Data di fine dell'appuntamento
	 */
	
	public LocalDateTime getFine(){
		return fine;
	}
	
	/**
	 * Metodo che restituisce l'urgenza dell'appuntamento.
	 * @return Urgenza dell'appuntamento
	 */
	
	public Urgenza getUrgenza(){
		return urgenza;
	}
	
	/**
	 * Metodo che imposta la data d'inizio dell'appuntamento.
	 * @param inizio Data d'inizio dell'appuntamento
	 */
	
	public void setInizio(LocalDateTime inizio){
		this.inizio = inizio;
	}
	
	/**
	 * Metodo che imposta la data di fine dell'appuntamento.
	 * @param fine Data di fine dell'appuntamento
	 */
	
	public void setFine(LocalDateTime fine){
		this.fine = fine;
	}
	
	/**
	 * Metodo che imposta l'urgenza dell'appuntamento.
	 * @param urgenza Urgenza dell'appuntamento
	 */
	
	public void setUrgenza(Urgenza urgenza){
		this.urgenza = urgenza;
	}
	
	/**
	 * Metodo toString della classe Appuntamento. Restituisce le proprietà dell'istanza
	 * specifica.
	 * @return Stringa che mostra le caratteristiche dell'appuntamento.
	 */
	
	public String toString(){
		StringBuffer descrizione = new StringBuffer();
		descrizione.append(INIZIO+inizio+FINE+fine+URGENZA+urgenza);
		return descrizione.toString();
	}
}
