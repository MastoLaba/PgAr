package deathstar.consegna2;

public class Vino {

	private String nome;
	private String produttore;
	private int annata;
	private String regione;
	private Prezzo prezzo;
	
	public Vino(String nome, String produttore, int annata, String regione, Prezzo prezzo) {
		this.nome = nome;
		this.produttore = produttore;
		this.annata = annata;
		this.regione = regione;
		this.prezzo = prezzo;
	}

	public String getNome() {
		return nome;
	}

	public String getProduttore() {
		return produttore;
	}

	public int getAnnata() {
		return annata;
	}

	public String getRegione() {
		return regione;
	}
	
	public Prezzo getPrezzo() {
		return prezzo;
	}
	
	public boolean isCalled(String nome) {
		return nome.equals(this.nome);
	}
	
	public boolean isProdottoDa(String nome) {
		return nome.equals(this.produttore);
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Nome: " + nome);
		buffer.append("\n");
		buffer.append("Produttore: " + produttore);
		buffer.append("\n");
		buffer.append("Annata: " + annata);
		buffer.append("\n");
		buffer.append("Regione: " + regione);
		buffer.append("\n");
		buffer.append("Costo al pezzo: " + prezzo.toString());
		return buffer.toString();
	}
}
