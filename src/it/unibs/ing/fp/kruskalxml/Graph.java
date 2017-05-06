package it.unibs.ing.fp.kruskalxml;

import java.util.PriorityQueue;
import java.util.Vector;

/**
 * Classe Graph, modella un grafo. Al suo interno troviamo un Vector di Nodes ed
 * una PriorityQueue di edge. La scelta della coda rispetto ad un normale array
 * sta nel fatto che: 1- E' più efficiente 2- E' sempre ordinata in base al peso
 * degli Edge e quindi ci sono meno controlli da fare
 * 
 * @author Stefano Poma
 * @author Daniele Vezzoli
 * @author Matteo Zanolla
 *
 */
public class Graph {

	private Vector<Node> nodes = new Vector<>();
	private PriorityQueue<Edge> edges = new PriorityQueue<>();

	/**
	 * Aggiunge un nodo al Vector nodes se il nodo non è già presente
	 * 
	 * @param node
	 *            Il nodo da aggiungere
	 * @return true se il nodo è stato aggiunto, false altrimenti.
	 */
	public boolean addNode(Node node) {
		boolean flag = true;
		// if(edges.contains(edge));
		for (Node n : nodes) {
			if (n.equals(node)) {
				flag = false;
			}
		}
		if (flag) {
			nodes.add(node);
		}
		return flag;
	}

	public Vector<Node> getNodes() {
		return nodes;
	}

	public PriorityQueue<Edge> getEdges() {
		return edges;
	}

	/**
	 * Aggiunge un edge alla coda edges se l'edge non è già presente
	 * 
	 * @param edge
	 *            l'edge da aggiungere
	 * @return true se l'edge è stato aggiunto, false altrimenti.
	 */
	public boolean addEdge(Edge edge) {
		boolean flag = false;
		if (!edges.contains(edge)) {
			edge.getStartNode().setLinkedNodes(edge.getEndNode());
			edge.getEndNode().setLinkedNodes(edge.getStartNode());
			edge.getStartNode().addEdge(edge);
			edge.getEndNode().addEdge(edge);
			edges.add(edge);
			flag = true;
		}
		return flag;
	}

	public void setNodes(Vector<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * Restituisce il nodo contrassegnato da id
	 * 
	 * @param id
	 * @return Se il nodo è presente ritorna il nodo, altrimenti null
	 */
	public Node getNodeById(String id) {
		Node n = null;
		for (Node node : nodes) {
			if (node.getId().equals(id))
				n = node;
		}
		return n;
	}

	/**
	 * Stampa il grafo
	 */
	public void printGraph() {
		for (Node n : nodes) {
			System.out.println(n);
		}
		System.out.print("\n\n\n\n");
		for (Edge e : edges) {
			System.out.println(e + " ");
		}
	}

}
