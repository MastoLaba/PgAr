package dijkstra_kruskal;

/**
 * struttura di un arco non orientato
 * @author mauro
 *
 */
public class Edge {

	private String nameEdge;
	private Node nodeA;
	private Node nodeB;
	private int weight;
	
	private static final String UNDERSCORE="_";
	
	/**
	 * il nome di Edge è formato dal nome del primo nodo e quello del secondo
	 * separati da un underscore
	 * @param nodeA il primo nodo
	 * @param nodeB il secondo nodo
	 * @param weight il peso del Edge
	 * @author mauro
	 */
	public Edge(Node nodeA, Node nodeB, int weight){
		nameEdge = nodeA.getNameNode() + UNDERSCORE + nodeB.getNameNode();
		this.nodeA=nodeA;
		this.nodeB=nodeB;
		this.weight=weight;
	}

	/**
	 * getter
	 * @return il nome dell'Edge
	 * @author mauro
	 */
	public String getNameEdge() {
		return nameEdge;
	}

	/**
	 * setter
	 * @param nameEdge il nuovo nome dell'Edge
	 * @author mauro
	 */
	public void setNameEdge(String nameEdge) {
		this.nameEdge = nameEdge;
	}

	/**
	 * getter
	 * @return torna il primo nodo
	 * @author mauro
	 */
	public Node getNodeA() {
		return nodeA;
	}

	/**
	 * setter
	 * @param nodeA il nuovo valore del primo nodo
	 * @author mauro
	 */
	public void setNodeA(Node nodeA) {
		this.nodeA = nodeA;
	}

	/**
	 * getter
	 * @return il secondo nodo
	 * @author mauro
	 */
	public Node getNodeB() {
		return nodeB;
	}

	/**
	 * setter
	 * @param nodeB il nuovo valore del secondo nodo
	 * @author mauro
	 */
	public void setNodeB(Node nodeB) {
		this.nodeB = nodeB;
	}

	/**
	 * getter
	 * @return il peso dell'Edge
	 * @author mauro
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * setter
	 * @param weight nuovo peso dell'Edge
	 * @author mauro
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	

	/**
	 * cambia la variabile visited a entrambi i nodi
	 */
	public void visited(){
		nodeA.visitedTrue();
		nodeB.visitedTrue();
	}
	
	
	//LO USERÒ DOPO PER CAMBIARE NODO
	/**
	 * si identifica il nodo da cui si parte e si ritorna l'altro.
	 * al peso del percorso viene aggiunto il peso dell'Edge
	 * @param startNode il nodo iniziale
	 * @param weightFinal  il peso del percorso già fatto
	 * @return il nodo a cui sono arrivato
	 * @author mauro
	 */
	public Node visited(Node startNode, int weightFinal){
		weightFinal += weight;
		if (startNode.equals(nodeA)) return nodeB;
		else return nodeA;
	}

	
	//UTILE PER IL METODO ISCYCLING
	/**
	 * @return true se entrambi i nodi sono stati visitati
	 * false se almeno uno dei due non è stato visitato
	 * @author mauro
	 */
	public boolean bothVisited(){
		if (nodeA.getVisited() && nodeB.getVisited()) return true;
		else return false;
	}
	
	
	public Node otherNode(Node node){
		Node ritorno;
		if (node.equals(nodeA)) ritorno= nodeB;
		else ritorno= nodeA;
		return ritorno;
	}

	public boolean contains(Node node){
		if (node.equals(nodeA) || node.equals(nodeB)) return true;
		else return false;
				
	}
	
	
}
