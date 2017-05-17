
public class Valuta {

	private String valuta;
	private double tassoRiferimento;
	public Valuta(String valuta, double tassoRiferimento)
	{
		this.valuta = valuta;
		this.tassoRiferimento = tassoRiferimento;
	}
	public String getValuta() {
		return valuta;
	}
	public void setValuta(String valuta) {
		this.valuta = valuta;
	}
	public double getTassoRiferimento() {
		return tassoRiferimento;
	}
	public void setTassoRiferimento(double tassoRiferimento) {
		this.tassoRiferimento = tassoRiferimento;
	}
	
	
}