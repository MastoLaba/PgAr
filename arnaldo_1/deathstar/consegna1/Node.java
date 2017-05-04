package deathstar.consegna1;

import java.util.Vector;

/** Classe utilizzata per la rappresentazione di un Nodo in un grafo
 * 
 * @author Alessandro Ghirardi Luna
 */
public class Node {
	
	//Costanti
	private final static int ZERO = 0;
	private final static int ERROR = -1;
	
	private final static String LABEL = "Node: ";
	private final static String NEW_LINE = "\n";
	
	//Attributi
	private static int lastsAssignedId = ZERO; //Mi permette di ottenere id univoci da 1 a (2^31 - 1)
	private int id; 						//Cambiato il tipo da String a int
	private String label;
	private Vector<Arc> linkedNodes;		//Vettore di archi verso i nodi direttamente collegati
	private boolean isStartNode = false;	//Un nodo non ha la propriet� intrinseca di essere il punto iniziale o finale di un cammino in un grafo quindi
	private boolean isEndNode = false;		//questi attributi saranno di default impostati a false e potranno essere modificati con dei metodi pubblici.
	
	//Costruttori
	/**
	 * Costruisce un nodo dopo che sono stati forniti come parametri, 
	 * un etichetta ed un Vector di archi verso i nodi al quale questo nodo deve essere direttamente connesso.
	 * Inoltre al nodo � assegnato un numero identificativo. (id precedente istanza sommato di 1)
	 * 
	 * @param _label Etihetta assegnata al nodo.
	 * @param _linkedNodes Vettore di nodi addiacenti.
	 */
	public Node(String _label, Vector<Arc> _linkedNodes){
		id = lastsAssignedId++;
		label = _label;
		linkedNodes = _linkedNodes;
	}
	
	/**
	 * Costruisce un node al quale viene assegnata una label vuota ed un Vector di archi che punta a null.
	 * Al nodo � assegnato un numero identificativo. (id precedente istanza sommato di 1)
	 */
	public Node(){
		this("", new Vector<Arc>());
	}
	
	//getters
	/**
	 * Restituisce il numero identificatico di questo nodo.
	 * 
	 * @return id del nodo
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Restituisce l'etichetta di questo nodo.
	 * 
	 * @return label del nodo
	 */
	public String getLabel(){
		return label;
	}
	
	/**
	 * Restituisce il vettore di tutti i nodi verso i quali questo nodo ha un collegamento.
	 * Se questo nodo non � collegato a nessun altro nodo restituisce null.
	 * 
	 * @return Vettore di nodi raggiungibili.
	 */
	public Vector<Node> getLinked(){
		Vector<Node> linked = new Vector<>();
		Node toAdd;
		
		for(int i = 0; i < linkedNodes.size(); i++){
			toAdd = linkedNodes.get(i).getNode2();
			linked.add(toAdd);
		}
		
		return linked;
	}
	
	/**
	 * Restituisce una stringa che rappresenta il peso dell'arco che connette questo nodo al nodo con l'etichetta label.
	 * (Pre-condizione: i due nodi devono avere effettivamente un arco in comune,
	 * verificare con hasArc(label)).
	 * 
	 * @param label Etichetta nodo destinazione.
	 * @return Peso dell'arco tra i due nodi.
	 */
	public String getWeightArc(String label){			//Questo metodo considera che l'arco inizi da this e termini al nodo indicato da label
		String weight = "";										//Funziona perch� ogni nodo ha una sua copia dell'arco che lo connette con un altro nodo
		String toCheck;										//Sara usando la classe grafo che verificheremo di non avere due volte lo stesso arco
		
		for(int i = 0; i < linkedNodes.size(); i++){			//utilizzzando i metodi di arco
			toCheck = linkedNodes.get(i).getNode2().getLabel();
			
			if(toCheck.equals(label))
				weight = Integer.toString(linkedNodes.get(i).getWeight());
		}															
		
		return weight;
	}
	
	/**
	 * Restituisce il peso dell'arco, un intero, che connette questo nodo al nodo con l'etichetta label.
	 * (Pre-condizione: i due nodi devono avere effettivamente un arco in comune,
	 * verificare con hasArc(label)).
	 * 
	 * @param label Etichetta nodo destinazione.
	 * @return Peso dell'arco tra i due nodi.
	 */
	public int getWeightArc(int id){			//Questo metodo considera che l'arco inizi da this e termini al nodo indicato da label
		int weight = ERROR;										
		int toCheck;										
		
		for(int i = 0; i < linkedNodes.size(); i++){			
			toCheck = linkedNodes.get(i).getNode2().getId();
			
			if(toCheck == id)
				weight = linkedNodes.get(i).getWeight();
		}															
		
		return weight;
	}
	
	
	//booleani
	/**
	 * Verifica che un nodo sia considerato o meno il nodo iniziale di un cammino all'interno di un grafo.
	 * Restituisce valore booleano: true se la risposta � affermativa, false altrimenti.
	 * 
	 * @return Esito del quesito.
	 */
	public boolean isStart(){
		return isStartNode;
	}
	

	/**
	 * Verifica che un nodo sia considerato o meno il nodo finale di un cammino all'interno di un grafo.
	 * Restituisce valore booleano: true se la risposta � affermativa, false altrimenti.
	 * 
	 * @return Esito del quesito.
	 */
	public boolean isEnd(){
		return isEndNode;
	}
	
	/**
	 * Verifica che il vettore di archi di questo nodo non sia vuoto, quindi che questo nodo sia
	 * collegato mediante un arco con un altro nodo.
	 * 
	 * @return Esito del quesito.
	 */
	public boolean hasArcs(){
		return !linkedNodes.isEmpty(); //attenzione alla negazione prima dell'invocazione
	}
	
	/**
	 * Verifica che questo nodo condivida un arco con il nodo che verr� identificato dalla label all'interno
	 * del vettore degli archi di questo nodo.
	 * 
	 * @param label Label del nodo da trovare.
	 * @return Esito della ricerca.
	 */
	public boolean hasArc(String label){
		String toCheck = "";
		boolean find = false;
		
		for(int i = 0; i < linkedNodes.size() && !find; i++){
			toCheck = linkedNodes.get(i).getNode2().getLabel();
			
			if(toCheck.equals(label))
				find = true;
		}
		
		return find;
	}
	
	/**
	 * Verifica che questo nodo condivida un arco con il nodo che verr� identificato dal suo id all'interno
	 * del vettore degli archi di questo nodo.
	 * 
	 * @param otherId Id del nodo da trovare.
	 * @return Esito della ricerca.
	 */
	public boolean hasArc(int otherId){
		int toCheck = ERROR;
		boolean find = false;
		
		for(int i = 0; i < linkedNodes.size() && !find; i++){
			toCheck = linkedNodes.get(i).getNode2().getId();
			
			if(toCheck == otherId)
				find = true;
		}
		
		return find;
	}
	
	//setters
	/**
	 * Imposta questo nodo come nodo iniziale di un albero dei cammini minimi.
	 * (Pre-condizione: non deve essere gi� presente un altro nodo iniziale).
	 */
	public void setAsStart(){
		isStartNode = true;
	}
	
	/**
	 * Imposta questo nodo come nodo finale di un albero dei cammini minimi.
	 * (Pre-condizione: non deve essere gi� presente un altro nodo finale).
	 */
	public void setAsEnd(){
		isEndNode = true;
	}
	
	/**
	 * Modifica l'etichetta di questo nodo cambiando con il parametro fornito al metodo.
	 * 
	 * @param label Nuova etichetta di questo nodo.
	 */
	public void setLabel(String _label){
		label = _label;
	}
	
	/**
	 * Permette di aggiungere un arco al Vector di archi di questo nodo fornendo come parametri
	 * il nodo da connettere e il peso dell'arco.
	 * 
	 * @param toLink Nodo da connettere.
	 * @param weight Peso del nuovo arco.
	 */
	public void addArc(Node toLink, String weight){ //Attenzione: fino a qui � necessario che weight sia una stringa
		int myWeight = Integer.parseInt(weight);
		Arc myArc = new Arc(this, toLink, myWeight);
		
		linkedNodes.addElement(myArc);
	}
	/**
	 * Permette di aggiungere un arco al Vector di archi di questo nodo fornendo come parametri
	 * il nodo da connettere e il peso dell'arco.
	 * 
	 * @param toLink Nodo da connettere.
	 * @param weight Peso del nuovo arco.
	 */
	public void addArc(Node toLink, int weight){ //Attenzione: fino a qui � necessario che weight sia una stringa
		Arc myArc = new Arc(this, toLink, weight);
		
		linkedNodes.addElement(myArc);
	}
	
	/**
	 * Permette di rimuovere l'arco, dal Vector di archi di questo nodo, specificando come parametro la label del nodo
	 * situato al secondo estremo dell'arco da rimuovere.
	 * 
	 * (pre-condizione: Bisogna aver prima verificato che tale arco esista con il metodo hasArc(toRemove))
	 * 
	 * @param toRemove Label del nodo verso il quale rimuovere l'arco.
	 */
	public void removeArc(String toRemove){
		String ispezionato = "";
		
		for(int i = 0; i < linkedNodes.size(); i++){
			ispezionato = linkedNodes.get(i).getNode2().getLabel();
			
			if(ispezionato.equals(toRemove))
				linkedNodes.remove(i);
		}
		
		return;
	}
		
	/**
	 * Permette di rimuovere l'arco, dal Vector di archi di questo nodo, specificando come parametro l'id del nodo
	 * situato al secondo estremo dell'arco da rimuovere.
	 * 
	 * (pre-condizione: Bisogna aver prima verificato che tale arco esista con il metodo hasArc(toRemove))
	 * 
	 * @param toRemove Id del nodo verso il quale rimuovere l'arco.
	 */
	public void removeArc(int toRemove){
		int ispezionato = -1;
		
		for(int i = 0; i < linkedNodes.size(); i++){
			ispezionato = linkedNodes.get(i).getNode2().getId();
			
			if(ispezionato == toRemove)
				linkedNodes.remove(i);
		}
		
		return;
	}
	
	/**
	 * Restituisce una Stringa che rappresenta un nodo e tutti i suoi archi
	 */
	@Override
	public String toString(){
		StringBuffer description = new StringBuffer(LABEL);
	
		description.append(label);
		description.append(isStart() ? " - START" : "");
		description.append(isEnd() ? " - END" : "");
		description.append(NEW_LINE);
		
		for(int i = 0; i < linkedNodes.size(); i++) {
			description.append(linkedNodes.get(i).toString());
		}
		description.append(NEW_LINE);
		
		return description.toString();
	}
	
}
