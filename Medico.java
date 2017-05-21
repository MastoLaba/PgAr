import java.lang.reflect.Array;
import java.util.*;

public class Medico {
	public static final String CORNICE="-------------------------------------------";
	private String nome;
	private String cognome ;
	private int ID;
	private int annoLaurea;
	private int annoAssunzione;
	private boolean[][] settimanaLavorativa= new boolean[7][11];//il valore è true se in quella data il medico è impegnato false se il medico è libero
	private Vector<Appuntmento>listaAppuntamenti=new Vector<Appuntamento>();;
	
	public Medico(String nome,String cognome,int ID, int annoLaurea,int annoAssunzione)
	{
		this.nome=nome;
		this.cognome=cognome;
		this.ID=ID;
		this.annoLaurea=annoLaurea;
		this.annoAssunzione=annoAssunzione;
	}
	public String toString()
	{
		StringBuffer ris=new StringBuffer();
		ris.append(CORNICE);
		ris.append(System.lineSeparator());
		ris.append("nome medico: "+nome);
		ris.append(System.lineSeparator());
		ris.append("cognome medico: "+cognome);
		ris.append(System.lineSeparator());
		ris.append("ID: "+ID);
		ris.append(System.lineSeparator());
		ris.append("anno laurea medico: " +annoLaurea);
		ris.append(System.lineSeparator());
		ris.append("anno assuzione medico: " +annoAssunzione);
		ris.append(CORNICE);
		return ris.toString();
	}
	public int getSettimanaLavorativa(int giorno,int ora)//questo metonìdo richiede in ingeresso un giorno e un ora e restutuisce 1 se in tale data il medico è disponibile 0 altrimenti
	{
		if(settimanaLavorativa[giorno][ora]==true)
		{
			return 0;
		}
		return 1 ;
		
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
	public boolean[][] getSettimanaLavorativa() {
		return settimanaLavorativa;
	}
	public void setSettimanaLavorativa(boolean[][] settimanaLavorativa) {
		this.settimanaLavorativa = settimanaLavorativa;
	}
	public Vector<Appuntmento> getListaAppuntamenti() {
		return listaAppuntamenti;
	}
	public void setListaAppuntamenti(Vector<Appuntmento> listaAppuntamenti) {
		this.listaAppuntamenti = listaAppuntamenti;
	}

}
