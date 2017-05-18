package enoteca;

import java.util.ArrayList;
import java.util.Scanner;

public class ListaVini {
	private static final String ANNATA_INIZIO = "Inserire l'inizio della fascia di annate";
	private static final String ANNATA_FINE = "Inserire la fine della fascia di annate";
	private static final String ALTRA_VALUTA = "Inserire l'altra valuta";
	private static final String STAMPA_VINO = "Nome: %s\tProduttore: %s\tBottiglie rimaste: %d";
	private static final String STAMPA_VINO_D = "Nome: %s\tBottiglie rimaste: %d";
	private static final String ERRORE_VALUTA = "Valuta inserita errata!";
	private static final Scanner input = new Scanner(System.in);;
	
	ArrayList<Vino> lista;
	
	public ListaVini(ArrayList<Vino> listaVini){
		lista = listaVini;
	}
	
	public ListaVini(){
		
	}
	
	public ArrayList<Vino> ordineAlfabetico(){
		for(int i=0; i<lista.size(); i++){
			for(int j=0; j<lista.size(); j++){
					if(lista.get(i).getNomeVino().compareToIgnoreCase(lista.get(j).getNomeVino())==1){
						Vino temp = lista.get(i);
						lista.set(i, lista.get(j));
						lista.set(j, temp);
					}
			}
		}
		return lista;
	}
		
	public void ordineProduttore(){
		for(int i=0; i<lista.size(); i++){
			for(int j=0; j<lista.size(); j++){
				if(lista.get(i).getProduttore().compareToIgnoreCase(lista.get(j).getProduttore())==1){
					Vino temp = lista.get(i);
					lista.set(i, lista.get(j));
					lista.set(j, temp);
				}
			}
			System.out.printf(STAMPA_VINO, lista.get(i).getNomeVino(), lista.get(i).getProduttore(), lista.get(i).getInDeposito());
		}
	}
	
	public ArrayList<Vino> ordineRegione(){
		for(int i=0; i<lista.size(); i++){
			for(int j=0; j<lista.size(); j++){
					if(lista.get(i).getLocazioneGeografica().compareToIgnoreCase(lista.get(j).getLocazioneGeografica())==1){
						Vino temp = lista.get(i);
						lista.set(i, lista.get(j));
						lista.set(j, temp);
					}
			}
		}
		return lista;
	}
	
	public ArrayList<Vino> ordineAnnata(){
		for(int i=0; i<lista.size(); i++){
			for(int j=0; j<lista.size(); j++){
				if(lista.get(i).getAnnataVino()>lista.get(j).getAnnataVino()){
					Vino temp = lista.get(i);
					lista.set(i, lista.get(j));
					lista.set(j, temp);
				}
			}
		}
		return lista;
	}
	
	public ArrayList<Vino> ordinePrezzo(){
		for(int i=0; i<lista.size(); i++){
			for(int j=0; j<lista.size(); j++){
				if(lista.get(i).getPrezzoVino()>lista.get(j).getPrezzoVino()){
					Vino temp = lista.get(i);
					lista.set(i, lista.get(j));
					lista.set(j, temp);
				}
			}
		}
		return lista;
	}
	
	public void ordineDeposito(){
		for(int i=0; i<lista.size(); i++){
			for(int j=0; j<lista.size(); j++){
				if(lista.get(i).getInDeposito()>lista.get(j).getInDeposito()){
					Vino temp = lista.get(i);
					lista.set(i, lista.get(j));
					lista.set(j, temp);
				}
			}
			System.out.printf(STAMPA_VINO_D, lista.get(i).getNomeVino(), lista.get(i).getInDeposito());
		}
	}
	
	public ListaVini compresiAnnata(){
		ListaVini compresi = new ListaVini();
		System.out.println(ANNATA_INIZIO);
		int annataInizio = input.nextInt();
		System.out.println(ANNATA_FINE);
		int annataFine = input.nextInt();
		for(int i=0; i<lista.size(); i++){
			if(lista.get(i).getAnnataVino()>=annataInizio&&lista.get(i).getAnnataVino()<=annataFine){
				compresi.lista.add(lista.get(i));
			}
		}
		return compresi;
	}
	public int valutaIntero(int k){
		if(k==1 || k==2 || k==3) return 1;
		if(k==4 || k==5 || k==6) return -1;
		else return 0;
	}
	
	public void stampaVini(){
		for(int i=0; i<lista.size(); i++){
			Vino vino = lista.get(i);
			vino.stampaVino();
		}
	}
	
	public void multiValuta(){
		char c;
		do{
			System.out.println(ALTRA_VALUTA);
			c = input.next().charAt(0);
			if(lista.get(0).guadagnoMultiValuta(c)==-1) System.out.println(ERRORE_VALUTA);
		}while(lista.get(0).guadagnoMultiValuta(c)==-1);
		for(int i=0; i<lista.size(); i++){
			lista.get(i).guadagnoMultiValuta(c);
		}
	}
	
	public void stampatutto(){
		for(int i=0; i<lista.size();i++){
			System.out.println(lista.get(i).toString());
		}
	}

}
