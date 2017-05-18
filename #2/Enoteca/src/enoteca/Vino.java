package enoteca;

public class Vino {
	private String nomeVino;
	private int annataVino;
	private double prezzoVino;
	private String locazioneGeografica;
	private String produttore;
	private int inDeposito;
	private char valuta;
	private static final String DA_STAMPARE = "Nome: %s\t Annata: %d\t Prezzo %.2f\t Locazione geografica: %s\t Produttore: %s\t In deposito: %d\n";
	private static final double EU_TO_DO = 1.09;
	
	public Vino(){
		
	}
	
	public Vino(String nomeVino, int annataVino, double prezzoVino, String locazioneGeografica, String produttore, int inDeposito, char valuta){
		this.nomeVino = nomeVino;
		this.annataVino = annataVino;
		this.prezzoVino = prezzoVino;
		this.locazioneGeografica = locazioneGeografica;
		this.produttore = produttore;
		this.inDeposito = inDeposito;
		this.valuta= valuta;
	}
	
	public String getNomeVino() {
		return nomeVino;
	}

	public void setNomeVino(String nome) {
		this.nomeVino = nome;
	}

	public int getAnnataVino() {
		return annataVino;
	}

	public void setAnnataVino(int annata) {
		this.annataVino = annata;
	}

	public double getPrezzoVino() {
		return prezzoVino;
	}

	public void setPrezzoVino(double prezzo) {
		this.prezzoVino = prezzo;
	}

	public String getLocazioneGeografica() {
		return locazioneGeografica;
	}

	public void setLocazioneGeografica(String locazioneGeografica) {
		this.locazioneGeografica = locazioneGeografica;
	}

	public String getProduttore() {
		return produttore;
	}

	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}

	public int getInDeposito() {
		return inDeposito;
	}

	public void setInDeposito(int inDeposito) {
		this.inDeposito = inDeposito;
	}

	public char getValuta() {
		return valuta;
	}

	public void setValuta(char valuta) {
		this.valuta = valuta;
	}

	public void stampaVino(){
		System.out.printf(DA_STAMPARE, nomeVino, annataVino, prezzoVino, locazioneGeografica, produttore, inDeposito);
	}
	
	public String toString(){
		StringBuffer stampare = new StringBuffer(String.format(DA_STAMPARE, nomeVino, annataVino, prezzoVino, locazioneGeografica, produttore, inDeposito));
		return stampare.toString();
	}
	
	public double guadagnoMultiValuta(char c){
		switch(c){
		case '$':
			if(c==valuta){
				System.out.println("Già in tale valuta!");
				break;
			}
			prezzoVino*=EU_TO_DO;
			System.out.println(prezzoVino);
			return prezzoVino;
		case '€':
			if(c==valuta){
				System.out.println("Già in tale valuta!");
				break;
			}
			prezzoVino/=EU_TO_DO;
			System.out.println(prezzoVino);
			return prezzoVino;
		}
		return -1;
	}
}
