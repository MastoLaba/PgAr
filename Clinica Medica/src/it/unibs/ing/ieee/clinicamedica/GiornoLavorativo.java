package it.unibs.ing.ieee.clinicamedica;

public class GiornoLavorativo {
	GiorniSettimana giorno;
	private boolean lavoro = true;
	private Ora[] orario  = new Ora[4];
	
	public GiornoLavorativo(boolean lavoro, Ora[] orario) {
		this.lavoro = lavoro;
		this.orario = orario;
	}
	
	public GiornoLavorativo(boolean lavoro, Ora iMatt, Ora fMatt, Ora iPom, Ora fPom, GiorniSettimana giorno) {
		this.lavoro = lavoro;
		orario[0] = iMatt;
		orario[1] = fMatt;
		orario[2] = iPom;
		orario[3] = fPom;
		this.giorno = giorno;
	}

	public boolean isLavoro() {
		return lavoro;
	}

	public GiorniSettimana getGiorno() {
		return giorno;
	}

	public void setGiorno(GiorniSettimana giorno) {
		this.giorno = giorno;
	}

	public void setLavoro(boolean lavoro) {
		this.lavoro = lavoro;
	}

	public Ora[] getOrario() {
		return orario;
	}

	public void setOrario(Ora[] orario) {
		this.orario = orario;
	}
	
	public void setOrario(Ora iMatt, Ora fMatt, Ora iPom, Ora fPom){
		orario[0] = iMatt;
		orario[1] = fMatt;
		orario[2] = iPom;
		orario[3] = fPom;
	}
	
	public String toString(){
		return String.format("%s%nMattina:\t  %s : %s \nPomeriggio/Sera:  %s : %s%n", giorno,orario[0],orario[1],orario[2],orario[3]);
	}

}
