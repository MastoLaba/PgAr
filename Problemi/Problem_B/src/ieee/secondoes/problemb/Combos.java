package ieee.secondoes.problemb;

import java.util.ArrayList;

public class Combos {
	public static final int[] monete = new int[]{0, 1, 2, 5, 10, 20, 50, 100, 200}; 
	
	private final int somma;	
	private ArrayList<Combo> combos;
	
	public Combos(int somma){
		this.somma = somma;
		combos = new ArrayList<>();
	}
	
	// La funzione iterativa impiega un po di tempo per calcolare il risultato
	// Ma aumenta esponenzialmente all'aumentare dei centesimi
	// Se vuoi sapere le combinazioni per 2 euro e 50 ci vuole moolta pazienza :/
	// E' comunque più efficiente della ricorsiva
	public void calcolaCombos(){
		Combo combo = new Combo(somma);
		
		boolean continuaCiclo = true;
		int monetaDaAggiungere = monete.length-1;
		while(continuaCiclo){			
			combo.addMoneta(monetaDaAggiungere);
			int totale = combo.totale();
			if(totale>somma){
				combo.rimuoviUltima();
				monetaDaAggiungere--;
			} else if(totale==somma){
				combos.add(combo.clone());
				monetaDaAggiungere = combo.getMoneta(combo.ultimaGrande())-1;
				combo.taglia();
			}

			if(monetaDaAggiungere==0) 
				continuaCiclo=false;
		}
	}
	
	
	// La ricorsione richiede troppe risorse e non riesce a calcolare oltre i 50 centesimi
	// Probabilmente apre troppe funzioni, va in StackOverflow
	// Provare per credere!
	public void ricorsione(){
		ricorsione(new Combo(somma), monete.length-1);
	}
	
	private void ricorsione(Combo combo, int monetaDaAggiungere){
		if(monetaDaAggiungere==0)
			return;
		
		combo.addMoneta(monetaDaAggiungere);
		int totale = combo.totale(); 
		if(totale > somma){
			combo.rimuoviUltima();
			ricorsione(combo, monetaDaAggiungere-1);
		} else if(totale < somma)
			ricorsione(combo, monetaDaAggiungere);
		else {
			combos.add(combo.clone());
			int prossima = combo.getMoneta(combo.ultimaGrande())-1;
			combo.taglia();
			ricorsione(combo, prossima);
		} 
	}
	
	public String toString(){
		String ritorno = "";
		for(Combo b: combos)
			ritorno += b + "\n";
		return ritorno;
	}

}
