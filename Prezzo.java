package enoteca;

public class Prezzo {
	
	private double costo;
	private String valuta;
	
	public Prezzo(){
		
	}
	public Prezzo(double costo,String valuta )
	{
		this.costo=costo;
		this.valuta=valuta;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getValuta() {
		return valuta;
	}
	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

}
