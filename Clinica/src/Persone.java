import java.util.Date;

/**
 * 
 * @author Oscar
 *
 * La classe persona contiene gli attributi e 
 * i metodi che servono per descrivere una persona ed
 * ottenere/modificare i suoi dati
 */
public class Persone {
	private String nome,cognome,codFiscale,luogoNascita;
	private Date dataNascita;
	
	
	public Persone(String nome, String cognome, String codFiscale, String luogoNascita, Date dataNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codFiscale = codFiscale;
		this.luogoNascita = luogoNascita;
		this.dataNascita = dataNascita;
		
	}

	public Persone(String nome, String cognome, String codFiscale, Date dataNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codFiscale = codFiscale;
		this.dataNascita = dataNascita;
		this.luogoNascita="Sconosciuto";
	}
	public Persone() {
		super();
	}

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String tmp;
		tmp = "Nome: "+this.nome+" Cognome: "+this.cognome+"\nCodice fiscale:"+this.codFiscale+"\nData di nascita"+this.dataNascita.toString()+"Luogo di nascita: "+this.luogoNascita;
		return tmp;
	}
	
	//Getters and Setters
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

	public String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	
}
