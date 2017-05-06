package it.unibs.ing.fp.kruskalxml;

/**
 * 
 * Classe che modella un edge (o collegamento) tra due nodi di un grafo
 * 
 * Implementa l'interfacia Comparable in modo da ordinare gli edge secondo il
 * peso
 * 
 * @author Stefano Poma
 * @author Daniele Vezzoli
 * @author Matteo Zanolla
 *
 */
public class Edge implements Comparable<Edge> {
	private Node startNode;
	private Node endNode;
	private int weight;

	public Edge(Node _startNode, Node _endNode, int _weight) {
		startNode = _startNode;
		endNode = _endNode;
		weight = _weight;
	}

	// TODO mettere in ordine alfabetico per controolare più facilmente
	public boolean equals(Edge edge) {
		boolean flag = false;
		if (this.startNode.equals(edge.getStartNode()) && this.endNode.equals(edge.getEndNode())
				&& (this.weight == edge.getWeight())) {
			flag = true;
		}
		if (this.startNode.equals(edge.getEndNode()) && this.endNode.equals(edge.getStartNode())
				&& (this.weight == edge.getWeight())) {
			flag = true;
		}
		return flag;
	}

	@Override
	public String toString() {
		return String.format(startNode.getLabel() + " - " + endNode.getLabel() + " = " + weight);
	}

	public Node getStartNode() {
		return startNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public int getWeight() {
		return weight;
	}

	/**
	 * Metodo per ordinare gli edge in base al peso
	 */
	@Override
	public int compareTo(Edge e1) {
		return Integer.compare(this.getWeight(), e1.getWeight());
	}
}
