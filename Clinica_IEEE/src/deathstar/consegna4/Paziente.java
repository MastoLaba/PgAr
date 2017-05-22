package deathstar.consegna4;

import java.util.GregorianCalendar;
import java.util.TreeMap;
import java.util.Set;

/** Classe per la rappresentazione di un paziente della clinica
 * 
 * @author Lorenzo Nodari
 *
 */
public class Paziente {
	
	/** Costante che rappresenta il livello massimo di gravita' della
	 *  condizione del paziente
	 */
	public static final int CODICE_ROSSO = 3;
	/** Costante che rappresenta il secondo livello di gravita'
	 *  massimo per la condizione del paziente
	 */
	public static final int CODICE_GIALLO = 2;
	/** Costante che rappresenta il penultimo livello di gravita' della
	 *  condizione del paziente. In particolare il codice nero indica
	 *  che il paziente non ce l'ha fatta
	 */
	public static final int CODICE_NERO = 1;
	/** Costante che rappresenta la condizione meno grave del paziente.
	 *  In particolare, un paziente in codice marrone viene dimesso il
	 *  giorno successivo
	 */
	public static final int CODICE_MARRONE = 0;
		
	//Utilizzata per generare id univoci per i vari pazienti
	private static int idGenerator = 0;
	
	private String nome;
	private String cognome;
	private int id;
	private int condizione;
	private TreeMap<Appuntamento, Medico> listaAppuntamenti = new TreeMap<>(Appuntamento.COMPARATOR_URGENZA);
	
	private static final String ID_CHAR = "#";
	private static final String TOSTRING_SEPARATOR = ", ";
	private static final String[] CODICI = {"Codice marrone", "Codice nero", "Codice giallo", "Codice rosso"}; 
	private static final String WHITESPACE = " ";
	
	
	/** Crea un nuovo Paziente dati il nome, il cognome e la condizione. L'id
	 *  viene generato automaticamente e assegnato al Paziente.
	 *  La lista degli appuntamenti e' automaticamente creata vuota.
	 * 
	 * @param nome Il nome del paziente
	 * @param cognome Il cognome del paziente
	 * @param condizione La condizione del paziente: una costante tra quelle definite nella classe
	 */
	public Paziente(String nome, String cognome, int condizione) {
		this.nome = nome;
		this.cognome = cognome;
		this.condizione = condizione;
		this.id = idGenerator;
		idGenerator++;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getCondizione() {
		return condizione;
	}

	public void setCondizione(int condizione) {
		this.condizione = condizione;
	}

	public int getId() {
		return id;
	}
	
	public TreeMap<Appuntamento, Medico> getAppuntamenti() {
		return listaAppuntamenti;
	}
	
	public int getNumAppuntamenti() {
		return listaAppuntamenti.size();
	}
	
	/** Verifica che il Paziente sia libero in un dato giorno, durante un dato intervallo.
	 *  Il metodo DEVE essere chiamato prima di poter eseguire fissaAppuntamento al fine
	 *  di assicurare che tale prenotazione sia effettuabile
	 * 
	 * @param giorno Il giorno in cui si vuole verificare la presenza di impegni
	 * @param orario L'orario durante il quale si vuole verificare la presenza di impegni
	 * @return true se Paziente non ha impegni per la data specificata, false altrimenti
	 */
	public boolean liberoPerAppuntamento(GregorianCalendar giorno, Intervallo orario) {
		Set<Appuntamento> appuntamenti = listaAppuntamenti.keySet();
		for (Appuntamento tmp : appuntamenti) {
			if (tmp.getGiorno().equals(giorno) &&
				tmp.getIntervallo().controllaSovrapposizione(orario))
				
				return false;
		}
		
		return true;
	}
	
	/** Aggiunge un Appuntamento con  un dato Medico per il paziente.
	 *  Precondizioni alla corretta esecuzione del metodo e' la verifica
	 *  mediante liberoPerAppuntamento sia del Paziente che del Medico coinvolti
	 * 
	 * @param a L'Appuntamento da prenotare
	 * @param m Il Medico con il quale si vuole prenotare l'appuntamento
	 */
	public void fissaAppuntamento(Appuntamento a, Medico m) {
		listaAppuntamenti.put(a, m);
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(ID_CHAR + String.valueOf(id) + TOSTRING_SEPARATOR);
		buffer.append(nome + WHITESPACE + cognome + TOSTRING_SEPARATOR);
		buffer.append(CODICI[condizione]);
		return buffer.toString();
	}
	
	
	
	

}
