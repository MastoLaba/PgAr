import java.util.Date;

public class Pazienti extends Persone{
	private String gruppoSanguineo;

	public Pazienti(String nome, String cognome, String codFiscale, String luogoNascita, Date dataNascita,
			String gruppoSanguineo) {
		super(nome, cognome, codFiscale, luogoNascita, dataNascita);
		this.gruppoSanguineo = gruppoSanguineo;
	}
	
	public Pazienti() {
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		String tmp;
		tmp = "Nome: "+this.getNome()+" Cognome: "+this.getCognome()+"\nCodice fiscale:"+this.getCodFiscale()+"\nData di nascita"+this.getDataNascita().getDay()+"/"+this.getDataNascita().getMonth()+"/"+this.getDataNascita().getYear()+"Luogo di nascita: "+this.getLuogoNascita();
		tmp +="\nGruppo sanguineo: "+this.gruppoSanguineo;
		return tmp;
	}
	
	public String getGruppoSanguineo() {
		return gruppoSanguineo;
	}

	public void setGruppoSanguineo(String gruppoSanguineo) {
		this.gruppoSanguineo = gruppoSanguineo;
	}

}
