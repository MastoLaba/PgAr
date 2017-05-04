package deathstar.consegna1;

/**
 * Rappresenta un arco che pu� essere utilizzato per connettere due nodi in un grafo.
 * Un arco collega solo due nodi, i metodi di questa classe sono adatti a grafi non orientati.
 * 
 */
public class Arc {
	//Costanti
	private final static int PRIMO_NODO = 0;
	private final static int SECONDO_NODO = 1;
	private final static int MAX_ESTREMI = 2;

	private final static String ARC = "--";
	private final static String ARROW = "-->";
	private final static String NEW_LINE = "\n";
	
	
	//Attributi
	private int id1; //Conservano l'id dei nodi che connettono
	private int id2; //non tengono conto dell'ordine in un grafo non orientato
	private int weight; //consideriamo pesi sempre positivi
	private Node[] linkedNodes = new Node[MAX_ESTREMI];
	
	//Costruttori
	/**
	 * Costruisce un arco pesato che connette due nodi.
	 * Ogni nodo � identificato univocamente dagli id dei due nodi che connette.
	 * (pre-condizione: verificare con il metodo sameArc che l'arco non esista gi� 
	 * es. nel verso opposto nodo2 connesso a nodo1)
	 * 
	 * @param nodo1 Nodo da cui parte l'arco (intercambiabile considerando grafi non orientati).
	 * @param nodo2 Nodo dove termina l'arco (non � trattato il caso di cappi).
	 * @param _weight Peso dell'arco.
	 */
	public Arc(Node nodo1, Node nodo2, int _weight){
		id1 = nodo1.getId();
		id2 = nodo2.getId();
		weight = _weight;
		linkedNodes[PRIMO_NODO] = nodo1;
		linkedNodes[SECONDO_NODO] = nodo2;		
	}
	
	//getters
	/**
	 * Restituisce un Array contenete gli id dei nodi che questo arco collega e che quindi costituisce l'id dell'arco.
	 * 
	 *  @return Array di id dei nodi connessi.
	 */
	public int[] getId(){
		return new int[] {id1,id2};
	}
	
	/**
	 * Restituisce il peso di questo arco.
	 *
	 * @return Peso dell'arco.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Restituisce il riferimento al nodo collegato al primo estremo dell'arco.
	 * 
	 * @return Primo nodo dell'arco.
	 */
	public Node getNode1(){
		return linkedNodes[PRIMO_NODO];
	}
	
	/**
	 * Restituisce il riferimento al nodo collegato al secondo estremo dell'arco.
	 * 
	 * @return Secondo nodo dell'arco.
	 */
	public Node getNode2(){
		return linkedNodes[SECONDO_NODO];
	}
	
	
	/**
	 * Verifica che questo arco e l'arco fornito come parametro siano o meno lo stesso arco,
	 * quindi connettono la stessa coppia di nodi.
	 * Restituisce true se i due archi collegano una coppia di nodi con stessa label, false altrimenti.
	 * 
	 * @param otherArc Arco da confrontare.
	 * @return Esito del quesito.
	 */
	public boolean sameArc(Arc otherArc){
		return (linkedNodes[PRIMO_NODO].getLabel().equals(otherArc.linkedNodes[PRIMO_NODO].getLabel())) &&
				(linkedNodes[SECONDO_NODO].getLabel().equals(otherArc.linkedNodes[SECONDO_NODO].getLabel())) ||
				(linkedNodes[PRIMO_NODO].getLabel().equals(otherArc.linkedNodes[SECONDO_NODO].getLabel())) &&
				(linkedNodes[SECONDO_NODO].getLabel().equals(otherArc.linkedNodes[PRIMO_NODO].getLabel()));
				
	}
	
	/**
	 * Verifica che questo arco e l'arco fornito come parametro siano o meno lo stesso arco,
	 * quindi connettono la stessa coppia di nodi.
	 * Restituisce true se i due archi collegano una coppia di nodi con stesso id, false altrimenti.
	 * 
	 * @param otherArc Arco da confrontare.
	 * @return Esito del quesito.
	 */
	public boolean equals(Arc otherArc){
		return (linkedNodes[PRIMO_NODO].getId() == otherArc.linkedNodes[PRIMO_NODO].getId()) &&
				(linkedNodes[SECONDO_NODO].getId() == otherArc.linkedNodes[SECONDO_NODO].getId()) ||
				(linkedNodes[PRIMO_NODO].getId() == otherArc.linkedNodes[SECONDO_NODO].getId()) &&
				(linkedNodes[SECONDO_NODO].getId() == otherArc.linkedNodes[PRIMO_NODO].getId());
				
	}
	
	@Override
	public String toString()
	{
		StringBuffer description = new StringBuffer();
		
		description.append(linkedNodes[PRIMO_NODO].getLabel());
		description.append(ARC);
		description.append(getWeight());
		description.append(ARROW);
		description.append(linkedNodes[SECONDO_NODO].getLabel());
		description.append(NEW_LINE);
		
		return description.toString();
	}
}
