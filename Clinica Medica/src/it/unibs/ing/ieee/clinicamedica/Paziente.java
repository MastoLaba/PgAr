package it.unibs.ing.ieee.clinicamedica;

import java.util.Random;

public class Paziente extends Persona implements Comparable<Paziente>{

	private Allarme priorita;
	private int numeroAppuntamenti = 0;
	
	public Paziente(String _nome, String _cognome) {
		super(_nome, _cognome);
		this.setId(this.hashCode());
		
		Random rand = new Random();
		int i = rand.nextInt(4) + 1;
		switch(i) {
			case 1:
				priorita = Allarme.ROSSO;
				break;
			case 2:
				priorita = Allarme.GIALLO;
				break;
			case 3:
				priorita = Allarme.NERO;
				break;
			case 4:
				priorita = Allarme.MARRONE;
				break;
		}
		
	}

	public Allarme getPriorita() {
		return priorita;
	}

	public void setPriorita(Allarme priorita) {
		this.priorita = priorita;
	}

	public int getNumeroAppuntamenti() {
		return numeroAppuntamenti;
	}

	public void setNumeroAppuntamenti(int numeroAppuntamenti) {
		this.numeroAppuntamenti = numeroAppuntamenti;
	}
	
	public void incrementaNumeroAppuntamenti() {
		numeroAppuntamenti++;
	}
	
	public String toString(){
		return String.format("nome: %s\ncognome: %s\nid: %d\npriorita': %s\nnumero di appuntamenti: %d\n",this.getNome(),this.getCognome(),this.getId(),priorita,numeroAppuntamenti);
	}

	@Override
	public int compareTo(Paziente o) {
		return -Integer.compare(this.numeroAppuntamenti, o.getNumeroAppuntamenti());
	}

}
