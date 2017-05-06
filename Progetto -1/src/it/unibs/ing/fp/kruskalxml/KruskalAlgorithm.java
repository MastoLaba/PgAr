package it.unibs.ing.fp.kruskalxml;

import java.util.PriorityQueue;
import java.util.Vector;

/**
 * Classe statica per l'esecuzione dell'algoritmo di Kruskal
 * 
 * @author Stefano Poma
 * @author Daniele Vezzoli
 * @author Matteo Zanolla
 *
 */
public class KruskalAlgorithm {

	/**
	 * Inizializza i gruppi mettendo in ogni gruppo un nodo Es. gruppo0 -> A;
	 * gruppo1 -> B
	 * 
	 * @author Stefano Poma
	 * 
	 * @param nodes
	 *            Tutti i nodi del grafo
	 * @param groups
	 *            I gruppi da inizializzare
	 */
	private static void initGroups(Vector<Node> nodes, Vector<GroupString> groups) {
		for (int i = 0; i < nodes.size(); i++) {
			GroupString a = new GroupString();
			groups.add(a);
			groups.get(i).addSet(nodes.get(i).getLabel());

			// System.out.println("Indice : " + i);
			// System.out.println(groups.get(i).getSet());

		}
	}

	/**
	 * Metodo per l'esecuzione dell'algoritmo di Kruskal
	 * 
	 * @author Stefano Poma
	 * @author Matteo Zanolla
	 * @author Daniele Vezzoli
	 * 
	 * @param graph
	 *            Il grafo su cui eseguire l'algoritmo
	 * @return Il Vector contenente il MST
	 */
	public static Vector<Edge> startAlgorithm(Graph graph) {

		Vector<Node> nodes = new Vector<>(graph.getNodes());
		PriorityQueue<Edge> edges = new PriorityQueue<>(graph.getEdges());
		Vector<GroupString> groups = new Vector<>();

		Vector<Edge> path = new Vector<>();

		initGroups(nodes, groups);

		while (edges.size() > 0) {
			int indice1 = -1;
			int indice2 = -1;
			boolean presente = false;

			Edge currentEdge = edges.poll();

			for (int i = 0; i < groups.size(); i++) {
				// Trovo i gruppi dove si trovano il nodo di partenza ed il nodo
				// di arrivo dell'edge
				if (groups.get(i).contain2Set(currentEdge.getStartNode().getLabel(),
						currentEdge.getEndNode().getLabel()))
					presente = true;
				if (groups.get(i).contain1Set(currentEdge.getStartNode().getLabel()))
					indice1 = i;
				if (groups.get(i).contain1Set(currentEdge.getEndNode().getLabel()))
					indice2 = i;
			}

			if (!presente) {
				path.add(currentEdge);
				// Unisco i due gruppi
				groups.get(indice2).addSet(groups.get(indice1).getSet());
				groups.remove(indice1);
			}

		}
		System.out.println("Minimum Spanning Tree:");
		for (int i = 0; i < path.size(); i++) {
			System.out.println(path.get(i));

		}

		return path;

	}
}
