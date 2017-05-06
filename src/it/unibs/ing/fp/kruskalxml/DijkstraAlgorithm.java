package it.unibs.ing.fp.kruskalxml;

import java.util.Vector;

/**
 * Classe statica per l'esecuzione dell'algoritmo di Dijsktra
 * 
 * @author Stefano Poma
 * @author Matteo Zanolla
 * @author Daniele Vezzoli
 *
 */
public class DijkstraAlgorithm {

	private static Vector<Node> settledNodes = new Vector<>();
	private static Vector<Node> unSettledNodes = new Vector<>();
	private static Node endNode = new Node();
	private static Vector<String> path = new Vector<>();

	/**
	 * Metodo per inizializzare i nodi. All'inizio pongo il nodo iniziale a
	 * distanza = 0 Tutti gli altri nodi li pongo a distanza "infinito"
	 *
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 * 
	 * @param nodes
	 *            Il Vector di Node da inizializzare
	 */
	private static void initArray(Vector<Node> nodes) {
		for (Node n : nodes) {
			if (n.getStart()) {
				n.setDistance(0);
				settledNodes.add(n);
			}

			else {
				if (n.getEnd())
					endNode = n;
				// n.setDistance(99);
				n.setDistance(Integer.MAX_VALUE); // 2^32 > 99 lol
				unSettledNodes.add(n);
			}
		}

		for (Node n : settledNodes)
			System.out.println("settleNodes:" + n);

		for (Node n : unSettledNodes)
			System.out.println("unsettleNodes:" + n);
	}

	/**
	 * Metodo per aggiornare i nodi
	 * 
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 * @param Node
	 *            Il nodo attuale
	 */
	private static void updateNearNodes(Node actualNode) {
		for (Edge e : actualNode.getEdges()) {
			for (Node nearNode : actualNode.getLinkedNodes()) {
				System.out.println("nodo di partenza: " + actualNode.getLabel() + " nodo arrivo: " + nearNode.getLabel()
						+ " peso: " + e.getWeight() + " distanza " + nearNode.getDistance());
				if (e.getStartNode().equals(nearNode) || e.getEndNode().equals(nearNode)) {
					if (unSettledNodes.contains(nearNode)) {
						if ((actualNode.getDistance() + e.getWeight()) < nearNode.getDistance()) {
							nearNode.setDistance((actualNode.getDistance() + e.getWeight()));
							nearNode.setPreviousNode(actualNode);
							System.out.println("previous node: " + nearNode.getPreviousNode());
							System.out.println("nuova distanza: " + nearNode.getDistance());
						}
					}
				}
			}
		}
	}

	/**
	 * Metodo per decidere il nodo da considerare successivamente
	 * 
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 * @return Il nodo scelto
	 */
	private static Node takeNextNode() {
		Node nextNode = null;
		for (Node n : unSettledNodes) {

			if (nextNode == null || nextNode.getDistance() > n.getDistance())
				nextNode = n;
		}
		System.out.println("nodo con distanza minima: " + nextNode.getLabel());
		return nextNode;
	}

	/**
	 * Metodo che crea il path finale
	 * 
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 */
	private static void createPath() {
		Node pathNode = endNode;
		while (!pathNode.getStart()) {
			path.add(pathNode.getLabel());
			pathNode = pathNode.getPreviousNode();
		}
		path.add(pathNode.getLabel());

		Vector<String> tmp = new Vector<>();

		for (int i = (path.size() - 1); i >= 0; i--) {
			tmp.add(path.get(i));
		}

		path = tmp;
	}

	/**
	 * Metodo che stampa il path finale
	 * 
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 */
	private static void printPath() {
		System.out.println("Il path ottimale �:\n");
		for (int i = (path.size() - 1); i >= 0; i--) {
			System.out.println(path.get(i));
		}

		System.out.println("\n");
	}

	/**
	 * Metodo che esegue l'algoritmo di Dijkstra
	 * 
	 * @param graph
	 *            Il grafo su cui eseguire l'algoritmo
	 * @return Un vector contenente le label del path finale
	 */

	public static Vector<String> startAlgorithm(Graph graph) {
		initArray(graph.getNodes());
		boolean fine = false;
		while ((unSettledNodes.size() > 0) && (!fine)) {
			// updateNearNodes(graph.getEdges());
			updateNearNodes(settledNodes.lastElement());
			settledNodes.add(takeNextNode());
			for (Node n : settledNodes)
				System.out.println("SettledNodes " + n.getLabel() + "\n");
			unSettledNodes.remove(takeNextNode());
			for (Node n : unSettledNodes)
				System.out.println("unSettledNodes " + n.getLabel() + "\n");
			if (endNode.equals(settledNodes.lastElement())) {
				fine = true;
			}
		}
		createPath();
		printPath();
		return path;

	}
}