package deathstar.consegna1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Classe statica che implementa l'algorimto di Kruskal.
 * Vedere la descrizione dei metodi per maggiori informazioni.
 */

public final class KruskalImplementer {
	
	private static final int STARTING_NODE_INDEX = -1;
	private static final int FIRST_NODE = 0;
	private static final int SECOND_NODE = 1;

	private static final String ERROR_DIRECTED_GRAPH = "Errore:\nIl metodo invocato non è applicabile a grafi orientati.\nPuoi rendere il grafo non orientato con il metodo forceUndirected().";
	
	// Metodi pubblici:
	
	/**
	 * Metodo che implementa l'algoritmo di Kruskal per la ricerca del minimo sotto-albero di un grafo in ingresso.
	 * Da una lista di archi ordinata per peso crescente, seleziona solo quelli che non creano un ciclo all'interno 
	 * del nuovo grafo.
	 * Non modifica il grafo iniziale passato come parametro, ne crea una copia.
	 * 
	 * @param Grafo sotto forma di lista di adiacenze.
	 * @return Albero di supporto minimo, come nuovo oggetto Graph.
	 */
	public static Graph getMinimumSpanningTree(Graph _graph) {
		// Controllo che il grafo sia NON orientato
		if (_graph.checkDirected()) {
			System.out.println(ERROR_DIRECTED_GRAPH);
			return null;
		}
		// Lista ordinata di archi
		SortedSet<Arc> graph_arcs = KruskalImplementer.getArcsFromNodes(_graph.getNodes());
		// Albero vuoto
		Graph minimum_tree = _graph.cloneOnlyNodes();
		// Lista di adiacenze vuota dell'albero
		HashMap<Integer, Vector<Integer>> tree_adj_list = KruskalImplementer.getEmptyAdjacencyList(_graph.getNodes());
		
		// Numero di archi aggiunti
		int num_added_arcs = 0;
		final int SUP_LIMIT_ARCS = minimum_tree.getNumNodes(); // Numero massimo (+ 1) di archi aggiungibili
		
		// Ciclo
		Iterator<Arc> iterator = graph_arcs.iterator();
		while (num_added_arcs < SUP_LIMIT_ARCS && iterator.hasNext()) {
			Arc arc = iterator.next();
			int arc_new_id1 = arc.getId()[FIRST_NODE];
			int arc_new_id2 = arc.getId()[SECOND_NODE];
			// Aggiungo il nuovo link alla lista di adiacenze, sulla quale è facile verificare i cicli.
			KruskalImplementer.addLink(arc_new_id1, arc_new_id2, tree_adj_list);
			if (!KruskalImplementer.hasCycle(tree_adj_list)) {
				// Non ottengo cicli, quindi aggiungo il link al grafo.
				minimum_tree.linkNodes(arc.getNode1().getLabel(), arc.getNode2().getLabel(), arc.getWeight());
				num_added_arcs++;
			} else {
				// Ho ottentuo un ciclo, rimuovo l'ultimo link inserito.
				KruskalImplementer.undoLastLink(arc_new_id1, arc_new_id2, tree_adj_list);
			}
		}
		minimum_tree.forceUndirected();
		return minimum_tree;
	}
	
	// Metodi privati:
	
	/**
	 * Dato in ingresso un grafo sotto forma di lista di adiacenze (Vector di oggetti Node),
	 * restituisce un SortedSet di oggetti Arc ordinato in base al peso degli archi.
	 * Di fatto "converte" un grafo visto come insieme di nodi in un grafo visto come insieme ordinato di archi.
	 * 
	 * @param Vettore dei nodi del grafo.
	 * @return Vettore degli archi del grafo.
	 */
	private static SortedSet<Arc> getArcsFromNodes(Vector<Node> _nodes) {
		ArcComparator comparator = new ArcComparator();
		SortedSet<Arc> arcs = new TreeSet<Arc>(comparator);
		
		for (Node node : _nodes) {
			for (Node linked_node : node.getLinked()) {
				int weight = node.getWeightArc(linked_node.getId());
				Arc new_arc = new Arc(node, linked_node, weight);
				arcs.add(new_arc); // Il fatto che non si creino duplicati è garantito dal comparatore.
			}
		}
		return arcs;
	}

	/**
	 * Restituisce una HashMap di Vector di Integer indicizzati con un Integer,
	 * corrispondente alla lista di adiacenze del grafo formato dai nodi passati come parametro, 
	 * ma senza collegamenti.
	 * Serve solo per inizializzare la lista per l'algortimo di Kruskal.
	 * 
	 * @param Vector di Node.
	 * @return Lista di adiacenze vuota.
	 */
	private static HashMap<Integer, Vector<Integer>> getEmptyAdjacencyList(Vector<Node> nodes_vector) {
		HashMap<Integer, Vector<Integer>> adj_list = new HashMap<Integer, Vector<Integer>>();
		for (Node node : nodes_vector) { // Per ciascun nodo passato come parametro
			adj_list.put(node.getId(), new Vector<Integer>());
			// Inizializzo la chiave con l'Id del nodo, e il valore con il suo vettore di adiacenze
			// (O meglio, con un Vector che contenga gli Id dei nodi adiacenti, che in ogni caso per ora è vuoto).
		}
		return adj_list;
	}
	
	/**
	 * Aggiunge un link alla lista di adiacenze dei nodi con Id passati con parametro.
	 * L'aggiunta è effettuata in ultima posizione, in modo che sia più facile rimuoverla successivamente.
	 * 
	 * @param id1
	 * @param id2
	 * @param adj_list
	 */
	private static void addLink(Integer id1, Integer id2, HashMap<Integer, Vector<Integer>> adj_list) {
		adj_list.get(id1).addElement(id2);
		adj_list.get(id2).addElement(id1);
	}
	
	/**
	 * Rimuove l'ultimo link dalla lista di adiacenze dei nodi con Id passato come parametro.
	 * 
	 * @param id1
	 * @param id2
	 * @param adj_list
	 */
	private static void undoLastLink(int id1, int id2, HashMap<Integer, Vector<Integer>> adj_list) {
		// Chiamo un altro metodo per evitare di richiamare due volte il get su adj_list.
		KruskalImplementer.removeLast(adj_list.get(id1));
		KruskalImplementer.removeLast(adj_list.get(id2));
	}
	
	/**
	 * Rimuove l'ultimo collegamento in una sequenza (Vector) di adiacenze per un singolo nodo.
	 * @param vect
	 */
	private static void removeLast(Vector<Integer> vect) {
		vect.removeElementAt(vect.size() - 1); // Rimuove l'ultimo inserimento.
	}
	
	/**
	 * Metodo che segnala eventuali cicli all'interno di un grafo di tipo Graph.
	 * 
	 * @param Grafo come tipo Graph.
	 * @return TRUE se è presente almeno un ciclo, FALSE altrimenti.
	 */
	private static boolean hasCycle(HashMap<Integer, Vector<Integer>> adjacency_list) {
		// Creazione di una HashMap di (NUM_NODES) boolean che memorizza i nodi visitati.
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		Iterator<Entry<Integer, Vector<Integer>>> iterator1 = adjacency_list.entrySet().iterator();
		while (iterator1.hasNext()) {
			visited.put(iterator1.next().getKey(), false); // NON MI PIACEEE
		}
		
		// Chiamata della funzione ricorsiva.
		Iterator<Entry<Integer, Vector<Integer>>> iterator2 = adjacency_list.entrySet().iterator(); // TODO metodo per azzerare l'iterator?
		while (iterator2.hasNext()) {
			int current_id = iterator2.next().getKey();
			if (!visited.get(current_id)) {
				if (KruskalImplementer.isCyclicUtil(adjacency_list, current_id, STARTING_NODE_INDEX, visited)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Metodo ricorsivo utilizzato dal metodo hasCycle.
	 * @param Lista di adiacenze.
	 * @param Id del vertice corrente.
	 * @param Id del vertice di arrivo.
	 * @param Array di nodi visitati.
	 * @return TRUE se dal nodo corrente è possibile compiere un ciclo e tornare su se stessi.
	 */
	private static boolean isCyclicUtil(HashMap<Integer, Vector<Integer>> adjacency_list, int current_node_id, int parent_node_id, HashMap<Integer, Boolean> visited) {
		// Segna il nodo corrente come visitato
		visited.replace(current_node_id, true);
		// Per tutti i vertici (visti come id) raggiungibili dal nodo 
		for (int adj_node_id : adjacency_list.get(current_node_id)) {
			if (!visited.get(adj_node_id)) { // Se il nodo adiacente non è stato visitato
				if (isCyclicUtil(adjacency_list, adj_node_id, current_node_id, visited)) {
					return true;
				}
			} else if (adj_node_id != parent_node_id) { // Altrimenti (se è già stato visitato) controllo che sia il padre
				// Se non è il nodo da cui arrivo direttamente (nodo padre), allora vuol dire che posso ciclare sul grafo
				return true;
			}
		}
		return false;
	}
    
	// Metodi di utilità della classe.
	// Utilizzano pezzi dell'algoritmo di Kruskal, e potrebbero tornare utili in futuro per operazioni sui grafi.
	
	/**
	 * Metodo di utilità.
	 * Controlla che il grafo inserito come parametro abbia un ciclo al suo interno.
	 * Si avvale della lista di adiacenze del grafo e del metodo privato di questa classe "hasCycle".
	 * 
	 * @param graph
	 * @return TRUE se il grafo presenta un ciclo.
	 */
	public static boolean hasCycle(Graph _graph) { // Al momento è utilizzato dalla classe responsabile dei test.
		if (_graph.checkDirected()) {
			System.out.println(ERROR_DIRECTED_GRAPH);
			return false;
		} else {
			return KruskalImplementer.hasCycle(_graph.getIdAdjacencyList());
		}
	}
}