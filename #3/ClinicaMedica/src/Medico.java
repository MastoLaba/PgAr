import java.time.LocalTime;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;


/**
 * Classe che rappresenta il concetto di medico. 
 * @author qwertyteam
 * @version 1.0
 */

public class Medico {
	private String nome;
	private String cognome;
	private int ID;
	private int annoLaurea;
	private int annoAssunzione;
	private TreeMap<Integer, Vector<Appuntamento>> orariAppuntamenti;
	private TreeMap<Integer, Vector<LocalTime>> orariFissi;
	
	/**
	 * Costruttore vuoto della classe medico.
	 */
	
	public Medico(){
		orariFissi = new TreeMap<Integer, Vector<LocalTime>>();
		orariAppuntamenti = new TreeMap<Integer, Vector<Appuntamento>>();
	}
	
	/**
	 * Costruttore con parametri della classe Medico.
	 * @param nome Nome del medico
	 * @param cognome Cognome del medico
	 * @param annoLaurea Anno di laurea del medico
	 * @param annoAssunzione Anno assunzione del medico
	 */
	
	public Medico(String nome, String cognome, int annoLaurea, int annoAssunzione, TreeMap<Integer, 
			Vector<Appuntamento>> orariAppuntamenti, TreeMap<Integer, Vector<LocalTime>> orariFissi){
		this.nome = nome;
		this.cognome = cognome;
		this.annoLaurea = annoLaurea;
		this.annoAssunzione = annoAssunzione;
		this.orariAppuntamenti = orariAppuntamenti;
		this.orariFissi = orariFissi;
	}
	
	/**
	 * Metodo che imposta il nome del medico.
	 * @param nome Nome
	 */
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	/**
	 * Metodo che imposta il cognome del medico.
	 * @param cognome Cognome
	 */
	
	public void setCognome(String cognome){
		this.cognome = cognome;
	}
	
	/**
	 * Metodo che imposta la matricola del medico.
	 * @param id Matricola
	 */
	
	public void setID(int id){
		this.ID = id;
	}
	
	/**
	 * Metodo che imposta la data di laurea del medico.
	 * @param giorno Data di laurea
	 */
	
	public void setLaurea(int giorno){
		this.annoLaurea = giorno;
	}
	
	/**
	 * Metodo che imposta l'anno di assunzione del medico.
	 * @param assunzione Data di assunzione
	 */
	
	public void setAssunzione(int assunzione){
		this.annoAssunzione = assunzione;
	}
	
	/**
	 * Metodo che permette di impostare gli orari lavorativi del medico.
	 * @param orariFissi Orari lavorativi da impostare.
	 */
	
	public void setOrariFissi(TreeMap<Integer, Vector<LocalTime>> orariFissi){
		this.orariFissi = orariFissi;
	}
	
	/**
	 * Metodo che restituisce il nome del medico.
	 * @return Nome del medico
	 */
	
	public String getNome(){
		return nome;
	}
	
	/**
	 * Metodo che restituisce il cognome del medico.
	 * @return Cognome del medico
	 */
	
	public String getCognome(){
		return cognome;
	}
	
	/**
	 * Metodo che restituisce la matricola del medico.
	 * @return Matricola del medico
	 */
	
	public int getID(){
		return ID;
	}
	
	/**
	 * Metodo che restituisce l'anno di laurea del medico.
	 * @return Anno di laurea.
	 */
	
	public int getAnnoLaurea(){
		return annoLaurea;
	}
	
	/**
	 * Metodo che restituisce l'anno di assunzione del medico.
	 * @return Anno di assunzione
	 */
	
	public int getAnnoAssunzione(){
		return annoAssunzione;
	}
	
	/**
	 * Metodo che restituisce tutti i prossimi appuntamenti che ha il medico.
	 * @return Appuntamenti del medico
	 */
	
	public TreeMap<Integer, Vector<Appuntamento>> getOrariAppuntamenti(){
		return orariAppuntamenti;
	}
	
	/**
	 * Metodo che restituisce gli orari lavorativi del medico.
	 * @return Orari lavorativi
	 */
	
	public TreeMap<Integer, Vector<LocalTime>> getOrariFissi(){
		return orariFissi;
	}
	
	/**
	 * Metodo toString della classe Medico. Restituisce una breve descrizione delle sue principali
	 * caratteristiche.
	 * @return Una stringa con breve descrizione
	 */
	
	public String toString(){
		StringBuilder descrizioneMedico = new StringBuilder();
		descrizioneMedico.append("Nome: ").append(nome).append("\nCognome: ").append(cognome)
		.append("\nID: ").append(ID).append("\nAnno laurea: ").append(annoLaurea)
		.append("\nAnno assunzione: ").append(annoAssunzione);
		return descrizioneMedico.toString();
	}

	/**
	 * Metodo equals della classe Medico. Permette di verificare se due medici sono la stessa persona in base a:
	 * - Locazione dell'oggetto in memoria;
	 * - Se hanno lo stesso ID, nome e cognome.
	 * @param medico Medico da verificare
	 * @return True se sono la stessa persona, false se null oppure se sono due persone diverse
	 */
	
	public boolean equals(Medico medico) {
		if(medico == null)
			return false;
		if(this == medico)
			return true;
		if(ID == medico.ID && nome.equals(medico.nome) && cognome.equals(medico.cognome))
			return true;
		return false;
	}
	
	/**
	 * Metodo che dovrebbe riordinare ogni Vector di Appuntamenti presente all'interno della TreeMap attraverso
	 * un algoritmo di Bubble-Sort. In ogni caso non è funzionante poichè ad ogni richiamo della funzione il 
	 * Vector rimane ordinato in base all'inserimento degli elementi...
	 */
	
	public void getAppuntamentiTemporale(){
		Iterator<Vector<Appuntamento>> iterator = orariAppuntamenti.values().iterator();
		while(iterator.hasNext()){
			Vector<Appuntamento> appuntamenti = iterator.next();
			for(int i = 0; i < appuntamenti.size(); i++){
				boolean lettura = false;
				for(int j = 0; j < appuntamenti.size() - 1; j++){
					if(appuntamenti.get(j).getInizio().isAfter(appuntamenti.get(j + 1).getInizio())){
						Appuntamento apTemp = appuntamenti.get(j);
						appuntamenti.set(j, appuntamenti.get(j + 1));
						appuntamenti.set(j + 1, apTemp);
						lettura = true;
					}
				}
				if(!lettura)
					break;
			}
		}
	}
}
