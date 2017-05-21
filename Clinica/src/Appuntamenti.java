import java.util.Date;

/**
 * 
 * @author Oscar
 *
 */
public class Appuntamenti {
	private static int istanze = 0;
	public static final String[] URGENZE={"nero","marrone","giallo","rosso"};
	private Date data,oraFine;
	private String urgenza;
	private Dottori medico;
	private Pazienti paziente;
	private int id;
	
	public Appuntamenti(Date data, Date orafine, String urgenza, Dottori medico, Pazienti paziente) {
		super();
		this.data = new Date();
		this.data = data;
		this.oraFine = new Date();
		this.oraFine = orafine;
		this.urgenza = urgenza;
		this.medico = new Dottori();
		this.medico = medico;
		this.paziente = new Pazienti();
		this.paziente = paziente;
		this.id = istanze;
		istanze++;
	}
	public String toString(){
		String tmp="paziente:"+this.getPaziente().toString();
		tmp+="\nMedico:"+this.medico.toString();
		tmp+="\nUrgenza:"+this.getUrgenza();
		tmp+="\nInizio:"+this.getData().getDay()+"/"+this.getData().getMonth()+"/"+this.getData().getYear()+" Alle ore->"+this.getData().getHours()+":"+this.getData().getMinutes();
		tmp+="\nInizio:"+this.getOrafine().getDay()+"/"+this.getOrafine().getMonth()+"/"+this.getOrafine().getYear()+" Alle ore->"+this.getOrafine().getHours()+":"+this.getOrafine().getMinutes();
		return tmp;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getUrgenza() {
		return urgenza;
	}

	public void setUrgenza(String urgenza) {
		this.urgenza = urgenza;
	}
	public Dottori getMedico() {
		return medico;
	}
	public void setMedico(Dottori medico) {
		this.medico = medico;
	}
	public Pazienti getPaziente() {
		return paziente;
	}
	public void setPaziente(Pazienti paziente) {
		this.paziente = paziente;
	}
	public Date getOrafine() {
		return oraFine;
	}
	public void setOrafine(Date orafine) {
		oraFine = orafine;
	}
	public int getId() {
		return id;
	}
	
	
}
