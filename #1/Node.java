package dijkstra_kruskal;

import java.util.LinkedList;
import java.util.Vector;

public class Node {
	
	private String nameNode;
	private boolean visited;
	private boolean start;
	private boolean end;
	private Vector<Edge> collegamenti = new Vector<Edge>();
	
	/**
	 * il nome del nodo viene passato come parametro
	 * la variabile visited viene inizializzata sempre come false
	 * @param nameNode
	 * @author mauro
	 */
	public Node(String nameNode){
		this.nameNode=nameNode;
		visited=false;
		start= false;
		end = false;
	}

	
	/**
	 * getter 
	 * @return il nome del nodo
	 * @author mauro
	 */
	public String getNameNode() {
		return nameNode;
	}

	
	public Vector<Edge> getCollegamenti() {
		return collegamenti;
	}


	public void setCollegamenti(Vector<Edge> collegamenti) {
		this.collegamenti = collegamenti;
	}


	public boolean isStart() {
		return start;
	}


	public void setStart(boolean start) {
		this.start = start;
	}


	public boolean isEnd() {
		return end;
	}


	public void setEnd(boolean end) {
		this.end = end;
	}


	public void setVisited(boolean visited) {
		this.visited = visited;
	}


	/**
	 * setter
	 * @param nameNode nuovo nome del nodo
	 * @author mauro
	 */
	public void setNameNode(String nameNode) {
		this.nameNode = nameNode;
	}

	/**
	 * getter
	 * @return se il nodo è stato attraversato o meno
	 * @author mauro
	 */
	public boolean getVisited() {
		return visited;
	}
	
	/**
	 * indica se è il nodo di partenza
	 * @author mauro
	 * @author alessandro
	 */
	public void start(){
		start=true;
	}
	
	/**
	 * indica se è il nodo di arrivo
	 * @author mauro
	 * @author alessandro
	 */
	public void end(){
		end=true;
	}

	
	

	/**
	 * cambia la variabile visited in true
	 * @author mauro
	 */
	public void visitedTrue(){
		visited=true;
	}
	
	
	/**
	 * cambia la variabile visited in false
	 * @author mauro
	 */
	public void visitedFalse(){
		visited=false;
	}

	/**
	 * se un nodo ha come collegamenti verso altri nodi
	 * un edge con lo stesso nome della stringa passsata come
	 * parametro, torna l'indice della posizione all'interno
	 * del Vector collegamenti
	 * @param nomeEdge 
	 * @return
	 * @author mauro
	 */
	public int findEdge(String nomeEdge){
		int ritorno;
		for(ritorno=0; ritorno<collegamenti.size(); ritorno++){
			if(collegamenti.get(ritorno).getNameEdge().equalsIgnoreCase(nomeEdge)) break;
		}
		
		return ritorno;
	}
}