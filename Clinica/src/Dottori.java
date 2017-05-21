import java.util.Date;
/**
 * 
 * @author Oscar
 *la classe Dottori estende persone e quindi ne eredità gli attributi,
 * in più ne vengono aggiunti alcuni specifici dei medici più i metodi 
 * per modificarli o visualizzarli
 *
 */
public class Dottori extends Persone {
	private int matricola;
	private Date annoLaurea, annoAssunzione;
	private Orari orario;
	
	public Dottori(String nome, String cognome, String codFiscale, String luogoNascita, Date dataNascita, int matricola,
			Date annoLaurea, Date annoAssunzione, Orari orario) {
		super(nome, cognome, codFiscale, luogoNascita, dataNascita);
		this.matricola = matricola;
		this.annoLaurea = annoLaurea;
		this.annoAssunzione = annoAssunzione;
		this.orario = orario;
	}
	public Dottori() {
		super();
	}
	public boolean isInServizio(String giorno,Date oraI, Date oraF){
		System.err.println(giorno);
		for(int i=0; i<Orari.SETTIMANA.length;i++){
			if(Orari.SETTIMANA[i].equalsIgnoreCase(giorno)){
				if(oraI.getHours()>=orario.getInizio().get(i).getHours() && oraF.getHours()<orario.getFine().get(i).getHours()){
					if(oraI.getMinutes()>=orario.getInizio().get(i).getMinutes() && oraF.getMinutes()<orario.getFine().get(i).getMinutes())
						return true;
				}
			}
		}
		return false;
	}
	public String toString(){
		String tmp;
		tmp = "Nome: "+this.getNome()+" Cognome: "+this.getCognome()+"\nCodice fiscale:"+this.getCodFiscale()+"\nData di nascita"+this.getDataNascita().getDay()+"/"+this.getDataNascita().getMonth()+"/"+this.getDataNascita().getYear()+"Luogo di nascita: "+this.getLuogoNascita();
		tmp +="\n:Matricola"+this.getMatricola()+"\n Anno di laurea:"+this.getAnnoLaurea().getYear()+" Anno di assunzione:"+this.getAnnoAssunzione().getYear();
		tmp +="\nOrario:\n"+this.getOrario().toString();
		return tmp;
	}
	//Getters and Setters
	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public Date getAnnoLaurea() {
		return annoLaurea;
	}

	public void setAnnoLaurea(Date annoLaurea) {
		this.annoLaurea = annoLaurea;
	}

	public Date getAnnoAssunzione() {
		return annoAssunzione;
	}

	public void setAnnoAssunzione(Date annoAssunzione) {
		this.annoAssunzione = annoAssunzione;
	}

	public Orari getOrario() {
		return orario;
	}

	public void setOrario(Orari orario) {
		this.orario = orario;
	}
	
	
	

}
