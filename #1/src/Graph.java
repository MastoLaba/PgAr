import java.util.Vector;

/**
 * Classe che rappresenta il concetto di grafo.
 * @author Qwertyteam
 * @version 1.4
 * @since 1.0
 */

public class Graph implements Cloneable{
	private Nodo [] nodes;
	private MatriceAdiacenze adjacencyMatrix;
	private static final String MALFORMED_GRAPH = "Il grafo è malformato!";
	private static final String MULTIPLE_SOURCES = "Il grafo presenta nodi sorgente multipli.";
	private static final String MULTIPLE_DESTINATIONS = "Il grafo presenta nodi destinazione multipli.";
	private static final String NODE_NOT_LINKED = "Il grafo presenta nodi irraggiungibili.";
	private static final String SOURCE_AND_DESTINATION = "Il grafo presenta un nodo settato come sorgente e destinazione.";
	
	/**
	 * Costruttore della classe Graph. Vengono inizializzate le variabili necessarie 
	 * al corretto funzionamento dell'oggetto Graph e vengono riordinati i nodi per ID.
	 * @param nodes Nodi da aggiungere al grafo.
	 * @param matrix Matrice di adiacenza del grafo.
	 */
	
	public Graph(Vector<Nodo> nodes, MatriceAdiacenze matrix){
		this.nodes = new Nodo [nodes.size()];
		nodes.toArray(this.nodes);
		this.adjacencyMatrix = matrix;
		orderGraphByID(this.nodes);
	}
	
	/**
	 * Metodo che permette di restituire la matrice di adiacenza.
	 * @return Matrice di adiacenza.
	 */
	
	public MatriceAdiacenze getAdjacencyMatrix(){
		return adjacencyMatrix;
	}
	
	/**
	 * Metodo che permette di restituire i nodi del grafo.
	 * @return Nodi del grafo.
	 */
	
	public Nodo[] getNodes(){
		return nodes;
	}
	
	/**
	 * Metodo che permette di controllare l'integrità del grafo. Se ci sono 
	 * malformazioni di qualsiasi tipo l'utente ne viene informato attraverso
	 * interfaccia a linea di comando.
	 * @param graph Il grafo da analizzare
	 * @return Riscontro del controllo.
	 */
	
	public boolean checkIntegrity(Graph graph){
		boolean integritySource = false;
		boolean integrityDestination = false;
		for(Nodo nodo : nodes){
			if(nodo.isSourceNode() && nodo.isDestinationNode()){
				printStatus(SOURCE_AND_DESTINATION);
				return false;
			}
			if(nodo.isSourceNode() && !integritySource)
				integritySource = true;
			else if(nodo.isSourceNode() && integritySource){
				printStatus(MULTIPLE_SOURCES);
				return false;
			}
			if(nodo.isDestinationNode() && !integrityDestination)
				integrityDestination = true;
			else if(nodo.isDestinationNode() && integrityDestination){
				printStatus(MULTIPLE_DESTINATIONS);
				return false;
			}
			if(!adjacencyMatrix.isLinkedToGraph(nodo.getIDNode()) && !nodo.isDestinationNode()){
				printStatus(NODE_NOT_LINKED);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Metodo che permette di riordinare il grafo in ordine crescente di ID. Importante ricordare che
	 * il nodo con ID = 0 è sempre il nodo sorgente. L'algoritmo utilizzato non è nient'altro che il 
	 * Bubble-Sort.
	 * @param graph Il grafo da riordinare
	 */
	
	public static void orderGraphByID(Nodo [] graph){
		for(int index = 0; index < graph.length; index++) {
            boolean flag = false;
            for(int j = 0; j < graph.length-1; j++) {
                if(graph[j].getIDNode() >graph[j+1].getIDNode()) {
                    Nodo node = graph[j];
                    graph[j] = graph[j+1];
                    graph[j+1] = node;
                    flag=true; //Lo setto a true per indicare che é avvenuto almeno uno scambio
                }
            }
            
            /*
             * Se flag=false allora vuol dire che nell' ultima iterazione non ci sono stati scambi, quindi 
             * il metodo può terminare poiché l' array risulta essere ordinato
             */
            if(!flag) break;                      
        }
	}
	
	/**
	 * Ricerca, dato un array di nodi e l'ID del nodo, il nodo.
	 * @param idNode ID del nodo da ricercare
	 * @param Array di nodi su cui ricercare il nodo.
	 * @return Nodo se la ricerca è andata a buon fine, altrimenti null.
	 */
	
	public Nodo searchNodeByID(int idNode, Nodo [] nodes){
		for(Nodo node : nodes){
			if(node.getIDNode() == idNode)
				return node;
		}
		return null;
	}
	
	/**
	 * Stampa a schermo lo stato del Grafo. Permette di informare all'utente se 
	 * il grafo presenta malformazioni di qualche tipo oppure se è integro.
	 * @param statusGraph Stato del grafo.
	 */
	
	private static void printStatus(String statusGraph){
		System.out.println(statusGraph);
	}
	
	/**
	 * Metodo che permette di fa partire la scansione di eventuali cicli presenti
	 * all'interno del grafo. Funziona con grafi orientati.
	 * @return Presenza di eventuali cicli o meno.
	 */
	
	public boolean isCyclic(){
		boolean visited[] = new boolean[nodes.length];

		for(int i=0; i<nodes.length; i++){
			visited[i]=false;
		}
		
		for(int i=0; i<nodes.length; i++){
			if(!visited[i]){
				if(isCyclicUtil(i, visited, -1)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Metodo ricorsivo che controlla eventuali cicli presenti all'interno del grafo. Funziona
	 * con grafi orientati.
	 * @param currentV Nodo corrente
	 * @param visited Vettore che tiene traccia dei nodi visitati
	 * @param parentV Nodo padre da cui arriva il nodo corrente
	 * @return Riscontro della presenza di cicli.
	 */
	
	public boolean isCyclicUtil(int currentV, boolean visited[], int parentV){
		visited[currentV]=true;
		int accanto = adjacencyMatrix.areNodesLinked(parentV, currentV);
		while(accanto!=-1){
			if(!visited[accanto]){
				if(isCyclicUtil(accanto, visited, currentV)) return true;
				else return false;
			}
			if(accanto != parentV) return true;
		}
		return false;
	}
	
	/**
	 * Metodo che permette di clonare il grafo per evitare la modifica dei dati
	 * sul grafo originale.
	 */
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		final Graph clone;
        try {
            clone = (Graph) super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new RuntimeException("superclass messed up", ex);
        }
        clone.nodes = this.nodes.clone();
        return clone;
    }
}
