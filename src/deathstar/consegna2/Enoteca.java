package deathstar.consegna2;

import java.util.ArrayList;

public class Enoteca implements Interfaccia {

	private ArrayList<Vino> vini;
	
	public Enoteca() {
		this.vini = new ArrayList<>();
	}
	
	public Enoteca(ArrayList<Vino> vini) {
		this.vini = vini;
	}
	
	public void setVini(ArrayList<Vino> vini) {
		this.vini = vini;
	}
	
	public String stampaVino() {
		StringBuffer buffer = new StringBuffer();
		for (Vino v : vini) {
			buffer.append(v.toString());
			buffer.append("\n\n");
		}
		return buffer.toString();
	}
	
	public long numeroViniPerNomeVino(String nome) {
		long num = 0;
		for (Vino v : vini) {
			if (v.isCalled(nome)) {
				num++;
			}
		}
		return num;
	}
	
	public long numeroViniPerNomeProduttore(String nome) {
		long num = 0;
		for (Vino v : vini) {
			if (v.isProdottoDa(nome)) {
				num++;
			}
		}
		return num;
	}
	
	public void possibileGuadagno(String valuta) {
		Valuta valutaRichiesta = Valuta.getValuta(valuta);
		double guadagno = 0.0;
		if (valutaRichiesta == Valuta.DOLLARI) {
			for (Vino v : vini) {
				Prezzo tmpPrezzo = v.getPrezzo();
				if (tmpPrezzo.isDollari()) {
					guadagno += tmpPrezzo.getValore();
				}
				else {
					guadagno += tmpPrezzo.toDollari().getValore();
				}
			}
			System.out.println("Guadagno possibile: $ " + guadagno);
		}
		else if (valutaRichiesta == Valuta.EURO) {
			for (Vino v : vini) {
				Prezzo tmpPrezzo = v.getPrezzo();
				if (tmpPrezzo.isEuro()) {
					guadagno += tmpPrezzo.getValore();
				}
				else {
					guadagno += tmpPrezzo.toEuro().getValore();
				}
			}
			System.out.println("Guadagno possibile: € " + guadagno);
		}
		else {
			System.out.println("ERRORE: Valuta sconosciuta");
		}
	}
	
	public ArrayList<Vino> stampaFasciaVini(int annoInizio, int annoFine) {
		ArrayList<Vino> viniFascia = new ArrayList<>();
		for (Vino v : vini) {
			if (v.getAnnata() >= annoInizio && v.getAnnata() <= annoFine) {
				viniFascia.add(v);
			}
		}
		return viniFascia;
	}
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("ERRORE: Fornire file");
		}
		else {
			try {
				Enoteca enoteca = new Enoteca(ParserXMLEnoteca.XMLUtil(args[0]));
				System.out.println(enoteca.stampaVino());
				System.out.println("--------------X---------------");
				System.out.println("Numero vini \"Bolloc\": " + enoteca.numeroViniPerNomeVino("Bolloc"));
				System.out.println("--------------X---------------");
				System.out.println("Numero vini prodotti da \"Il Francesino\": " + enoteca.numeroViniPerNomeProduttore("Il Francesino"));
				System.out.println("--------------X---------------");
				enoteca.possibileGuadagno("$");
				enoteca.possibileGuadagno("€");
				System.out.println("--------------X---------------");
				ArrayList<Vino> vini = enoteca.stampaFasciaVini(100, 170);
				for (Vino v : vini) {
					System.out.println(v);
				}
			}
			catch (Exception e) {
				System.out.println("ERROR: ");
				e.printStackTrace();
			}
		}
	}
	
}
