package ieee.secondoes.enoteca;

public interface Interfaccia {

	public String stampaVino();
	
	public long numeroViniPerNomeVino(String nome);
	
	public long numeroViniPerNomeProduttore(String nome);
	
	public void possibileGuadagno(String valuta);
	
	public java.util.Collection<?> stampaFasciaVini(int annoInizio, int annoFine);
	
}
