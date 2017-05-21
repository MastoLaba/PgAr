package deathstar.consegna4;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeMap;
import java.util.NavigableMap;

/** Classe per la rappresentazione di un Medico della clinica
 * 
 * @author Lorenzo Nodari
 *
 */
public class Medico {
	
	private static final String ID_CHAR = "#";
	private static final String TOSTRING_SEPARATOR = ", ";
	private static final String ANNO_LAUREA_STRING = "Anno di Laurea: ";
	private static final String ANNO_ASSUNZIONE_STRING = "Anno di assunzione: ";
	private static final String WHITESPACE = " ";
	
	private static int idGenerator = 0;
	
	private String nome;
	private String cognome;
	private int id;
	private int annoLaurea;
	private int annoAssunzione;
	private TreeMap<Appuntamento, Paziente> listaAppuntamenti = new TreeMap<>(Appuntamento.COMPARATOR_TEMPORALE); //Le chiavi sono ordinate per ordine temporale
	private OrarioLavorativo orario;
	
	/** Crea un nuovo Medico dato il nome, il cognome, l'anno di laurea e l'anno di assunzione.
	 *  All'istanza creata viene inoltre assegnato automaticamente un id univoco
	 * 
	 * @param nome Il nome del medico
	 * @param cognome Il cognome del medico
	 * @param annoLaurea L'anno di laurea del medico
	 * @param annoAssunzione L'anno di assunzione del medico
	 */
	public Medico(String nome, String cognome, int annoLaurea, int annoAssunzione, OrarioLavorativo orario) {
		this.nome = nome;
		this.cognome = cognome;
		this.annoLaurea = annoLaurea;
		this.annoAssunzione = annoAssunzione;
		this.orario = orario;
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
	public int getAnnoLaurea() {
		return annoLaurea;
	}
	public void setAnnoLaurea(int annoLaurea) {
		this.annoLaurea = annoLaurea;
	}
	public int getAnnoAssunzione() {
		return annoAssunzione;
	}
	public void setAnnoAssunzione(int annoAssunzione) {
		this.annoAssunzione = annoAssunzione;
	}
	public int getId() {
		return id;
	}
	public TreeMap<Appuntamento, Paziente> getAppuntamenti() {
		return listaAppuntamenti;
	}
	public int getNumAppuntamenti() {
		return listaAppuntamenti.size();
	}
	public OrarioLavorativo getOrario() {
		return orario;
	}
	public void setOrario(OrarioLavorativo orario) {
		this.orario = orario;
	}
	
	/** Verifica che il Medico sia libero in un dato giorno, durante un dato intervallo.
	 *  Il metodo DEVE essere chiamato prima di poter eseguire fissaAppuntamento al fine
	 *  di assicurare che tale prenotazione sia effettuabile
	 * 
	 * @param giorno Il giorno in cui si vuole verificare la presenza di impegni
	 * @param orario L'orario durante il quale si vuole verificare la presenza di impegni
	 * @return true se il Medico non ha impegni per la data specificata, false altrimenti
	 */
	public boolean liberoPerAppuntamento(GregorianCalendar giorno, Intervallo orario) {
		Set<Appuntamento> appuntamenti = this.listaAppuntamenti.keySet();
		ArrayList<Appuntamento> lista_appuntamenti_giorno_cercato = new ArrayList<>();
		
		if (!this.orario.isOrarioLavorato(giorno, orario))
			return false;
		
		//Creo il subset di appuntamenti che corrispondono al giorno che si sta verificando
		for (Appuntamento tmp : appuntamenti) {
			if (tmp.getGiorno().equals(giorno))
				lista_appuntamenti_giorno_cercato.add(tmp);
			else if (tmp.getGiorno().after(giorno))
				break;
		}
		
		//Verifico che l'intervallo dato non collida con nessuno degli intervalli impegnati del giorno cercato
		for (Appuntamento tmp : lista_appuntamenti_giorno_cercato) {
			if (tmp.getIntervallo().controllaSovrapposizione(orario))
				return false;
		}
		
		return true;
	}
	
	/** Fissa un Appuntamento per il Medico con un dato Paziente.
	 *  Il Paziente e' automaticamente aggiornato in modo da riflettere il cambiamento.
	 *  Precondizioni per la corretta esecuzione del metodo sono la verifica
	 *  della disponibilita' sia del Paziente che del Medico mediante i rispettivi
	 *  metodi liberoPerAppuntamento
	 * 
	 * @param a L'Appuntamento da fissare
	 * @param p Il Paziente con cui fissare l' Appuntamento
	 */
	public void fissaAppuntamento(Appuntamento a, Paziente p) {
		listaAppuntamenti.put(a, p);
	}
	
	/** Restituisce una Map di tutti gli appuntamenti fissati per il dottore tra i due giorni specificati.
	 *  Se nessun appuntamento viene trovato, viene ritornato null
	 * 
	 * @param inizio Il giorno di inizio della ricerca
	 * @param fine Il giorno di fine della ricerca
	 * @return Gli appuntamenti trovati o null se non ve ne sono nell'intervallo richiesto
	 */
	public NavigableMap<Appuntamento, Paziente> getAppuntamentiTra(GregorianCalendar inizio, GregorianCalendar fine) {
		Set<Appuntamento> keySet = listaAppuntamenti.keySet();
		Appuntamento firstKey = null;
		Appuntamento lastKey = null;
		
		for (Appuntamento app : keySet) {
			boolean appDopoInizio = app.getGiorno().after(inizio) || app.getGiorno().equals(inizio);
			boolean appPrimaFine = app.getGiorno().before(fine);
			if (appDopoInizio && appPrimaFine) {
				if (firstKey == null)
					firstKey = app;
				else
					lastKey = app;
			}
			else if (!appPrimaFine) {
				break;
			}
		}
		
		if (firstKey == null)
			return null;
		else if (firstKey != null && lastKey == null)
			return listaAppuntamenti.tailMap(firstKey, true);
		else
			return listaAppuntamenti.subMap(firstKey, true, lastKey, true);
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(ID_CHAR + String.valueOf(id) + TOSTRING_SEPARATOR);
		buffer.append(cognome + WHITESPACE + nome + TOSTRING_SEPARATOR);
		buffer.append(ANNO_LAUREA_STRING + String.valueOf(annoLaurea) + TOSTRING_SEPARATOR);
		buffer.append(ANNO_ASSUNZIONE_STRING + String.valueOf(annoAssunzione) + TOSTRING_SEPARATOR);
		return buffer.toString();
	}
	
	/** Restituisce una rappresentazione sintetica del Medico sotto forma di String.
	 *  La rappresentazione contiene l'id del medico e il suo nome
	 * 
	 * @return Una rappresentazione testuale del Medico
	 */
	public String toStringCorto() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(ID_CHAR + String.valueOf(id) + TOSTRING_SEPARATOR);
		buffer.append(cognome + WHITESPACE + nome);
		return buffer.toString();
	}

}
