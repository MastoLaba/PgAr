public class Vino {
	private String name;
	private double prezzo;
	private int data;
	private String localita;
	private String produttore;
	private String valuta;
	private int cont;
	private double tassoRiferimento;
	
	//COSTRUTTORI
	public Vino(String name, double prezzo, int data, String località, String produttore, String valuta, int cont) {
		super();
		this.name = name;
		this.prezzo = prezzo;
		this.data = data;
		this.localita = località;
		this.produttore = produttore;
		this.valuta = valuta;
		this.cont = cont;
	}
	public Vino() {
		super();
		
	}
	
	//GETTESR AND SETTERS
	
	public String getName() {
		return name;
	}
	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public String getLocalità() {
		return localita;
	}
	public void setLocalità(String località) {
		this.localita = località;
	}
	public String getProduttore() {
		return produttore;
	}
	public void setProduttore(String produttore) {
		this.produttore = produttore;
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
	public int getCont() {
		return cont;
	}
	public void setCont(int cont) {
		this.cont = cont;
	}	
	public String toString(){
		 String tmp = "Nome:" +name+ " Costo:" +prezzo+valuta+ " Annata:" +data+ " località:" +localita+ " Produttore:" +produttore+ "Numero bottiglie:"+cont;
		 return tmp;
	}
	
}