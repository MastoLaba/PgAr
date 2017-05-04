package deathstar.consegna1;

import java.util.HashMap;
import java.util.Vector;

/** Classe per la rappresentazione di una struttura dati di tipo grafo
 * 
 * @author Lorenzo Nodari
 * @author Michele Dusi
 * 
 */
public class Graph {

	private Vector<Node> nodes;
	private boolean directed = false;
	
	// Costruttori
	
	/**
	 * Costruttore vuoto, serve per la copia del grafo (vedi sotto).
	 */
	public Graph() {
		nodes = new Vector<Node>();
	}
	
	/**
	 * Costruttore con la lista di nodi.
	 * 
	 * @param Vector di Node.
	 */
	public Graph(Vector<Node> nodes) {
		this.nodes = nodes;
		this.forceUndirected();
	}
	
	/**
	 * Permette (in più) di definire fin da subito se il grafo non è orientato.
	 * @param nodes
	 * @param is_directed
	 */
	public Graph(Vector<Node> nodes, boolean is_directed) {
		this.nodes = nodes;
		this.directed = is_directed;
		if (!this.directed) {
			this.forceUndirected();
		}
	}
	
	// Getters.
	
	/**
	 * Getter per il vettore di oggetti Node.
	 * 
	 * @return
	 */
	public Vector<Node> getNodes() {
		return this.nodes;
	}
	
	/**
	 * Getter per il numero di nodi.
	 * 
	 * @return
	 */
	public int getNumNodes() {
		return this.nodes.size();
	}
	
	/**
	 * Restituisce l'oggetto nodo del grafo con la Label corrispondente.
	 * 
	 * @param searched_label
	 * @return Nodo
	 */
	public Node getNodeByLabel(String searched_label) {
		for (Node nodo : this.nodes) {
			if (nodo.getLabel().equals(searched_label)) {
				return nodo;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param searched_id
	 * @return
	 */
	public Node getNodeById(int searched_id) {
		for (Node nodo : this.nodes) {
			if (nodo.getId() == searched_id) {
				return nodo;
			}
		}
		return null;
	}
	
	// Controlli

	/**
	 * Controlla se il grafo è orientato.
	 * 
	 * @return TRUE se il grafo è orientato, FALSE se il grafo è non-orientato.
	 */
	/*
	 * Dovrei evitare i controlli inversi, il problema è che non esiste un ordine 
	 * nei collegamenti di un nodo, quindi non ho modo (a meno di creare e memorizzare 
	 * il tutto in strutture indicizzate apposta, ma anche no) di capire a che punto del
	 * controllo sono arrivato.
	 */
	public boolean checkDirected() {
		for (Node nodo : nodes) { // Per ogni nodo del grafo
			for (Node linked_node : nodo.getLinked()) { // Per ogni nodo raggiungibile da quel nodo
				// Controllo che esista il cammino inverso
				if (!linked_node.hasArc(nodo.getId())) {
					// Se non esiste, il grafo è orientato -> restituisco TRUE
					return true;
				} else if (nodo.getWeightArc(linked_node.getId()) != linked_node.getWeightArc(nodo.getId())) {
					// Anche se ho archi di peso diverso il grafo è orientato -> restituisco TRUE
					return true;
				}
			}
		}
		return false;
	}
	
	public void setDirected(boolean is_directed) {
		this.directed = is_directed;
		if (!this.directed) {
			this.forceUndirected();
		}
	}

	/**
	 * Restituisce TRUE se all'interno del grafo è presente un nodo che abbia come Id
	 * l'intero passato come parametro.
	 * 
	 * @param Id del nodo cercato
	 * @return TRUE se il nodo è presente all'interno del grafo.
	 */
	public boolean containsNodeId(int node_id) {
		for (Node node : this.nodes) {
			if (node.getId() == node_id) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Restituisce TRUE se all'interno del grafo è presente un nodo che abbia come Label
	 * la stringa passato come parametro.
	 * 
	 * @param Label del nodo cercato
	 * @return TRUE se il nodo è presente all'interno del grafo.
	 */
	public boolean containsNodeLabel(String node_label) {
		for (Node node : this.nodes) {
			if (node.getLabel().equals(node_label)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Restituisce TRUE se all'interno del grafo è presente l'oggetto Node passato come parametro.
	 * 
	 * @param Nodo cercato
	 * @return TRUE se il grafo contiene il nodo
	 */
	public boolean containsNode(Node searched_node) {
		return (this.nodes.contains(searched_node));
	}
	
	/**
	 * Restituisce TRUE se l'arco passato come parametro esiste all'interno del grafo.
	 * Il peso non è rilevante ai fini della ricerca.
	 * 
	 * @param Arco da cercare.
	 * @return 
	 */
	public boolean hasArc(Arc arc) {
		Node node1 = (arc != null) ? arc.getNode1() : null;
		Node node2 = (arc != null) ? arc.getNode2() : null;
		return (this.linkExists(node1, node2));
	}

	/**
	 * Restituisce TRUE se all'interno del grafo esiste un arco fra i due nodi passati come parametro.
	 * Il peso non è rilevante ai fini della ricerca. Nel caso in cui il grafo sia non-orientato,
	 * il controllo viene effettuato anche nel senso inverso.
	 * 
	 * @param Primo nodo
	 * @param Secondo nodo
	 * @return TRUE se esiste un arco fra i due nodi.
	 */
	public boolean linkExists(Node node1, Node node2) {
		if (node1 == null || node2 == null) { // Primo controllo da fare
			return false;
		} else if (this.containsNodeId(node1.getId()) && this.containsNodeId(node2.getId())) {
			if (this.directed) {
				return node1.hasArc(node2.getId());
			} else {
				return (node1.hasArc(node2.getId()) && node2.hasArc(node1.getId()));
			}
		} else {
			return false;
		}
	}

	/**
	 * Restituisco TRUE se all'interno del grafo è presente un collegamento fra il nodo con il primo Id
	 * e il nodo con il secondi Id.
	 * Il peso non è rilevante ai fini della ricerca. Nel caso in cui il grafo sia non-orientato,
	 * il controllo viene effettuato anche nel senso inverso.
	 * 
	 * @param id1
	 * @param id2
	 * @return TRUE se esiste Arc(id1, id2) nel grafo.
	 */
	public boolean linkExists(int id1, int id2) {
		if (this.containsNodeId(id1) && this.containsNodeId(id2)) {
			if (this.directed) {
				return this.getNodeById(id1).hasArc(id2);
			} else {
				return (this.getNodeById(id1).hasArc(id2) && this.getNodeById(id2).hasArc(id1));
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Restituisce TRUE se esiste un arco all'interno del grafo che collega i due nodi passati come parametri
	 * e che abbia esattamente il peso richiesto.
	 * 
	 * @param node1
	 * @param node2
	 * @param weight
	 * @return TRUE se esiste un arco del peso indicato.
	 */
	public boolean linkWeightedExists(Node node1, Node node2, int weight) {
		return (this.linkExists(node1, node2) && (node1.getWeightArc(node2.getId()) == weight));
	}

	// Metodi per l'aggiunta di archi, collegamenti o nodi.
	
	/**
	 * Dato in ingresso un nuovo arco, lo aggiunge al grafo aggiornando i collegamenti dei nodi.
	 * 
	 * @param new_arc
	 * @return TRUE se il collegamento viene effettuato.
	 */
	public void addArc(Arc new_arc) {
		Node start_node = new_arc.getNode1();
		Node end_node = new_arc.getNode2();
		this.linkNodes(start_node, end_node, new_arc.getWeight());
	}
	
	/**
	 * Dati in ingresso gli Id di due nodi, se questi sono presenti all'interno del grafo
	 * e non sono ancora collegati, vengono collegati da un arco di peso definito come parametro.
	 * 
	 * @param start_id
	 * @param end_id
	 * @param weight
	 * @return TRUE se il collegamento viene effettuato.
	 */
	public void linkNodes(int start_id, int end_id, int weight) {
		Node start_node = this.getNodeById(start_id);
		Node end_node = this.getNodeById(end_id);
		this.linkNodes(start_node, end_node, weight);
	}
	
	/**
	 * Date in ingresso le etichette di due nodi, se questi sono presenti all'interno del grafo
	 * e non sono ancora collegati, vengono collegati da un arco di peso definito come parametro.
	 * 
	 * @param start_label
	 * @param end_label
	 * @param weight
	 * @return TRUE se il collegamento viene effettuato.
	 */
	public void linkNodes(String start_label, String end_label, int weight) {
		Node start_node = this.getNodeByLabel(start_label);
		Node end_node = this.getNodeByLabel(end_label);
		this.linkNodes(start_node, end_node, weight);
	}
	
	/**
	 * Dati in ingresso due nodi, se questi sono presenti all'interno del grafo e non sono ancora collegati,
	 * vengono collegati da un arco di peso deinito come parametro.
	 * 
	 * @param start_node
	 * @param end_node
	 * @param weight
	 * @return TRUE se il collegamento viene effettuato.
	 */
	public void linkNodes(Node start_node, Node end_node, int weight) {
		if ((start_node != null && end_node != null) && !start_node.hasArc(end_node.getId())) {
			start_node.addArc(end_node, weight);
			if (!this.directed) {
				end_node.addArc(start_node, weight);
			}
		}
	}

	/**
	 * Dato un ingresso un arco, se questo esiste all'interno del grafo viene rimosso.
	 * Attenzione: rimuove i collegamenti da ENTRAMBI gli estremi se e solo se il grafo è NON orientato.
	 * 
	 * @param Arco da rimuovere
	 */
	public void removeArcById(Arc to_be_removed) {
		if (this.hasArc(to_be_removed)) {
			to_be_removed.getNode1().removeArc(to_be_removed.getNode2().getId());
			if (!this.directed) {
				to_be_removed.getNode1().removeArc(to_be_removed.getNode2().getId());
			}
		}
	}
	
	/**
	 * Dati due nodi in ingresso, rimuove il collegamento che va dal primo nodo al secondo.
	 * 
	 * @param Primo nodo.
	 * @param Secondo nodo.
	 * @return TRUE se la rimozione è stata effettuata.
	 */
	public boolean removeLink(Node start_node, Node end_node) {
		if (this.linkExists(start_node, end_node)) {
			start_node.removeArc(end_node.getId());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Aggiunge un nodo passato come parametro al grafo, se questo non è già presente.
	 * 
	 * @param new_node
	 * @return TRUE se l'aggiunta viene effettuata.
	 */
	public void addNode(Node new_node) {
		if (!this.nodes.contains(new_node)) {
			this.nodes.add(new_node);
		}
	}

	/**
	 * Aggiunge i doppi collegamenti in modo che siano simmetrici, per creare un grafo non orientato.
	 * Se trova due collegamenti in conflitto fra loro si blocca e restituisce un valore FALSE.
	 * 
	 * @return TRUE se i doppi collegamenti vanno a buon fine.
	 */
	/* TODO
	 * - In questo modo faccio i doppi controlli, che rallentano parecchio; si può migliorare.
	 */
	public boolean forceUndirected() {
		boolean everything_okay = true; // Rimane TRUE se e solo se non ho avuto conflitti fra archi inversi.
		for (Node start_node : this.nodes) {
			for (Node end_node : start_node.getLinked()) {
				// Per tutti i collegamenti da start_node
				int current_weight = start_node.getWeightArc(end_node.getId());
				if (end_node.hasArc(start_node.getId())) { // Se esiste il collegamento inverso
					if (current_weight != end_node.getWeightArc(start_node.getId())) {
						// Controllo che il collegamento inverso sia dello stesso peso, altrimenti ho un errore.
						everything_okay = false;
					}
				} else { // Se non è presente il collegamento inverso:
					end_node.addArc(start_node, current_weight); // Lo creo.
				}
			}
		}
		return everything_okay;
	}
	
	// Metodi per Kruskal e Dijkstra.

	/**
	 * Restituisce il minimo sotto-albero del grafo che attraversa tutti i nodi.
	 * O almeno ci prova.
	 * (Kruskal non garantisce il risultato ottimo).
	 * 
	 * @return minimo sotto-albero del grafo.
	 */
	public Graph getMinimumSpanningTree() {
		/* Restituisce una ALTRO grafo che contiene l'albero minimo */
		if (this.directed) { // OPPURE if (!this.checkDirected), forse è meglio
			// ECCEZIONE -> non posso lanciare Kruskal su grafi orientati.
			return this;
		} else {
			return KruskalImplementer.getMinimumSpanningTree(this);
		}
	}
	
	/**
	 * Restituisce il minimo cammino interno al grafo che colleghi il nodo di partenza al nodo finale.
	 * 
	 * @return minimo cammino del grafo da StartNode a EndNode.
	 */
	public Graph getMinimumPath() {
		/* Restituisce un ALTRO grafo che contiene il cammino minimo */
		return DijkstraImplementer.applyDijkstra(this);
	}
	
	// Metodi per la copia del grafo.
	
	/**
	 * Crea un nuovo oggetto GraphList con gli stessi nodi, ma senza
	 * creare alcun collegamento fra di essi. I nodi sono uguali per Label, ma differenti per Id.
	 * 
	 * @return Oggetto GraphList con gli stessi nodi non collegati.
	 */
	public Graph cloneOnlyNodes() {
		Graph unlinked_graph = new Graph();
		for (Node nodo : this.nodes) {
			Node cloned_node = new Node (nodo.getLabel(), new Vector<Arc>());
			unlinked_graph.addNode(cloned_node);
		}
		return unlinked_graph;
	}
	
	// Metodi di output.

	/**
	 * Restituisce la lista di adiacenze del grafo, solamente con i collegamenti, senza i relativi pesi.
	 * 
	 * @return lista di adiacenze come Array di Vector di Interi.
	 */
	public HashMap<Integer, Vector<Integer>> getIdAdjacencyList() {
		HashMap<Integer, Vector<Integer>> adj_list = new HashMap<Integer, Vector<Integer>>();
		for (Node node : nodes) {
			// Uso l'Id del nodo come chiave, gli id dei suoi nodi adiacenti come elementi del Vector.
			Vector<Integer> adj_id_vector = new Vector<Integer>();
			for (Node linked_node : node.getLinked()) {
				// Ogni id adiacente viene memorizzato nel vector.
				adj_id_vector.add(linked_node.getId());
			}
			// Inserisco tutto nella HashMap.
			adj_list.put(node.getId(), adj_id_vector); 
		}
		return adj_list;
	}
	
	/** Restituisce una copia esatta del grafo
	 * 
	 * @return Un grafo identico
	 */
	public Graph clone() {
		return new Graph(this.nodes, this.directed);
	}
	
	/**
	 * Restituisce una stringa che descrive i Nodi e i collegamenti.
	 */
	@Override
	public String toString() {
		if (nodes.size() == 0) {
			return "Grafo vuoto";
		}
		StringBuffer s = new StringBuffer();
		for (Node node : this.nodes) {
			s.append(node.toString());
		}
		return s.toString();
	}
}