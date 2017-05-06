package ieee.primoes.grafi;

import java.util.ArrayList;

public class Nodo {
	private String etichetta;
	private boolean primo=false, ultimo=false;
	
	private ArrayList<Legame> legami;
	
	public Nodo() {	legami = new ArrayList<>(); }
	
	public Nodo(String etichetta) {
		this.etichetta = etichetta; 
		legami = new ArrayList<>();
	}
	
	public String getEtichetta() { return etichetta; }
	
	public boolean isPrimo() { return primo; }
	
	public boolean isUltimo() { return ultimo; }
	
	public ArrayList<Legame> getLegami(){ return legami; }
	
	/**
	 * @return un ArrayList di Nodi vicini estratti dai Legami
	 */
	public ArrayList<Nodo> getVicini(){
		ArrayList<Nodo> vicini = new ArrayList<>();
		for(Legame l: legami)
			vicini.add(l.getNodo());
		return vicini;
	}
	
	public void setEtichetta(String etichetta) { this.etichetta = etichetta; }
	
	public void setPrimo() { primo = true; }
	
	public void setUltimo() { ultimo = true; }
	
	/**
	 * Aggiunge un legame a un Nodo con un costo
	 * @param vicino Nodo da legare
	 * @param costo costo da assegnare
	 */
	public void lega(Nodo vicino, int costo){
		for(int i=0; i<legami.size(); i++)
			if(legami.get(i).getNodo().equals(vicino))
				legami.remove(i); // se il legame esiste già, prima lo rimuove		
		legami.add(new Legame(vicino, costo));
	}
	
	/**
	 * Rimuove un legame con un Nodo
	 * @param etichetta l'etichetta del Nodo da rimuovere
	 */
	public void slega(String etichetta){
		for(int i=0; i<legami.size(); i++)
			if(legami.get(i).getNodo().getEtichetta().equals(etichetta))
				legami.remove(i); 		
	}
	
	public String toString(){
		String s = etichetta + ": ";
		for(Legame l: legami)
			s += l.getNodo().getEtichetta()+"("+l.getCosto()+") ";
		if(isPrimo()) s += "(primo)";
		if(isUltimo()) s+= "(ultimo)";
		return s;
	}
}

