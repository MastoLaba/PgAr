package dijkstra_kruskal.myUtil.myClass;

public class Address {

	private String nazione = "Italia";
	private String provincia = "BS";
	private String città = "Brescia";
	private String indirizzo = "Via Roma, n. 92";

	public Address() {

	}

	// GETTER E SETTER
	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

}
