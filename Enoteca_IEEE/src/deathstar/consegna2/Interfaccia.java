package deathstar.consegna2;

import java.util.Collection;

public interface Interfaccia {

	public String stampaVino();
	
	public long numeroViniPerNomeVino(String nome);
	
	public long numeroViniPerNomeProduttore(String nome);
	
	public void possibileGuadagno(String valuta);
	
	public Collection<?> stampaFasciaVini(int annoInizio, int annoFine);
	
}
