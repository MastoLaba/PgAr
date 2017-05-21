import java.util.*;
public class Paziente {

	public static final String CORNICE="-------------------------------------------";
	private String nome;
	private String cognome ;
	private int ID;
	//private Vector<Appuntmento>listaAppuntamenti=new Vector<Appuntamento>();;
	private String codPriorita;
	
	public Paziente(String nome,String cognome,int ID, String codPriorita)
	{
		this.nome=nome;
		this.cognome=cognome;
		this.ID=ID;
		this.codPriorita=codPriorita;
	}
	public String toString()
	{
		StringBuffer ris=new StringBuffer();
		ris.append(CORNICE);
		ris.append(System.lineSeparator());
		ris.append("nome paziente: "+nome);
		ris.append(System.lineSeparator());
		ris.append("cognome paziente: "+cognome);
		ris.append(System.lineSeparator());
		ris.append("ID: "+ID);
		ris.append(System.lineSeparator());
		ris.append("codice priorità: "+codPriorita);
		ris.append(System.lineSeparator());
		ris.append(CORNICE);
		return ris.toString();
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
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	/*public Vector<Appuntmento> getListaAppuntamenti() {
		return listaAppuntamenti;
	}
	public void setListaAppuntamenti(Vector<Appuntmento> listaAppuntamenti) {
		this.listaAppuntamenti = listaAppuntamenti;
	}*/
	public String getCodPriorita() {
		return codPriorita;
	}
	public void setCodPriorita(String codPriorita) {
		this.codPriorita = codPriorita;
	}
}
