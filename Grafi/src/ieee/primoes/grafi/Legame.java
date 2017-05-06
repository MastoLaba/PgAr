package ieee.primoes.grafi;

public class Legame {
	private Nodo nodo;
	private int costo;
	
	public Legame(Nodo vicino, int costo) {
		this.nodo = vicino;
		this.costo = costo;
	}
	
	public Nodo getNodo() { return nodo; }
	
	public int getCosto() { return costo; }
}
